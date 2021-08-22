package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.api.service.D11TeamMatchWeekStatApiService;
import org.d11.boot.application.model.jpa.D11TeamMatchWeekStat;
import org.d11.boot.application.repository.D11TeamMatchWeekStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 team match week stat API tests.
 */
public class D11TeamMatchWeekStatApiTests extends AbstractRepositoryApiTests<D11TeamMatchWeekStat, D11TeamMatchWeekStatRepository, D11TeamMatchWeekStatApiService> {

    /**
     * Tests the findD11TeamMatchWeekStatById API operation.
     */
    @Test
    public void findD11TeamMatchWeekStatById() {
        for(final D11TeamMatchWeekStat d11TeamMatchWeekStat : getEntities()) {
            final D11TeamMatchWeekStatDTO result = getApiService().findD11TeamMatchWeekStatById(d11TeamMatchWeekStat.getId());
            final D11TeamMatchWeekStatDTO d11TeamMatchWeekStatDTO = map(d11TeamMatchWeekStat, D11TeamMatchWeekStatDTO.class);

            assertNotNull(result, "D11 team match week stat by id should not be null.");
            assertEquals(d11TeamMatchWeekStatDTO, result, "D11 team match week stat by id should equal D11TeamMatchWeekStat.");
        }

        assertNull(getApiService().findD11TeamMatchWeekStatById(-1L), "D11 team match week stat not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

}
