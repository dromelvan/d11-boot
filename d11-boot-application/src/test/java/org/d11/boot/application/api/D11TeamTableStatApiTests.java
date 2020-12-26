package org.d11.boot.application.api;

import org.d11.boot.api.model.D11TeamTableStatDTO;
import org.d11.boot.api.service.D11TeamTableStatApiService;
import org.d11.boot.application.model.D11TeamTableStat;
import org.d11.boot.application.repository.D11TeamTableStatRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 team table stat API tests.
 */
public class D11TeamTableStatApiTests extends AbstractApiTests<D11TeamTableStat, D11TeamTableStatRepository, D11TeamTableStatApiService> {

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

}
