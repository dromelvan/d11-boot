package org.d11.boot.application.api;

import feign.FeignException;
import org.d11.boot.api.model.D11TeamMatchWeekStatDTO;
import org.d11.boot.application.model.D11TeamMatchWeekStat;
import org.d11.boot.application.repository.D11TeamMatchWeekStatRepository;
import org.d11.boot.client.api.D11TeamMatchWeekStatApi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * D11 team match week stat API tests.
 */
public class D11TeamMatchWeekStatApiTests extends AbstractRepositoryApiTests<D11TeamMatchWeekStat, D11TeamMatchWeekStatRepository> {

    /**
     * Tests the findD11TeamMatchWeekStatById API operation.
     */
    @Test
    public void findD11TeamMatchWeekStatById() {
        final D11TeamMatchWeekStatApi d11TeamMatchWeekStatApi = getApi(D11TeamMatchWeekStatApi.class);
        for(final D11TeamMatchWeekStat d11TeamMatchWeekStat : getEntities()) {
            final D11TeamMatchWeekStatDTO result = d11TeamMatchWeekStatApi.findD11TeamMatchWeekStatById(d11TeamMatchWeekStat.getId());
            final D11TeamMatchWeekStatDTO d11TeamMatchWeekStatDTO = map(d11TeamMatchWeekStat, D11TeamMatchWeekStatDTO.class);

            assertNotNull(result, "D11 team match week stat by id should not be null.");
            assertEquals(d11TeamMatchWeekStatDTO, result, "D11 team match week stat by id should equal D11TeamMatchWeekStat.");
        }

        assertThrows(FeignException.NotFound.class,
                     () -> d11TeamMatchWeekStatApi.findD11TeamMatchWeekStatById(-1L),
                     "D11 team match week stat not found should throw NotFound exception.");
    }

}
