package org.d11.boot.application.service.api;

import org.d11.boot.api.model.InsertPlayerDTO;
import org.d11.boot.api.model.InsertPlayerResultDTO;
import org.d11.boot.api.model.UpdatePlayerDTO;
import org.d11.boot.api.model.UpdatePlayerResultDTO;
import org.d11.boot.application.model.D11Team;
import org.d11.boot.application.model.Player;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.CountryRepository;
import org.d11.boot.application.repository.D11TeamRepository;
import org.d11.boot.application.repository.PlayerRepository;
import org.d11.boot.application.repository.PlayerSeasonStatRepository;
import org.d11.boot.application.repository.PositionRepository;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.application.repository.TeamRepository;
import org.d11.boot.application.util.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Provides update services for players.
 */
@Service
public class PlayerAdminService extends ApiService {

    /**
     * Inserts a player and a player season stat for the current season.
     *
     * @param insertPlayerDTO Details for the player that will be inserted.
     * @return Result of the insert with ids of created entities if successful and list of errors if not.
     */
    @Transactional
    public InsertPlayerResultDTO insertPlayer(final InsertPlayerDTO insertPlayerDTO) {
        Player player = map(insertPlayerDTO, Player.class);
        player.setCountry(getRepository(CountryRepository.class).findById(insertPlayerDTO.getCountryId()).orElse(null));

        PlayerSeasonStat playerSeasonStat = new PlayerSeasonStat();
        playerSeasonStat.setPlayer(player);
        playerSeasonStat.setSeason(getCurrentSeason());
        playerSeasonStat.setTeam(getRepository(TeamRepository.class).findById(insertPlayerDTO.getTeamId()).orElse(null));
        playerSeasonStat.setD11Team(getRepository(D11TeamRepository.class).findById(D11Team.DUMMY_D11_TEAM_ID).orElse(null));
        playerSeasonStat.setPosition(getRepository(PositionRepository.class).findById(insertPlayerDTO.getPositionId()).orElse(null));

        final List<String> errors = getValidationErrors(player, playerSeasonStat);
        if(errors.isEmpty()) {
            player = getRepository(PlayerRepository.class).save(player);

            playerSeasonStat.setPlayer(player);
            playerSeasonStat = getRepository(PlayerSeasonStatRepository.class).save(playerSeasonStat);

            getRepository(SeasonRepository.class).updateStatsBySeasonId(playerSeasonStat.getSeason().getId());
        }
        return new InsertPlayerResultDTO()
                .playerId(player.getId())
                .playerSeasonStatId(playerSeasonStat.getId())
                .errors(errors);
    }

    /**
     * Updates a player and a player season stat for the current season.
     *
     * @param updatePlayerDTO Details for the player that will be updated.
     * @param seasonStatsOnly Update only season stats if true, update player properties as well if false.
     * @return Result of the update with ids of updated entities if successful and list of errors if not.
     */
    @Transactional
    public UpdatePlayerResultDTO updatePlayer(final UpdatePlayerDTO updatePlayerDTO, final boolean seasonStatsOnly) {
        Player player = getRepository(PlayerRepository.class).findById(updatePlayerDTO.getId()).orElseThrow(NotFoundException::new);
        if(!seasonStatsOnly) {
            map(updatePlayerDTO, player);
        }

        final Season season = getCurrentSeason();
        PlayerSeasonStat playerSeasonStat = getRepository(PlayerSeasonStatRepository.class).findByPlayerIdAndSeasonId(player.getId(), season.getId())
                .orElse(new PlayerSeasonStat());

        playerSeasonStat.setPlayer(player);
        playerSeasonStat.setSeason(season);
        playerSeasonStat.setTeam(getRepository(TeamRepository.class).findById(updatePlayerDTO.getTeamId()).orElse(null));
        playerSeasonStat.setD11Team(getRepository(D11TeamRepository.class).findById(updatePlayerDTO.getD11TeamId()).orElse(null));
        playerSeasonStat.setPosition(getRepository(PositionRepository.class).findById(updatePlayerDTO.getPositionId()).orElse(null));

        final List<String> errors = getValidationErrors(player, playerSeasonStat);
        if(errors.isEmpty()) {
            if(!seasonStatsOnly) {
                player = getRepository(PlayerRepository.class).save(player);
            }
            playerSeasonStat = getRepository(PlayerSeasonStatRepository.class).save(playerSeasonStat);
            getRepository(SeasonRepository.class).updateStatsBySeasonId(playerSeasonStat.getSeason().getId());
        }

        return new UpdatePlayerResultDTO()
                .playerId(player.getId())
                .playerSeasonStatId(playerSeasonStat.getId())
                .errors(errors);
    }

}
