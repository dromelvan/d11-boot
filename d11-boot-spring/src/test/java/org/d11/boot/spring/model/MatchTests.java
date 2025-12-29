package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Match tests.
 */
class MatchTests extends EasyRandomTests {

    /**
     * Tests Match::isValid.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testIsValid() {
        final Match match = generate(Match.class);

        assertTrue(match.isValid());

        match.setStatSourceId(-1);
        assertFalse(match.isValid());
        match.setStatSourceId(1);

        match.setDatetime(null);
        assertFalse(match.isValid());
        match.setDatetime(LocalDateTime.now());

        match.setHomeTeamGoalsScored(-1);
        assertFalse(match.isValid());
        match.setHomeTeamGoalsScored(1);

        match.setAwayTeamGoalsScored(-1);
        assertFalse(match.isValid());
        match.setAwayTeamGoalsScored(1);

        match.setPreviousHomeTeamGoalsScored(-1);
        assertFalse(match.isValid());
        match.setPreviousHomeTeamGoalsScored(1);

        match.setPreviousAwayTeamGoalsScored(-1);
        assertFalse(match.isValid());
        match.setPreviousAwayTeamGoalsScored(1);

        match.setElapsed(null);
        assertFalse(match.isValid());
        match.setElapsed("");
        assertFalse(match.isValid());
        match.setElapsed("ABCDEFGHIJKL");
        assertFalse(match.isValid());
        match.setElapsed("A");

        match.setStatus(null);
        assertFalse(match.isValid());
        match.setStatus(Status.PENDING);

        match.setHomeTeam(null);
        assertFalse(match.isValid());
        match.setHomeTeam(new Team());

        match.setAwayTeam(null);
        assertFalse(match.isValid());
        match.setAwayTeam(new Team());

        match.setMatchWeek(null);
        assertFalse(match.isValid());
        match.setMatchWeek(new MatchWeek());

        match.setStadium(null);
        assertFalse(match.isValid());
        match.setStadium(new Stadium());

        assertTrue(match.isValid());
    }

    /**
     * Tests Match::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final Match match = generate(Match.class);

        assertTrue(match.isParticipant(match.getHomeTeam()));
        assertTrue(match.isParticipant(match.getAwayTeam()));

        final Team team = new Team();
        team.setId(-1L);

        assertFalse(match.isParticipant(team));
    }

    /**
     * Tests Match::isStarted.
     */
    @Test
    void testIsStarted() {
        final Match match = generate(Match.class);

        match.setStatus(Status.PENDING);
        assertFalse(match.isStarted());

        match.setStatus(Status.POSTPONED);
        assertFalse(match.isStarted());

        match.setStatus(Status.ACTIVE);
        assertTrue(match.isStarted());

        match.setStatus(Status.FULL_TIME);
        assertTrue(match.isStarted());

        match.setStatus(Status.FINISHED);
        assertTrue(match.isStarted());
    }

    /**
     * Tests Match::isWinner.
     */
    @Test
    void testIsWinner() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        assertTrue(match.isWinner(match.getHomeTeam()));
        assertFalse(match.isWinner(match.getAwayTeam()));

        match.setHomeTeamGoalsScored(0);

        assertFalse(match.isWinner(match.getHomeTeam()));
        assertFalse(match.isWinner(match.getAwayTeam()));

        match.setAwayTeamGoalsScored(1);

