package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamD11MatchWeekStatDTO;
import org.d11.boot.api.service.D11TeamD11MatchWeekStatApiService;
import org.d11.boot.application.model.D11TeamD11MatchWeekStat;
import org.d11.boot.application.repository.D11TeamD11MatchWeekStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 team D11 match week stat API tests.
 */
public class D11TeamD11MatchWeekStatApiTests extends AbstractRepositoryApiTests<D11TeamD11MatchWeekStat, D11TeamD11MatchWeekStatRepository, D11TeamD11MatchWeekStatApiService> {

    /**
     * Tests the findD11TeamD11MatchWeekStatById API operation.
     */
    @Test
    public void findD11TeamD11MatchWeekStatById() {
        for(final D11TeamD11MatchWeekStat d11TeamD11MatchWeekStat : getEntities()) {
            final D11TeamD11MatchWeekStatDTO result = getApiService().findD11TeamD11MatchWeekStatById(d11TeamD11MatchWeekStat.getId());
            final D11TeamD11MatchWeekStatDTO d11TeamD11MatchWeekStatDTO = map(d11TeamD11MatchWeekStat, D11TeamD11MatchWeekStatDTO.class);

            assertNotNull(result, "D11 team D11 match week stat by id should not be null.");
            assertEquals(d11TeamD11MatchWeekStatDTO, result, "D11 team D11 match week stat by id should equal D11TeamMatchWeekStat.");
        }

        assertNull(getApiService().findD11TeamD11MatchWeekStatById(-1L), "D11 team match week stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
