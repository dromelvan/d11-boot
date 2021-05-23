package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamSeasonStatStubDTO;
import org.d11.boot.api.model.PlayerSeasonStatStubDTO;
import org.d11.boot.api.model.SeasonDTO;
import org.d11.boot.api.model.SeasonSummaryDTO;
import org.d11.boot.api.model.TeamSeasonStatStubDTO;
import org.d11.boot.api.service.SeasonApiService;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.model.PlayerSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TeamSeasonStat;
import org.d11.boot.application.model.projection.EntityId;
import org.d11.boot.application.repository.SeasonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        getEntities().addAll(getRepository().findAll());
        Collections.sort(getEntities());
        assertFalse(getEntities().isEmpty(), "Seasons should not be empty.");
    }

    /**
     * Tests the findSeasonById API operation.
     */
    @Test
    public void findSeasonById() {
        for(final Season season : getRepository().findAll()) {
            final SeasonDTO result = getApiService().findSeasonById(season.getId());
            final SeasonDTO seasonDTO = map(season, SeasonDTO.class);
            assertNotNull(result, "Season by id should not be null.");
            assertEquals(seasonDTO, result, "Season by id should equal Season.");
        }

        assertNull(getApiService().findSeasonById(-1L), "Season not found should return null.");
        assertBadRequest(get("BAD_SEASON_REQUEST"));
    }

    /**
     * Tests the findSeasonSummaryById API operation.
     */
    @Test
    public void findSeasonSummaryById() {
        for(final Season season : getRepository().findAll()) {
            final SeasonSummaryDTO result = getApiService().findSeasonSummaryById(season.getId());
            final SeasonSummaryDTO seasonSummaryDTO = map(season, SeasonSummaryDTO.class);
            assertNotNull(result, "Season summary by id should not be null.");
            assertEquals(seasonSummaryDTO, result, "Season summary by id should equal Season summary.");

            assertNotNull(result.getTop3PlayerSeasonStats(), "Season summary player season stats should not be null.");
            assertFalse(result.getTop3PlayerSeasonStats().isEmpty(),
                    "Season summary player season stats should not be empty.");
            final List<PlayerSeasonStat> playerSeasonStats = season.getTop3PlayerSeasonStats();
            assertEquals(map(playerSeasonStats, PlayerSeasonStatStubDTO.class), result.getTop3PlayerSeasonStats(),
                    "Season summary by id top 3 player season stats should equal season top 3 player season stats");

            assertNotNull(result.getTop3TeamSeasonStats(), "Season summary team season stats should not be null.");
            assertFalse(result.getTop3TeamSeasonStats().isEmpty(),
                    "Season summary team season stats should not be empty.");
            final List<TeamSeasonStat> teamSeasonStats = season.getTop3TeamSeasonStats();
            assertEquals(map(teamSeasonStats, TeamSeasonStatStubDTO.class), result.getTop3TeamSeasonStats(),
                    "Season summary by id top 3 team season stats should equal season top 3 team season stats");

            assertNotNull(result.getTop3D11TeamSeasonStats(), "Season summary D11 team season stats should not be null.");
            assertFalse(result.getTop3D11TeamSeasonStats().isEmpty(),
                    "Season summary D11 team season stats should not be empty.");
            final List<D11TeamSeasonStat> d11TeamSeasonStats = season.getTop3D11TeamSeasonStats();
            assertEquals(map(d11TeamSeasonStats, D11TeamSeasonStatStubDTO.class), result.getTop3D11TeamSeasonStats(),
                    "Season summary by id top 3 D11 team season stats should equal season top 3 D11 team season stats");
        }
        assertNull(getApiService().findSeasonSummaryById(-1L), "Season summary not found should return null.");
        assertBadRequest(get("BAD_SEASON_SUMMARY_REQUEST"));
    }

    /**
     * Tests the findAllSeasons API operation.
     */
    @Test
    public void findAllSeasons() {
        final List<Long> ids = getApiService().findAllSeasons();
        final List<SeasonDTO> result = new ArrayList<>();

        ids.forEach(id -> result.add(getApiService().findSeasonById(id)));

        final List<Season> seasons = new ArrayList<>();
        for(final EntityId entityId : getRepository().findByOrderByDateDesc()) {
            final Optional<Season> optional = getRepository().findById(entityId.getId());
            optional.ifPresent(seasons::add);
        }
        final List<SeasonDTO> seasonDTOs = map(seasons, SeasonDTO.class);

        assertNotNull(result, "All seasons should not be null.");
        assertEquals(seasonDTOs, result, "All seasons should equal seasons.");
    }

    /**
     * Tests the findCurrentSeason API operation.
     */
    @Test
    public void findCurrentSeason() {
        final SeasonDTO result = getApiService().findCurrentSeason();

        final EntityId entityId = getRepository().findByOrderByDateDesc().get(0);
        final Optional<Season> optional = getRepository().findById(entityId.getId());

        assertTrue(optional.isPresent(), "Season from entity id should be present.");
        final SeasonDTO seasonDTO = map(optional.get(), SeasonDTO.class);

        assertNotNull(result, "Current season should not be null.");
        assertEquals(seasonDTO, result, "Current season result should equal current season.");
    }

}
