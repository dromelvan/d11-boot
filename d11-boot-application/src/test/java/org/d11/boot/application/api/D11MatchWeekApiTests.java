package org.d11.boot.application.api;

import org.d11.boot.api.model.D11MatchWeekDTO;
import org.d11.boot.application.model.D11MatchWeek;
import org.d11.boot.application.repository.D11MatchWeekRepository;
import org.d11.boot.client.api.D11MatchWeekApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 match week API tests.
 */
public class D11MatchWeekApiTests extends AbstractApiTests<D11MatchWeek, D11MatchWeekRepository> {

    /**
     * Sets up D11 match weeks for the tests to use.
     */
    @Override
    @BeforeAll
    public void beforeAll() {
        final List<D11MatchWeek> d11MatchWeeks = getRepository().findAll();
        // Start with yesterday and set the date of each d11 match week one week forward from the previous.
        // This means the 'current' d11 match week should be the second one.
        LocalDate localDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
        for(final D11MatchWeek d11MatchWeek : d11MatchWeeks) {
            d11MatchWeek.setDate(localDate);
            localDate = localDate.plus(1, ChronoUnit.DAYS);
        }
        getEntities().addAll(getRepository().saveAll(d11MatchWeeks));
    }

    /**
     * Tests the findD11MatchWeekById API operation.
     */
    @Test
    public void findD11MatchWeekById() {
        final D11MatchWeekApi d11MatchWeekApi = new D11MatchWeekApi(getApiClient());

        assertFalse(getEntities().isEmpty(), "D11 match weeks should not be empty.");

        for(final D11MatchWeek d11MatchWeek : getEntities()) {
            final D11MatchWeekDTO result = d11MatchWeekApi.findD11MatchWeekById(d11MatchWeek.getId()).block();
            final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);
            assertNotNull(result, "D11 match week by id should not be null.");
            assertEquals(d11MatchWeekDTO, result, "D11 match week by id should equal D11MatchDay.");
        }

        assertNotFound(d11MatchWeekApi.findD11MatchWeekById(-1L));
        assertBadRequest(getMono("BAD_REQUEST"));
    }

    /**
     * Tests the findCurrentMatchWeek API operation.
     */
    @Test
    public void findCurrentD11MatchWeek() {
        final D11MatchWeekApi d11MatchWeekApi = new D11MatchWeekApi(getApiClient());
        final D11MatchWeekDTO result = d11MatchWeekApi.findCurrentD11MatchWeek().block();

        boolean currentD11MatchWeekFound = false;

        for(final D11MatchWeek d11MatchWeek : getEntities()) {
            if(d11MatchWeek.getDate().equals(LocalDate.now())) {
                currentD11MatchWeekFound = true;
                final D11MatchWeekDTO d11MatchWeekDTO = map(d11MatchWeek, D11MatchWeekDTO.class);

                assertNotNull(result, "Current D11 match week should not be null.");
                assertEquals(d11MatchWeekDTO, result, "Current D11 match week result should equal current D11 match week.");
                break;
            }
        }

        assertTrue(currentD11MatchWeekFound, "The test did not complete correctly as no current "
                                             + " D11 match week was found.");
    }

}
