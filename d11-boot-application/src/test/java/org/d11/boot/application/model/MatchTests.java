package org.d11.boot.application.model;

import org.d11.boot.api.model.MatchDTO;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match tests.
 */
public class MatchTests extends D11EasyRandomTests {

    /**
     * Tests match validity.
     */
    @Test
    public void isValid() {
        final Match match = generate(Match.class);

        assertTrue(match.isValid(), "New match should be valid.");

        match.setWhoscoredId(-1);
        assertFalse(match.isValid(), "Negative whoscoredId should not be valid.");
        match.setWhoscoredId(1);

        match.setDatetime(null);
        assertFalse(match.isValid(), "Null datetime should not be valid.");
        match.setDatetime(LocalDateTime.now());

        match.setHomeTeamGoals(-1);
        assertFalse(match.isValid(), "Negative home team goals should not be valid.");
        match.setHomeTeamGoals(1);

        match.setAwayTeamGoals(-1);
        assertFalse(match.isValid(), "Negative away team goals should not be valid.");
        match.setAwayTeamGoals(1);

        match.setPreviousHomeTeamGoals(-1);
        assertFalse(match.isValid(), "Negative previous home team goals should not be valid.");
        match.setPreviousHomeTeamGoals(1);

        match.setPreviousAwayTeamGoals(-1);
        assertFalse(match.isValid(), "Negative previous away team goals should not be valid.");
        match.setPreviousAwayTeamGoals(1);

        match.setElapsed(null);
        assertFalse(match.isValid(), "Null elapsed should not be valid.");
        match.setElapsed("");
        assertFalse(match.isValid(), "Too short elapsed should not be valid.");
        match.setElapsed("ABCDEFGHIJKL");
        assertFalse(match.isValid(), "Too long elapsed should not be valid.");
        match.setElapsed("A");

        match.setStatus(null);
        assertFalse(match.isValid(), "Null status should not be valid.");
        match.setStatus(Status.PENDING);

        assertTrue(match.isValid(), "Match should be valid.");
    }

    /**
     * Tests mapping between Match and MatchDTO.
     */
    @Test
    public void map() {
        final Match match = generate(Match.class);

        final MatchDTO matchDTO = map(match, MatchDTO.class);
        final Match mappedMatch = map(matchDTO, Match.class);

        assertEquals(match, mappedMatch, "Match should equal mapped match.");
    }

}
