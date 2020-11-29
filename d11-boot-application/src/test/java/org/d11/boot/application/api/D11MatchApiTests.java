package org.d11.boot.application.api;

import org.d11.boot.api.model.D11MatchDTO;
import org.d11.boot.api.service.D11MatchApiService;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.repository.D11MatchRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * D11 match API tests.
 */
public class D11MatchApiTests extends AbstractApiTests<D11Match, D11MatchRepository, D11MatchApiService> {

    /**
     * Tests the findD11MatchById API operation.
     */
    @Test
    public void findD11MatchById() {
        for(final D11Match d11Match : getEntities()) {
            final D11MatchDTO result = getApiService().findD11MatchById(d11Match.getId());
            final D11MatchDTO d11MatchDTO = map(d11Match, D11MatchDTO.class);

            assertNotNull(result, "D11 Match by id should not be null.");
            assertEquals(d11MatchDTO, result, "D11 Match by id should equal D11 Match.");
        }

        assertNull(getApiService().findD11MatchById(-1L), "D11 match not found should return null.");
        assertBadRequest(get("d11-matches", "BAD_REQUEST"));
    }

}
