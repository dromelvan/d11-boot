package org.d11.boot.application.api;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.api.service.MatchApiService;
import org.d11.boot.application.model.Match;
import org.d11.boot.application.repository.MatchRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Match API tests.
 */
public class MatchApiTests extends AbstractApiTests<Match, MatchRepository, MatchApiService> {

    /**
     * Tests the findMatchById API operation.
     */
    @Test
    public void findMatchById() {
        for(final Match match : getEntities()) {
            final MatchDTO result = getApiService().findMatchById(match.getId());
            final MatchDTO matchDTO = map(match, MatchDTO.class);

            assertNotNull(result, "Match by id should not be null.");
            assertEquals(matchDTO, result, "Match by id should equal Match.");
        }

        assertNull(getApiService().findMatchById(-1L), "Match not found should return null.");
        assertBadRequest(get("matches", "BAD_REQUEST"));
    }

}
