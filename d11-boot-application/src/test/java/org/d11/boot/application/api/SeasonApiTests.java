package org.d11.boot.application.api;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.api.service.SeasonApiService;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Season API tests.
 */
public class SeasonApiTests extends AbstractRepositoryApiTests<Season, SeasonRepository, SeasonApiService> {

    /**
     * Sets up seasons for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        getEntities().addAll(getRepository().findByOrderByDateDesc());
        assertFalse(getEntities().isEmpty(), "Seasons should not be empty.");
    }

    /**
     * Tests the findSeasonsById API operation.
     */
    @Test
    public void findSeasonById() {
        for(final Season season : getEntities()) {
            final SeasonDTO result = getApiService().findSeasonById(season.getId());
            final SeasonDTO seasonDTO = map(season, SeasonDTO.class);
            assertNotNull(result, "Season by id should not be null.");
            assertEquals(seasonDTO, result, "Season by id should equal Season.");
        }

        assertNull(getApiService().findSeasonById(-1L), "Season not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findAllSeasons API operation.
     */
    @Test
    public void findAllSeasons() {
        final List<SeasonDTO> result = getApiService().findAllSeasons();

        final List<SeasonDTO> seasonDTOs = map(getEntities(), SeasonDTO.class);

        assertNotNull(result, "All seasons should not be null.");
        assertEquals(seasonDTOs, result, "All seasons should equal seasons.");
    }

    /**
     * Tests the findCurrentSeason API operation.
     */
    @Test
    public void findCurrentSeason() {
        final SeasonDTO result = getApiService().findCurrentSeason();

        final Season season = getEntities().get(0);
        final SeasonDTO seasonDTO = map(season, SeasonDTO.class);

        assertNotNull(result, "Current season should not be null.");
        assertEquals(seasonDTO, result, "Current season result should equal current season.");
    }

}
