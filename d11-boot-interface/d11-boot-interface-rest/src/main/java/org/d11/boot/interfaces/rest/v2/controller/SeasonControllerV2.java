package org.d11.boot.interfaces.rest.v2.controller;

import org.d11.boot.api.v2.SeasonApi;
import org.d11.boot.api.v2.model.D11TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.PlayerSeasonStatDTO;
import org.d11.boot.api.v2.model.SeasonDTO;
import org.d11.boot.api.v2.model.SeasonResponseBodyDTO;
import org.d11.boot.api.v2.model.SeasonWinnersDTO;
import org.d11.boot.api.v2.model.SeasonWinnersResponseBodyDTO;
import org.d11.boot.api.v2.model.SeasonsResponseBodyDTO;
import org.d11.boot.api.v2.model.TeamSeasonStatDTO;
import org.d11.boot.api.v2.model.UpdateSeasonRequestBodyDTO;
import org.d11.boot.interfaces.rest.RepositoryServiceController;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.security.RoleAdmin;
import org.d11.boot.spring.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Season API REST controller implementation.
 */
@RestController
public class SeasonControllerV2 extends RepositoryServiceController<SeasonService> implements SeasonApi {

    /**
     * Create a new controller.
     *
     * @param seasonService The service the controller will use.
     */
    @Autowired
    public SeasonControllerV2(final SeasonService seasonService) {
        super(seasonService);
    }

    @Override
    public ResponseEntity<SeasonResponseBodyDTO> getSeasonById(final Long seasonId) {
        final Season season = getRepositoryService().getById(seasonId);

        return ResponseEntity.ok(new SeasonResponseBodyDTO()
                .season(getMapper().map(season, SeasonDTO.class)));
    }

    @Override
    public ResponseEntity<SeasonsResponseBodyDTO> getSeasons() {
        final List<Season> seasons = getRepositoryService().getSeasons();

        return ResponseEntity.ok(new SeasonsResponseBodyDTO()
                .seasons(getMapper().map(seasons, SeasonDTO.class)));
    }

    @Override
    public ResponseEntity<SeasonWinnersResponseBodyDTO> getSeasonWinners() {
        final List<SeasonWinnersDTO> seasonWinners = getRepositoryService().getSeasonWinners().stream()
                .map(sw -> new SeasonWinnersDTO()
                        .season(getMapper().map(sw.season(), SeasonDTO.class))
                        .d11TeamSeasonStat(getMapper().map(sw.d11TeamSeasonStat(), D11TeamSeasonStatDTO.class))
                        .teamSeasonStat(getMapper().map(sw.teamSeasonStat(), TeamSeasonStatDTO.class))
                        .playerSeasonStat(getMapper().map(sw.playerSeasonStat(), PlayerSeasonStatDTO.class)))
                .toList();

        return ResponseEntity.ok(new SeasonWinnersResponseBodyDTO().seasonWinners(seasonWinners));
    }

    @Override
    public ResponseEntity<SeasonResponseBodyDTO> getCurrentSeason() {
        final Season season = getRepositoryService().getCurrentSeason();

        return ResponseEntity.ok(new SeasonResponseBodyDTO()
                .season(getMapper().map(season, SeasonDTO.class)));
    }

    @Override
    @RoleAdmin
    public ResponseEntity<SeasonResponseBodyDTO> updateSeason(final Long seasonId,
                                                              final UpdateSeasonRequestBodyDTO requestBody) {
        final SeasonDTO seasonDTO = requestBody.getSeason()
                .id(seasonId);

        final Season season = getRepositoryService().updateSeason(getMapper().map(seasonDTO, Season.class));

        return ResponseEntity.ok(new SeasonResponseBodyDTO().season(getMapper().map(season, SeasonDTO.class)));
    }

}
