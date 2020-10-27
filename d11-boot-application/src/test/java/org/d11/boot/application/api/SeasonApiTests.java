package org.d11.boot.application.api;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.client.api.SeasonApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Season API tests.
 */
public class SeasonApiTests extends AbstractApiTests<Season> {

    /**
     * Sets up mocked seasons for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getSeasonRepository().findAll());
        Collections.sort(getEntities());
        Collections.reverse(getEntities());
    }

    /**
     * Tests the findSeasonsById API operation.
     */
    @Test
    public void findSeasonById() {
        final SeasonApi seasonApi = new SeasonApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "Seasons should not be empty.");

        for(final Season season : getEntities()) {
            final SeasonDTO result = seasonApi.findSeasonById(season.getId()).block();
            final SeasonDTO seasonDTO = map(season, SeasonDTO.class);
            assertNotNull(result, "Season by id should not be null.");
            assertEquals(seasonDTO, result, "Season by id should equal Season.");
        }

        assertNotFound(seasonApi.findSeasonById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Tests the findAllSeasons API operation.
     */
    @Test
    public void findAllSeasons() {
        final SeasonApi seasonApi = new SeasonApi(getApiClient());
        final List<SeasonDTO> result = seasonApi.findAllSeasons().collectList().block();

        final List<SeasonDTO> seasonDTOs = map(getEntities(), SeasonDTO.class);

        assertNotNull(result, "All seasons should not be null.");
        assertEquals(seasonDTOs, result, "All seasons should equal seasons.");
    }

    /**
     * Tests the findCurrentSeason API operation.
     */
    @Test
    public void findCurrentSeason() {
        final SeasonApi seasonApi = new SeasonApi(getApiClient());
        final SeasonDTO result = seasonApi.findCurrentSeason().block();

        final Season season = getEntities().get(0);
        final SeasonDTO seasonDTO = map(season, SeasonDTO.class);

        assertNotNull(result, "Current season should not be null.");
        assertEquals(seasonDTO, result, "Current season result should equal current season.");
    }

}
