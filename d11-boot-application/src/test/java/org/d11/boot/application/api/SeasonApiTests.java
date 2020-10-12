package org.d11.boot.application.api;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.client.api.SeasonApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Season API tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeasonApiTests extends AbstractApiTests {

    /**
     * Season repository.
     */
    @Autowired
    private SeasonRepository seasonRepository;
    /**
     * List of seasons.
     */
    private List<Season> seasons;

    /**
     * Sets up mocked seasons for the tests to use.
     */
    @BeforeAll
    public void beforeAll() {
        this.seasons = this.seasonRepository.findAll();
        Collections.sort(this.seasons);
        Collections.reverse(this.seasons);
    }

    /**
     * Tests the findSeasonsById API operation.
     */
    @Test
    public void findSeasonById() {
        final SeasonApi seasonApi = new SeasonApi(getApiClient());

        for(final Season season : this.seasons) {
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

        final List<SeasonDTO> seasonDTOs = map(this.seasons, SeasonDTO.class);

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

        final Season season = this.seasons.get(0);
        final SeasonDTO seasonDTO = map(season, SeasonDTO.class);

        assertNotNull(result, "Current season should not be null.");
        assertEquals(seasonDTO, result, "Current season result should equal current season.");
    }

}
