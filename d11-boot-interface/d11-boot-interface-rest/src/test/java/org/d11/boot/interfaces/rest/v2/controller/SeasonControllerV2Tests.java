package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.SeasonApi;
import org.d11.boot.api.v2.model.SeasonDTO;
import org.d11.boot.api.v2.model.SeasonResponseBodyDTO;
import org.d11.boot.api.v2.model.SeasonsResponseBodyDTO;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.repository.SeasonRepository;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Season controller tests.
 */
class SeasonControllerV2Tests extends D11BootControllerV2Tests {

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;

    /**
     * Tests SeasonController::getSeasonById.
     */
    @Test
    void testGetSeasonById() {
        final SeasonApi seasonApi = getApi(SeasonApi.class);

        this.seasonRepository.findAll().forEach(season -> {
            final SeasonResponseBodyDTO result = seasonApi.getSeasonById(season.getId());
            assertNotNull(result, "SeasonController::getSeasonById not null");
            assertEquals(getMapper().map(season, SeasonDTO.class), result.getSeason(),
                         "SeasonController::getSeasonById equals");
        });

        assertThrows(FeignException.NotFound.class, () -> seasonApi.getSeasonById(0L),
                     "SeasonController::getSeasonById not found");
    }

    @Test
    void testGetSeasons() {
        final SeasonApi seasonApi = getApi(SeasonApi.class);

        final List<Season> seasons = this.seasonRepository.findByOrderByDateDesc();
        final SeasonsResponseBodyDTO seasonsResponseBodyDTO = seasonApi.getSeasons();

        assertNotNull(seasonsResponseBodyDTO, "SeasonController::getSeasons not null");

        final List<SeasonDTO> result = seasonsResponseBodyDTO.getSeasons();

        assertEquals(seasons.size(), result.size(), "SeasonController::getSeasons size");

        for (int i = 0; i < seasons.size(); ++i) {
            final Season season = seasons.get(i);
            final SeasonDTO seasonDto = result.get(i);

            assertEquals(getMapper().map(season, SeasonDTO.class), seasonDto, "SeasonController::getSeasons equals");
        }
    }

    /**
     * Tests SeasonController::getCurrentSeason.
     */
    @Test
    void testGetCurrentSeason() {
        final SeasonApi seasonApi = getApi(SeasonApi.class);

        final Season currentSeason =
            this.seasonRepository.findFirstByOrderByDateDesc().orElseThrow(NotFoundException::new);

        final SeasonResponseBodyDTO result = seasonApi.getCurrentSeason();
        assertNotNull(result, "SeasonController::getCurrentSeason not null");
        assertEquals(getMapper().map(currentSeason, SeasonDTO.class), result.getSeason(),
                     "SeasonController::getCurrentSeason equals");
    }

}
