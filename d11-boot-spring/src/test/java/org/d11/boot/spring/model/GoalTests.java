package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Goal tests.
 */
class GoalTests extends EasyRandomTests {

    /**
     * Tests Goal::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final Goal goal = generate(Goal.class);
        goal.setPenalty(!goal.isOwnGoal());

        assertTrue(goal.isValid(), "Goal::isValid");

        goal.setTime(-1);
        assertFalse(goal.isValid(), "Goal::isValid time negative");
        goal.setTime(Goal.MAX_GOAL_TIME + 1);
        assertFalse(goal.isValid(), "Goal::isValid time too high");
        goal.setTime(0);

        goal.setAddedTime(-1);
        assertFalse(goal.isValid(), "Goal::isValid added time negative");
        goal.setAddedTime(0);

        goal.setMatch(null);
        assertFalse(goal.isValid(), "Goal::isValid match null");
        goal.setMatch(new Match());

        goal.setTeam(null);
        assertFalse(goal.isValid(), "Goal::isValid team null");
        goal.setTeam(new Team());

        goal.setPlayer(null);
        assertFalse(goal.isValid(), "Goal::isValid player null");
        goal.setPlayer(new Player());

        goal.setPenalty(true);
        goal.setOwnGoal(true);
        assertFalse(goal.isValid(), "Goal::isValid penalty and own goal true");
        goal.setPenalty(false);

        assertTrue(goal.isValid(), "Goal::isValid valid");
    }

}
