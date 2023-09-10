package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.d11.boot.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match tests.
 */
class MatchTests extends EasyRandomTests {

    /**
     * Tests Match::isValid.
     */
    @Test
    @SuppressWarnings({"DataFlowIssue", "checkstyle:ExecutableStatementCount"})
    void testIsValid() {
        final Match match = generate(Match.class);

        assertTrue(match.isValid(), "Match::isValid");

        match.setWhoscoredId(-1);
        assertFalse(match.isValid(), "Match::isValid WhoScored id negative");
        match.setWhoscoredId(1);

        match.setDatetime(null);
        assertFalse(match.isValid(), "Match::isValid datetime null");
        match.setDatetime(LocalDateTime.now());

        match.setHomeTeamGoals(-1);
        assertFalse(match.isValid(), "Match::isValid home team goals negative");
        match.setHomeTeamGoals(1);

        match.setAwayTeamGoals(-1);
        assertFalse(match.isValid(), "Match::isValid away team goals negative");
        match.setAwayTeamGoals(1);

        match.setPreviousHomeTeamGoals(-1);
        assertFalse(match.isValid(), "Match::isValid previous home team goals negative");
        match.setPreviousHomeTeamGoals(1);

        match.setPreviousAwayTeamGoals(-1);
        assertFalse(match.isValid(), "Match::isValid previous away team goals negative");
        match.setPreviousAwayTeamGoals(1);

        match.setElapsed(null);
        assertFalse(match.isValid(), "Match::isValid elapsed null");
        match.setElapsed("");
        assertFalse(match.isValid(), "Match::isValid elapsed empty");
        match.setElapsed("ABCDEFGHIJKL");
        assertFalse(match.isValid(), "Match::isValid elapsed too long");
        match.setElapsed("A");

        match.setStatus(null);
        assertFalse(match.isValid(), "Match::isValid status null");
        match.setStatus(Status.PENDING);

        match.setHomeTeam(null);
        assertFalse(match.isValid(), "Match::isValid home team null");
        match.setHomeTeam(new Team());

        match.setAwayTeam(null);
        assertFalse(match.isValid(), "Match::isValid away team null");
        match.setAwayTeam(new Team());

        match.setMatchWeek(null);
        assertFalse(match.isValid(), "Match::isValid match week null");
        match.setMatchWeek(new MatchWeek());

        match.setStadium(null);
        assertFalse(match.isValid(), "Match::isValid stadium null");
        match.setStadium(new Stadium());

        assertTrue(match.isValid(), "Match::isValid valid");
    }

    /**
     * Tests Match::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final Match match = generate(Match.class);

        assertTrue(match.isParticipant(match.getHomeTeam()), "Match::isParticipant home team");
        assertTrue(match.isParticipant(match.getAwayTeam()), "Match::isParticipant away team");

        final Team team = new Team();
        team.setId(-1L);

        assertFalse(match.isParticipant(team), "Match::isParticipant non participant team");
    }

    /**
     * Tests Match::isStarted.
     */
    @Test
    void testIsStarted() {
        final Match match = generate(Match.class);

        match.setStatus(Status.PENDING);
        assertFalse(match.isStarted(), "Match::isStarted status PENDING");

        match.setStatus(Status.POSTPONED);
        assertFalse(match.isStarted(), "Match::isStarted status POSTPONED");

        match.setStatus(Status.ACTIVE);
        assertTrue(match.isStarted(), "Match::isStarted status ACTIVE");

        match.setStatus(Status.FULL_TIME);
        assertTrue(match.isStarted(), "Match::isStarted status FULL_TIME");

        match.setStatus(Status.FINISHED);
        assertTrue(match.isStarted(), "Match::isStarted status FINISHED");
    }

    /**
     * Tests Match::isWinner.
     */
    @Test
    void testIsWinner() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(0);

        assertTrue(match.isWinner(match.getHomeTeam()), "Match::isWinner home team winner");
        assertFalse(match.isWinner(match.getAwayTeam()), "Match::isWinner away team not winner");

        match.setHomeTeamGoals(0);

        assertFalse(match.isWinner(match.getHomeTeam()), "Match::isWinner home team draw");
        assertFalse(match.isWinner(match.getAwayTeam()), "Match::isWinner away team draw");

        match.setAwayTeamGoals(1);

