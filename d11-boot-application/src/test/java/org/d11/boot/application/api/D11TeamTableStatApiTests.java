package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.api.service.D11TeamTableStatApiService;
import org.d11.boot.application.model.D11League;
import org.d11.boot.application.model.D11TeamTableStat;
import org.d11.boot.application.repository.D11TeamTableStatRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 team table stat API tests.
 */
public class D11TeamTableStatApiTests extends AbstractRepositoryApiTests<D11TeamTableStat, D11TeamTableStatRepository, D11TeamTableStatApiService> {

    /**
     * Tests the findD11TeamTableStatById API operation.
     */
    @Test
    public void findD11TeamTableStatById() {
        for(final D11TeamTableStat d11TeamTableStat : getEntities()) {
            final D11TeamTableStatDTO result = getApiService().findD11TeamTableStatById(d11TeamTableStat.getId());
            final D11TeamTableStatDTO d11TeamTableStatDTO = map(d11TeamTableStat, D11TeamTableStatDTO.class);

            assertNotNull(result, "D11 team table stat by id should not be null.");
            assertEquals(d11TeamTableStatDTO, result, "D11 team table stat by id should equal D11TeamTableStat.");
        }

        assertNull(getApiService().findD11TeamTableStatById(-1L), "D11 team table stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findTeamTableStatByD11LeagueId API operation.
     */
    @Test
    public void findD11TeamTableStatByD11LeagueId() {
        final Map<D11League, List<D11TeamTableStat>> d11TeamTableStatMap = new HashMap<>();
        for(final D11TeamTableStat d11TeamTableStat : getEntities()) {
            final List<D11TeamTableStat> teamTableStats =
                    d11TeamTableStatMap.computeIfAbsent(d11TeamTableStat.getD11League(), d11League -> new ArrayList<>());
            teamTableStats.add(d11TeamTableStat);
        }

        for(final Map.Entry<D11League, List<D11TeamTableStat>> entry : d11TeamTableStatMap.entrySet()) {
            final List<D11TeamTableStat> d11LeagueTeamTableStats = entry.getValue();

            d11LeagueTeamTableStats.sort((d11TeamTableStat1, d11TeamTableStat2) -> {
                int compare = (int) (d11TeamTableStat2.getD11MatchWeek().getId() - d11TeamTableStat1.getD11MatchWeek().getId());
                if(compare == 0) {
                    compare = d11TeamTableStat1.getRanking() - d11TeamTableStat2.getRanking();
                }
                return compare;
            });

            final List<D11TeamTableStatDTO> d11TeamTableStats = getApiService().findD11TeamTableStatByD11LeagueId(entry.getKey().getId());

            assertEquals(d11LeagueTeamTableStats.size(), d11TeamTableStats.size(),
                    "D11 league D11 team table stats and D11 team table stats sizes are not equal. " +
                    "This means the D11 team table stat test data is not set up properly.");

            assertEquals(map(d11LeagueTeamTableStats, D11TeamTableStatDTO.class), d11TeamTableStats,
                    "D11 league D11 team table stats should equal D11 team table stats.");
        }
    }

}
