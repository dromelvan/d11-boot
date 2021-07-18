package org.d11.boot.application.service;

import org.d11.boot.api.model.AvailablePlayerDTO;
import org.d11.boot.api.model.AvailablePlayersTeamDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.Status;
import org.d11.boot.application.model.TransferDay;
import org.d11.boot.application.model.projection.TransferListingProjection;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.TeamSeasonStatRepository;
import org.d11.boot.application.repository.TransferDayRepository;
import org.d11.boot.application.repository.TransferListingRepository;
import org.d11.boot.application.util.AvailablePlayersByTeamConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides available player services.
 */
@Service
public class AvailablePlayersService extends D11BootService {

    /**
     * The transfer day repository this service will use.
     */
    private final TransferDayRepository transferDayRepository;
    /**
     * The transfer listing repository this service will use.
     */
    private final TransferListingRepository transferListingRepository;
    /**
     * The team season stat repository this service will use.
     */
    private final TeamSeasonStatRepository teamSeasonStatRepository;
    /**
     * The player season stat repository this service will use.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;

    /**
     * Creates a new service.
     *
     * @param transferDayRepository      The transfer day repository this service will use.
     * @param transferListingRepository  The transfer listing repository this service will use.
     * @param teamSeasonStatRepository   The team season stat repository this service will use.
     * @param playerSeasonStatRepository The player season stat repository this service will use.
     */
    @Autowired
    public AvailablePlayersService(final TransferDayRepository transferDayRepository,
                                   final TransferListingRepository transferListingRepository,
                                   final TeamSeasonStatRepository teamSeasonStatRepository,
                                   final PlayerSeasonStatRepository playerSeasonStatRepository) {
        this.transferDayRepository = transferDayRepository;
        this.transferListingRepository = transferListingRepository;
        this.teamSeasonStatRepository = teamSeasonStatRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
    }

    /**
     * Finds transfer listed players if the current transfer window is not pending or all players that do not
     * belong to a D11 team if it is.
     *
     * @return Available players grouped by team.
     */
    public List<AvailablePlayersTeamDTO> findAvailablePlayers() {
        final TransferDay transferDay = this.transferDayRepository.findFirstByOrderByDatetimeDesc().orElse(new TransferDay());
        final Season season = transferDay.getTransferWindow().getMatchWeek().getSeason();
        final Set<Long> teamIds = this.teamSeasonStatRepository.findBySeasonIdOrderByRanking(season.getId())
                .stream()
                .map(teamSeasonStat -> teamSeasonStat.getTeam().getId())
                .collect(Collectors.toSet());

        List<TransferListingProjection> transferListingProjections = transferDay.getStatus().equals(Status.PENDING)
                ? this.playerSeasonStatRepository.findBySeasonId(season.getId())
                : this.transferListingRepository.findByTransferDayId(transferDay.getId());

        transferListingProjections = transferListingProjections
                .stream()
                .filter(transferListingProjection -> transferListingProjection.getD11Team().getId() <= 1
                        && teamIds.contains(transferListingProjection.getTeam().getId()))
                .collect(Collectors.toList());

        final List<AvailablePlayerDTO> availablePlayers = map(transferListingProjections, AvailablePlayerDTO.class);
        return new AvailablePlayersByTeamConverter().convert(availablePlayers);
    }

}
