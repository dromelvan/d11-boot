package org.d11.boot.application.model;

import org.d11.boot.api.model.D11MatchDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 Match tests.
 */
public class D11MatchTests extends D11EasyRandomTests {

    /**
     * Tests D11 match validity.
     */
    @Test
    public void isValid() {
        final D11Match d11Match = generate(D11Match.class);

        assertTrue(d11Match.isValid(), "New D11 match should be valid.");

        d11Match.setDatetime(null);
        assertFalse(d11Match.isValid(), "Null datetime should not be valid.");
        d11Match.setDatetime(LocalDateTime.now());

        d11Match.setHomeTeamGoals(-1);
        assertFalse(d11Match.isValid(), "Negative home team goals should not be valid.");
        d11Match.setHomeTeamGoals(1);

        d11Match.setAwayTeamGoals(-1);
        assertFalse(d11Match.isValid(), "Negative away team goals should not be valid.");
        d11Match.setAwayTeamGoals(1);

        d11Match.setPreviousHomeTeamGoals(-1);
        assertFalse(d11Match.isValid(), "Negative previous home team goals should not be valid.");
        d11Match.setPreviousHomeTeamGoals(1);

        d11Match.setPreviousAwayTeamGoals(-1);
        assertFalse(d11Match.isValid(), "Negative previous away team goals should not be valid.");
        d11Match.setPreviousAwayTeamGoals(1);

        d11Match.setElapsed(null);
        assertFalse(d11Match.isValid(), "Null elapsed should not be valid.");
        d11Match.setElapsed("");
        assertFalse(d11Match.isValid(), "Too short elapsed should not be valid.");
        d11Match.setElapsed("ABCDEFGHIJKL");
        assertFalse(d11Match.isValid(), "Too long elapsed should not be valid.");
        d11Match.setElapsed("A");

        d11Match.setStatus(null);
        assertFalse(d11Match.isValid(), "Null status should not be valid.");
        d11Match.setStatus(Status.PENDING);

        assertTrue(d11Match.isValid(), "D11 match should be valid.");
    }

    /**
     * Tests mapping between D11Match and D11MatchDTO.
     */
    @Test
    public void map() {
        final D11Match d11Match = generate(D11Match.class);

        final D11MatchDTO d11MatchDTO = map(d11Match, D11MatchDTO.class);
        final D11Match mappedMatch = map(d11MatchDTO, D11Match.class);

        assertEquals(d11Match, mappedMatch, "D11Match should equal mapped D11 match.");
    }

}
