package org.d11.boot.interfaces.rest.v2.controller;

import feign.FeignException;
import org.d11.boot.api.v2.client.SeasonApi;
import org.d11.boot.api.v2.model.SeasonDTO;
import org.d11.boot.api.v2.model.SeasonResponseBodyDTO;
import org.d11.boot.api.v2.model.SeasonsResponseBodyDTO;
import org.d11.boot.api.v2.model.StatusDTO;
import org.d11.boot.api.v2.model.UpdateSeasonRequestBodyDTO;
import org.d11.boot.spring.model.Season;
import org.d11.boot.spring.model.Transfer;
import org.d11.boot.spring.repository.SeasonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
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
            this.seasonRepository.findFirstByOrderByDateDesc().orElse(null);

        assertNotNull(currentSeason, "SeasonController::getCurrentSeason currentSeason not null");

        final SeasonResponseBodyDTO result = seasonApi.getCurrentSeason();
        assertNotNull(result, "SeasonController::getCurrentSeason not null");
        assertEquals(getMapper().map(currentSeason, SeasonDTO.class), result.getSeason(),
                     "SeasonController::getCurrentSeason equals");
    }

    /**
     * Tests SeasonController::updateSeason.
     */
    @Test
    void testUpdateSeason() {
        final SeasonApi seasonApi = getApi(SeasonApi.class);

        final Season season = this.seasonRepository.findFirstByOrderByDateDesc().orElse(null);
        assertNotNull(season, "SeasonController::updateSeason season not null");

        final SeasonDTO rollbackSeasonDTO = getMapper().map(season, SeasonDTO.class);

        assertThrows(FeignException.Unauthorized.class,
                     () -> seasonApi.updateSeason(season.getId(),
                                                  new UpdateSeasonRequestBodyDTO().season(rollbackSeasonDTO)),
                     "SeasonController::updateSeason unauthorized throws");

        final SeasonApi userSeasonApi = getUserApi(SeasonApi.class);

        assertThrows(FeignException.Forbidden.class,
                     () -> userSeasonApi.updateSeason(season.getId(),
                                                      new UpdateSeasonRequestBodyDTO().season(rollbackSeasonDTO)),
                     "SeasonController::updateSeason user throws");

        final SeasonApi adminSeasonApi = getAdministratorApi(SeasonApi.class);

        final SeasonDTO seasonDTO = new SeasonDTO()
                .name("1970-1971")
                .d11TeamBudget(Transfer.FEE_DIVISOR)
                .d11TeamMaxTransfers(1)
                .status(StatusDTO.FINISHED)
                .date(LocalDate.parse("1970-01-01"))
                .legacy(true);

        final SeasonDTO result =
                adminSeasonApi.updateSeason(season.getId(),
                                            new UpdateSeasonRequestBodyDTO().season(seasonDTO)).getSeason();

        assertEquals(season.getId(), result.getId(), "SeasonController::updateSeason response id equals");
        assertEquals(seasonDTO.getName(), result.getName(), "SeasonController::updateSeason response name equals");
        assertEquals(seasonDTO.getD11TeamBudget(), result.getD11TeamBudget(),
                     "SeasonController::updateSeason response d11TeamBudget equals");
        assertEquals(seasonDTO.getD11TeamMaxTransfers(), result.getD11TeamMaxTransfers(),
                     "SeasonController::updateSeason d11TeamMaxTransfers response equals");
        assertEquals(seasonDTO.getStatus(), result.getStatus(),
                     "SeasonController::updateSeason response status equals");
        assertEquals(seasonDTO.getDate(), result.getDate(), "SeasonController::updateSeason response date equals");
        assertEquals(seasonDTO.isLegacy(), result.isLegacy(),
                     "SeasonController::updateSeason response legacy equals");

        final Season updatedSeason = this.seasonRepository.findById(season.getId()).orElse(null);

        assertNotNull(updatedSeason, "SeasonController::updateSeason updatedSeason not null");
        assertEquals(season.getId(), updatedSeason.getId(), "SeasonController::updateSeason entity id equals");
        assertEquals(seasonDTO.getName(), updatedSeason.getName(), "SeasonController::updateSeason entity name equals");
        assertEquals(seasonDTO.getD11TeamBudget(), updatedSeason.getD11TeamBudget(),
                     "SeasonController::updateSeason entity d11TeamBudget equals");
        assertEquals(seasonDTO.getD11TeamMaxTransfers(), updatedSeason.getD11TeamMaxTransfers(),
                     "SeasonController::updateSeason d11TeamMaxTransfers entity equals");
        assertEquals(seasonDTO.getStatus().getValue(), updatedSeason.getStatus().getName(),
                     "SeasonController::updateSeason entity status equals");
        assertEquals(seasonDTO.getDate(), updatedSeason.getDate(), "SeasonController::updateSeason entity date equals");
        assertEquals(seasonDTO.isLegacy(), updatedSeason.isLegacy(),
                     "SeasonController::updateSeason entity legacy equals");

        // Rollback the changes just in case
        this.seasonRepository.save(season);
    }

}
