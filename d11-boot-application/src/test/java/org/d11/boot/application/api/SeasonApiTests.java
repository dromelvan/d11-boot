package org.d11.boot.application.api;

import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.application.mock.SeasonRandomParameters;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.repository.SeasonRepository;
import org.d11.boot.client.api.SeasonApi;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        this.seasons = new EasyRandom(new SeasonRandomParameters())
                                    .objects(Season.class, 2)
                                    .collect(Collectors.toList());
        this.seasons.get(0).setDate(LocalDate.now().minus(1, ChronoUnit.YEARS));
        this.seasons.get(1).setDate(LocalDate.now());
        this.seasons = this.seasonRepository.saveAll(this.seasons);
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

        WebClientResponseException webClientResponseException = assertThrows(WebClientResponseException.class, () -> {
            seasonApi.findSeasonById(-1L).block();
        });
        assertEquals(HttpStatus.NOT_FOUND,
                     webClientResponseException.getStatusCode(),
                     "Response should have status NOT_FOUND.");

        webClientResponseException = assertThrows(WebClientResponseException.class, () -> {
            getUri("BAD_REQUEST");
        });
        assertEquals(HttpStatus.BAD_REQUEST,
                     webClientResponseException.getStatusCode(),
                     "Response should have status BAD_REQUEST.");
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
