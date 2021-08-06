package org.d11.boot.application.service;

import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.PositionRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides update services for players.
 */
@Service
public class PlayerAdminService extends D11BootService {

    /**
     * The player repository the service will use.
     */
    private final PlayerRepository playerRepository;
    /**
     * The player season stat repository the service will use.
     */
    private final PlayerSeasonStatRepository playerSeasonStatRepository;
    /**
     * The country repository the service will use.
     */
    private final CountryRepository countryRepository;
    /**
     * The season repository the service will use.
     */
    private final SeasonRepository seasonRepository;
    /**
     * The team repository the service will use.
     */
    private final TeamRepository teamRepository;
    /**
     * The D11 team repository the service will use.
     */
    private final D11TeamRepository d11TeamRepository;
    /**
     * The position repository the service will use.
     */
    private final PositionRepository positionRepository;

    /**
     * Creates a new player admin service.
     *
     * @param playerRepository           The player repository the service will use.
     * @param playerSeasonStatRepository The player season stat repository the service will use.
     * @param countryRepository          The country repository the service will use.
     * @param seasonRepository           The season repository the service will use.
     * @param teamRepository             The team repository the service will use.
     * @param d11TeamRepository          The D11 team repository the service will use.
     * @param positionRepository         The position repository the service will use.
     */
    public PlayerAdminService(final PlayerRepository playerRepository,
                              final PlayerSeasonStatRepository playerSeasonStatRepository,
                              final CountryRepository countryRepository,
                              final SeasonRepository seasonRepository,
                              final TeamRepository teamRepository,
                              final D11TeamRepository d11TeamRepository,
                              final PositionRepository positionRepository) {
        this.playerRepository = playerRepository;
        this.playerSeasonStatRepository = playerSeasonStatRepository;
        this.countryRepository = countryRepository;
        this.seasonRepository = seasonRepository;
        this.teamRepository = teamRepository;
        this.d11TeamRepository = d11TeamRepository;
        this.positionRepository = positionRepository;
    }

    /**
     * Inserts a player and a player season stat for the current season.
     *
     * @param insertPlayerDTO Details for the player that will be inserted.
     * @return Result of the insert with ids of created entities if successful and list of errors if not.
     */
    public InsertPlayerResultDTO insertPlayer(final InsertPlayerDTO insertPlayerDTO) {
        Player player = map(insertPlayerDTO, Player.class);
        player.setCountry(this.countryRepository.findById(insertPlayerDTO.getCountryId()).orElse(null));

        PlayerSeasonStat playerSeasonStat = new PlayerSeasonStat();
        playerSeasonStat.setPlayer(player);
        playerSeasonStat.setSeason(this.seasonRepository.findFirstByOrderByDateDesc().orElse(null));
        playerSeasonStat.setTeam(this.teamRepository.findById(insertPlayerDTO.getTeamId()).orElse(null));
        playerSeasonStat.setD11Team(this.d11TeamRepository.findById(D11Team.DUMMY_D11_TEAM_ID).orElse(null));
        playerSeasonStat.setPosition(this.positionRepository.findById(insertPlayerDTO.getPositionId()).orElse(null));

        final List<String> errors = getValidationErrors(player, playerSeasonStat);
        if(errors.isEmpty()) {
            player = this.playerRepository.save(player);

            playerSeasonStat.setPlayer(player);
            playerSeasonStat = this.playerSeasonStatRepository.save(playerSeasonStat);

            this.playerSeasonStatRepository.updateRankingsBySeasonId(playerSeasonStat.getSeason().getId());
        }
        return new InsertPlayerResultDTO()
                .playerId(player.getId())
                .playerSeasonStatId(playerSeasonStat.getId())
                .errors(errors);
    }

}
