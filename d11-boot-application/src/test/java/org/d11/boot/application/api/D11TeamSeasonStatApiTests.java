package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.D11TeamSeasonStatDTO;
import org.d11.boot.application.model.D11TeamSeasonStat;
import org.d11.boot.application.model.Season;
import org.d11.boot.application.model.TableStat;
import org.d11.boot.application.repository.D11TeamSeasonStatRepository;
import org.d11.boot.client.api.D11TeamSeasonStatApi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * D11 team season stat API tests.
 */
public class D11TeamSeasonStatApiTests extends AbstractRepositoryApiTests<D11TeamSeasonStat, D11TeamSeasonStatRepository> {

    /**
     * Tests the findD11TeamSeasonStatById API operation.
     */
    @Test
    public void findD11TeamSeasonStatById() {
        final D11TeamSeasonStatApi d11TeamSeasonStatApi = getApi(D11TeamSeasonStatApi.class);
        for(final D11TeamSeasonStat d11TeamSeasonStat : getEntities()) {
            final D11TeamSeasonStatDTO result = d11TeamSeasonStatApi.findD11TeamSeasonStatById(d11TeamSeasonStat.getId());
            final D11TeamSeasonStatDTO d11TeamSeasonStatDTO = map(d11TeamSeasonStat, D11TeamSeasonStatDTO.class);

            assertNotNull(result, "D11 team season stat by id should not be null.");
            assertEquals(d11TeamSeasonStatDTO, result, "D11 team season stat by id should equal D11TeamSeasonStat.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> d11TeamSeasonStatApi.findD11TeamSeasonStatById(-1L),
                     "D11 team season stat not found should throw NotFound exception.");
    }

    /**
     * Tests the findD11TeamSeasonStatBySeasonId API operation.
     */
    @Test
    public void findD11TeamSeasonStatBySeasonId() {
        final Map<Season, List<D11TeamSeasonStat>> d11TeamSeasonStatMap = new HashMap<>();
        for(final D11TeamSeasonStat d11TeamSeasonStat : getEntities()) {
            final List<D11TeamSeasonStat> d11TeamSeasonStats =
                    d11TeamSeasonStatMap.computeIfAbsent(d11TeamSeasonStat.getSeason(), season -> new ArrayList<>());
            d11TeamSeasonStats.add(d11TeamSeasonStat);
        }

        final D11TeamSeasonStatApi d11TeamSeasonStatApi = getApi(D11TeamSeasonStatApi.class);
        for(final Map.Entry<Season, List<D11TeamSeasonStat>> entry : d11TeamSeasonStatMap.entrySet()) {
            final List<D11TeamSeasonStat> seasonD11TeamSeasonStats = entry.getValue();

            seasonD11TeamSeasonStats.sort(Comparator.comparingInt(TableStat::getRanking));

            final List<D11TeamSeasonStatDTO> d11TeamSeasonStats = d11TeamSeasonStatApi.findD11TeamSeasonStatBySeasonId(entry.getKey().getId());

            assertEquals(seasonD11TeamSeasonStats.size(), d11TeamSeasonStats.size(),
                    "Season D11 team season stats and D11 team season stats sizes are not equal. " +
                    "This means the D11 team season stat test data is not set up properly.");

            assertEquals(map(seasonD11TeamSeasonStats, D11TeamSeasonStatDTO.class), d11TeamSeasonStats,
                    "Season D11 team season stats should equal D11 team season stats.");
        }
    }

    /**
     * Tests the findD11TeamSeasonStatByD11TeamIdAndSeasonId API operation.
     */
    @Test
    public void findD11TeamSeasonStatByD11TeamIdAndSeasonId() {
        final D11TeamSeasonStatApi d11TeamSeasonStatApi = getApi(D11TeamSeasonStatApi.class);
        for(final D11TeamSeasonStat d11TeamSeasonStat : getEntities()) {
            final D11TeamSeasonStatDTO d11TeamSeasonStatDTO = d11TeamSeasonStatApi.findD11TeamSeasonStatByD11TeamIdAndSeasonId(
                    d11TeamSeasonStat.getD11Team().getId(),
                    d11TeamSeasonStat.getSeason().getId()
            );

            assertNotNull(d11TeamSeasonStatDTO, "D11 team season stat DTO should not be null.");
            assertEquals(map(d11TeamSeasonStat, D11TeamSeasonStatDTO.class), d11TeamSeasonStatDTO,
                    "D11 team season stat DTO should equal D11 team season stat.");
        }
    }

}
