package org.d11.boot.application.api;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.api.service.D11MatchWeekApiService;
import org.d11.boot.application.model.D11Match;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.repository.D11MatchWeekRepository;
import org.d11.boot.application.util.D11MatchesByDateMapperConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match week API tests.
 */
public class D11MatchWeekApiTests extends AbstractRepositoryApiTests<D11MatchWeek, D11MatchWeekRepository, D11MatchWeekApiService> {

    /**
     * Tests the findD11MatchWeekById API operation.
     */
    @Test
    public void findD11MatchWeekById() {
        // Need to do getRepository().findAll() here since mapping D11MatchWeek requires getD11Matches() and the entities
        // in getEntities() are detached at this point.
        for(final D11MatchWeek d11MatchWeek : getRepository().findAll()) {
            final D11MatchWeekDTO result = getApiService().findD11MatchWeekById(d11MatchWeek.getId());
            final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);
            assertNotNull(result, "D11 match week by id should not be null.");
            assertEquals(d11MatchWeekDTO, result, "D11 match week by id should equal D11MatchDay.");
            assertFalse(result.getD11Matches().isEmpty(), "D11 match week by id should have D11 matches.");
            assertEquals(new D11MatchesByDateMapperConverter().convert(d11MatchWeek.getD11Matches()), result.getD11Matches(),
                    "D11 match week by id D11 matches should equal converted D11MatchWeek matches.");
        }

        assertNull(getApiService().findD11MatchWeekById(-1L), "D11 match week not found should return null.");
        assertBadRequest(get("BAD_REQUEST"));
    }

    /**
     * Tests the findCurrentMatchWeek API operation.
     */
    @Test
    public void findCurrentD11MatchWeek() {
        final D11MatchWeekDTO result = getApiService().findCurrentD11MatchWeek();

        boolean currentD11MatchWeekFound = false;

        for(final D11MatchWeek d11MatchWeek : getRepository().findAll()) {
            if(d11MatchWeek.getDate().equals(LocalDate.now())) {
                currentD11MatchWeekFound = true;
                final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);

                assertNotNull(result, "Current D11 match week should not be null.");
                assertEquals(d11MatchWeekDTO, result, "Current D11 match week result should equal current D11 match week.");
                assertFalse(result.getD11Matches().isEmpty(), "Current D11 match week should have D11 matches.");
                assertEquals(new D11MatchesByDateMapperConverter().convert(d11MatchWeek.getD11Matches()), result.getD11Matches(),
                        "Current D11 match week D11 matches should equal converted D11MatchWeek matches.");
                break;
            }
        }

        assertTrue(currentD11MatchWeekFound, "The test did not complete correctly as no current D11 match week was found.");
    }

    /**
     * Tests D11 match week D11 match order.
     */
    @Test
    public void d11MatchOrder() {
        for(final D11MatchWeek d11MatchWeek : getRepository().findAll()) {
            assertFalse(d11MatchWeek.getD11Matches().isEmpty(), "D11 match week D11 matches should not be empty.");

            final List<D11Match> d11Matches = new ArrayList<>(d11MatchWeek.getD11Matches());
            d11Matches.sort(Comparator.comparing(D11Match::getDate));

            assertEquals(d11Matches, d11MatchWeek.getD11Matches(), "D11 match order should be by date, ascending.");
        }
    }

}
