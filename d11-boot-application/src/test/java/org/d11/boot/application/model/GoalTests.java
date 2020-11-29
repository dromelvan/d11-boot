package org.d11.boot.application.model;

import org.d11.boot.api.model.GoalDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Goal tests.
 */
public class GoalTests extends D11EasyRandomTests {

    /**
     * Tests goal validity.
     */
    @Test
    public void isValid() {
        final Goal goal = generate(Goal.class);
        goal.setPenalty(!goal.isOwnGoal());

        assertTrue(goal.isValid(), "New goal should be valid.");

        goal.setTime(-1);
        assertFalse(goal.isValid(), "Negative time should not be valid.");
        goal.setTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(goal.isValid(), "Too high time should not be valid.");
        goal.setTime(0);

        goal.setAddedTime(-1);
        assertFalse(goal.isValid(), "Negative added time should not be valid.");
        goal.setAddedTime(0);

        goal.setMatch(null);
        assertFalse(goal.isValid(), "Null match should not be valid.");
        goal.setMatch(new Match());

        goal.setTeam(null);
        assertFalse(goal.isValid(), "Null team should not be valid.");
        goal.setTeam(new Team());

        goal.setPlayer(null);
        assertFalse(goal.isValid(), "Null player should not be valid.");
        goal.setPlayer(new Player());

        goal.setPenalty(true);
        goal.setOwnGoal(true);
        assertFalse(goal.isValid(), "Penalty and own goal both true should not be valid.");
        goal.setPenalty(false);

        assertTrue(goal.isValid(), "Goal should be valid.");
    }

    /**
     * Tests mapping between Goal and GoalDTO.
     */
    @Test
    public void map() {
        final Goal goal = generate(Goal.class);

        final GoalDTO goalDTO = map(goal, GoalDTO.class);
        final Goal mappedGoal = map(goalDTO, Goal.class);

        assertEquals(goal, mappedGoal, "Goal should equal mapped goak.");
    }

}