        assertFalse(match.isWinner(match.getHomeTeam()));
        assertTrue(match.isWinner(match.getAwayTeam()));
    }

    /**
     * Tests Match::isLoser.
     */
    @Test
    void testIsLoser() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(0);
        match.setAwayTeamGoalsScored(1);

        assertTrue(match.isLoser(match.getHomeTeam()));
        assertFalse(match.isLoser(match.getAwayTeam()));

        match.setAwayTeamGoalsScored(0);

        assertFalse(match.isLoser(match.getHomeTeam()));
        assertFalse(match.isLoser(match.getAwayTeam()));

        match.setHomeTeamGoalsScored(1);

        assertFalse(match.isLoser(match.getHomeTeam()));
        assertTrue(match.isLoser(match.getAwayTeam()));
    }

    /**
     * Tests Match::isDraw.
     */
    @Test
    void testIsDraw() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        assertFalse(match.isDraw());

        match.setHomeTeamGoalsScored(0);

        assertTrue(match.isDraw());

        match.setAwayTeamGoalsScored(1);

        assertFalse(match.isDraw());
    }

    /**
     * Tests Match::getHomeTeamGoals.
     */
    @Test
    void testGetHomeTeamGoals() {
        final Match match = generate(Match.class);

        final List<Goal> homeTeamGoals = generateList(Goal.class);
        homeTeamGoals.forEach(goal -> goal.setTeam(match.getHomeTeam()));

        final List<Goal> awayTeamGoals = generateList(Goal.class);
        awayTeamGoals.forEach(goal -> goal.setTeam(match.getAwayTeam()));

        match.getGoals().clear();
        match.getGoals().addAll(awayTeamGoals);
        match.getGoals().addAll(homeTeamGoals);

        final List<Goal> result = match.getHomeTeamGoals();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(homeTeamGoals, result);
    }

    /**
     * Tests Match::getAwayTeamGoals.
     */
    @Test
    void testGetAwayTeamGoals() {
        final Match match = generate(Match.class);

        final List<Goal> homeTeamGoals = generateList(Goal.class);
        homeTeamGoals.forEach(goal -> goal.setTeam(match.getHomeTeam()));

        final List<Goal> awayTeamGoals = generateList(Goal.class);
        awayTeamGoals.forEach(goal -> goal.setTeam(match.getAwayTeam()));

        match.getGoals().clear();
        match.getGoals().addAll(awayTeamGoals);
        match.getGoals().addAll(homeTeamGoals);

        final List<Goal> result = match.getAwayTeamGoals();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(awayTeamGoals, result);
    }

    /**
     * Tests Match::getGoalsFor.
     */
    @Test
    void testGetGoalsFor() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        assertEquals(match.getHomeTeamGoalsScored(), match.getGoalsFor(match.getHomeTeam()));
        assertEquals(match.getAwayTeamGoalsScored(), match.getGoalsFor(match.getAwayTeam()));
    }

    /**
     * Tests Match::getGoalsAgainst.
     */
    @Test
    void testGetGoalsAgainst() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        assertEquals(match.getAwayTeamGoalsScored(), match.getGoalsAgainst(match.getHomeTeam()));
        assertEquals(match.getHomeTeamGoalsScored(), match.getGoalsAgainst(match.getAwayTeam()));
    }

    /**
     * Tests Match::getPoints.
     */
    @Test
    void testGetPoints() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(0);

        assertEquals(Match.WIN_POINTS, match.getPoints(match.getHomeTeam()));
        assertEquals(Match.LOSS_POINTS, match.getPoints(match.getAwayTeam()));

        match.setHomeTeamGoalsScored(0);

        assertEquals(Match.DRAW_POINTS, match.getPoints(match.getHomeTeam()));
        assertEquals(Match.DRAW_POINTS, match.getPoints(match.getAwayTeam()));

        match.setAwayTeamGoalsScored(1);

        assertEquals(Match.LOSS_POINTS, match.getPoints(match.getHomeTeam()));
        assertEquals(Match.WIN_POINTS, match.getPoints(match.getAwayTeam()));
    }

    /**
     * Tests Match::getTeamByStatSourceId.
     */
    @Test
    void testGetTeamByStatSourceId() {
        final Match match = generate(Match.class);

        match.getHomeTeam().setStatSourceId(1);
        match.getAwayTeam().setStatSourceId(0);

        assertEquals(match.getHomeTeam(),
                     match.getTeamByStatSourceId(match.getHomeTeam().getStatSourceId()).orElse(null));
        assertEquals(match.getAwayTeam(),
                     match.getTeamByStatSourceId(match.getAwayTeam().getStatSourceId()).orElse(null));

        assertFalse(match.getTeamByStatSourceId(-1).isPresent());
    }

    /**
     * Tests Match::reset.
     */
    @Test
    void testReset() {
        final Match match = generate(Match.class);

        match.setHomeTeamGoalsScored(1);
        match.setAwayTeamGoalsScored(1);
        match.setPreviousHomeTeamGoalsScored(1);
        match.setPreviousAwayTeamGoalsScored(1);

        match.reset();

        assertEquals(0, match.getHomeTeamGoalsScored());
        assertEquals(0, match.getAwayTeamGoalsScored());
        assertEquals(1, match.getPreviousHomeTeamGoalsScored());
        assertEquals(1, match.getPreviousAwayTeamGoalsScored());
    }

}