        assertFalse(match.isWinner(match.getHomeTeam()), "Match::isWinner home team not winner");
        assertTrue(match.isWinner(match.getAwayTeam()), "Match::isWinner away team winner");
    }

    /**
     * Tests Match::isLoser.
     */
    @Test
    void testIsLoser() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(0);
        match.setAwayTeamGoals(1);

        assertTrue(match.isLoser(match.getHomeTeam()), "Match::isLoser home team loser");
        assertFalse(match.isLoser(match.getAwayTeam()), "Match::isLoser away team not loser");

        match.setAwayTeamGoals(0);

        assertFalse(match.isLoser(match.getHomeTeam()), "Match::isLoser home team draw");
        assertFalse(match.isLoser(match.getAwayTeam()), "Match::isLoser away team draw");

        match.setHomeTeamGoals(1);

        assertFalse(match.isLoser(match.getHomeTeam()), "Match::isLoser home team not winner");
        assertTrue(match.isLoser(match.getAwayTeam()), "Match::isLoser away team winner");
    }

    /**
     * Tests Match::isDraw.
     */
    @Test
    void testIsDraw() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(0);

        assertFalse(match.isDraw(), "Match::isDraw home team winner");

        match.setHomeTeamGoals(0);

        assertTrue(match.isDraw(), "Match::isDraw draw");

        match.setAwayTeamGoals(1);

        assertFalse(match.isDraw(), "Match::isDraw away team winner");
    }

    /**
     * Tests Match::getGoalsFor.
     */
    @Test
    void testGetGoalsFor() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(0);

        assertEquals(match.getHomeTeamGoals(), match.getGoalsFor(match.getHomeTeam()), "Match::getGoalsFor home team");
        assertEquals(match.getAwayTeamGoals(), match.getGoalsFor(match.getAwayTeam()), "Match::getGoalsFor away team");
    }

    /**
     * Tests Match::getGoalsAgainst.
     */
    @Test
    void testGetGoalsAgainst() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(0);

        assertEquals(match.getAwayTeamGoals(), match.getGoalsAgainst(match.getHomeTeam()),
                     "Match::getGoalsAgainst home team");
        assertEquals(match.getHomeTeamGoals(), match.getGoalsAgainst(match.getAwayTeam()),
                     "Match::getGoalsAgainst away team");
    }

    /**
     * Tests Match::getPoints.
     */
    @Test
    void testGetPoints() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(0);

        assertEquals(Match.WIN_POINTS, match.getPoints(match.getHomeTeam()), "Match::getPoints home team winner");
        assertEquals(Match.LOSS_POINTS, match.getPoints(match.getAwayTeam()), "Match::getPoints away team loser");

        match.setHomeTeamGoals(0);

        assertEquals(Match.DRAW_POINTS, match.getPoints(match.getHomeTeam()), "Match::getPoints home team draw");
        assertEquals(Match.DRAW_POINTS, match.getPoints(match.getAwayTeam()), "Match::getPoints away team draw");

        match.setAwayTeamGoals(1);

        assertEquals(Match.LOSS_POINTS, match.getPoints(match.getHomeTeam()), "Match::getPoints home team loser");
        assertEquals(Match.WIN_POINTS, match.getPoints(match.getAwayTeam()), "Match::getPoints away team winner");
    }

    /**
     * Tests Match::getTeamByWhoscoredId.
     */
    @Test
    void testGetTeamByWhoscoredId() {
        final Match match = generate(Match.class);

        match.getHomeTeam().setWhoscoredId(1);
        match.getAwayTeam().setWhoscoredId(0);

        assertEquals(match.getHomeTeam(), match.getTeamByWhoscoredId(match.getHomeTeam().getWhoscoredId()),
                     "Match::GetTeamByWhoscoredId home team");
        assertEquals(match.getAwayTeam(), match.getTeamByWhoscoredId(match.getAwayTeam().getWhoscoredId()),
                     "Match::GetTeamByWhoscoredId away team");

        assertThrows(NotFoundException.class, () -> match.getTeamByWhoscoredId(-1),
                     "Match::GetTeamByWhoscoredId not found");
    }

    /**
     * Tests Match::reset.
     */
    @Test
    void testReset() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoals(1);
        match.setAwayTeamGoals(1);
        match.setPreviousHomeTeamGoals(1);
        match.setPreviousAwayTeamGoals(1);

        match.reset();

        assertEquals(0, match.getHomeTeamGoals(), "Match::reset home team goals equals");
        assertEquals(0, match.getAwayTeamGoals(), "Match::reset away team goals equals");
        assertEquals(1, match.getPreviousHomeTeamGoals(), "Match::reset previous home team goals equals");
        assertEquals(1, match.getPreviousAwayTeamGoals(), "Match::reset previous away team goals equals");
    }

}
