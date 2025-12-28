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
    void testIsValid() {
        final Goal goal = generate(Goal.class);
        goal.setPenalty(!goal.isOwnGoal());

        assertTrue(goal.isValid());

        goal.setTime(-1);
        assertFalse(goal.isValid());
        goal.setTime(Goal.MAX_GOAL_TIME + 1);
        assertFalse(goal.isValid());
        goal.setTime(0);

        goal.setAddedTime(-1);
        assertFalse(goal.isValid());
        goal.setAddedTime(0);

        goal.setMatch(null);
        assertFalse(goal.isValid());
        goal.setMatch(new Match());

        goal.setTeam(null);
        assertFalse(goal.isValid());
        goal.setTeam(new Team());

        goal.setPlayer(null);
        assertFalse(goal.isValid());
        goal.setPlayer(new Player());

        goal.setPenalty(true);
        goal.setOwnGoal(true);
        assertFalse(goal.isValid());
        goal.setPenalty(false);

        assertTrue(goal.isValid());
    }

}
