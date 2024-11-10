package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.PlayerSeasonStatApi;
import org.d11.boot.api.v2.model.CreatePlayerSeasonStatInputRequestBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatResponseBodyDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatsResponseBodyDTO;
import org.d11.boot.api.v2.model.UpdatePlayerSeasonStatInputRequestBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.CreatePlayerSeasonStatInput;
import org.d11.boot.spring.model.PlayerSeasonStat;
import org.d11.boot.spring.model.UpdatePlayerSeasonStatInput;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.PlayerSeasonStatService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Player season stat API REST controller implementation.
 */
@RestController
public class PlayerSeasonStatControllerV2 extends RepositoryServiceController<PlayerSeasonStatService>
        implements PlayerSeasonStatApi {

    /**
     * REST controller mapper.
     */
    private final RestControllerMapperV2 mapper = Mappers.getMapper(RestControllerMapperV2.class);

    /**
     * Create a new controller.
     *
     * @param playerSeasonStatService The service the controller will use.
     */
    @Autowired
    public PlayerSeasonStatControllerV2(final PlayerSeasonStatService playerSeasonStatService) {
        super(playerSeasonStatService);
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByPlayerId(final Long playerId) {
        final List<PlayerSeasonStat> playerSeasonStats = getRepositoryService().getByPlayerId(playerId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsBySeasonId(final Long seasonId,
                                                                                           final Integer page) {
        final List<PlayerSeasonStat> playerSeasonStats = getRepositoryService().getBySeasonId(seasonId, page);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByTeamIdAndSeasonId(
            final Long teamId,
            final Long seasonId
    ) {
        final List<PlayerSeasonStat> playerSeasonStats =
                getRepositoryService().getByTeamIdAndSeasonId(teamId, seasonId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    public ResponseEntity<PlayerSeasonStatsResponseBodyDTO> getPlayerSeasonStatsByD11TeamIdAndSeasonId(
            final Long d11TeamId,
            final Long seasonId
    ) {
        final List<PlayerSeasonStat> playerSeasonStats =
                getRepositoryService().getByD11TeamIdAndSeasonId(d11TeamId, seasonId);

        return ResponseEntity.ok(new PlayerSeasonStatsResponseBodyDTO()
                .playerSeasonStats(getMapper().map(playerSeasonStats, PlayerSeasonStatDTO.class)));
    }

    @Override
    @RoleAdmin
    public ResponseEntity<PlayerSeasonStatResponseBodyDTO> createPlayerSeasonStat(
            final CreatePlayerSeasonStatInputRequestBodyDTO createPlayerSeasonStatInputRequestBodyDTO) {
        final CreatePlayerSeasonStatInput createPlayerSeasonStatInput = this.mapper.mapToCreatePlayerSeasonStatInput(
                createPlayerSeasonStatInputRequestBodyDTO.getPlayerSeasonStat()
        );
        final PlayerSeasonStat result = getRepositoryService().createPlayerSeasonStat(createPlayerSeasonStatInput);

        final PlayerSeasonStatResponseBodyDTO responseBody = new PlayerSeasonStatResponseBodyDTO()
                .playerSeasonStat(this.mapper.mapToPlayerSeasonStatDTO(result));

        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Override
    @RoleAdmin
    public ResponseEntity<PlayerSeasonStatResponseBodyDTO> updatePlayerSeasonStat(
            final Long playerSeasonStatId,
            final UpdatePlayerSeasonStatInputRequestBodyDTO updatePlayerSeasonStatInputRequestBodyDTO
    ) {
        final UpdatePlayerSeasonStatInput updatePlayerSeasonStatInput = this.mapper.mapToUpdatePlayerSeasonStatInput(
            updatePlayerSeasonStatInputRequestBodyDTO.getPlayerSeasonStat()
        );
        final PlayerSeasonStat result = getRepositoryService().updatePlayerSeasonStat(playerSeasonStatId,
                                                                                      updatePlayerSeasonStatInput);
        final PlayerSeasonStatResponseBodyDTO responseBody = new PlayerSeasonStatResponseBodyDTO()
                .playerSeasonStat(this.mapper.mapToPlayerSeasonStatDTO(result));

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
