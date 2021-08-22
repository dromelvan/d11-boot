package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.MatchEvent;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.Substitution;
import org.d11.boot.application.model.jpa.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Substitution tests.
 */
public class SubstitutionTests extends D11EasyRandomTests {

    /**
     * Tests card validity.
     */
    @Test
    public void isValid() {
        final Substitution substitution = generate(Substitution.class);

        substitution.prePersist();

        // It's unlikely but possible the generated players have the same id.
        substitution.getPlayerIn().setId(substitution.getPlayer().getId() + 1);

        assertTrue(substitution.isValid(), "New substitution should be valid.");

        substitution.setTime(-1);
        assertFalse(substitution.isValid(), "Negative time should not be valid.");
        substitution.setTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(substitution.isValid(), "Too high time should not be valid.");
        substitution.setTime(0);

        substitution.setAddedTime(-1);
        assertFalse(substitution.isValid(), "Negative added time should not be valid.");
        substitution.setAddedTime(0);

        substitution.setMatch(null);
        assertFalse(substitution.isValid(), "Null match should not be valid.");
        substitution.setMatch(new Match());

        substitution.setTeam(null);
        assertFalse(substitution.isValid(), "Null team should not be valid.");
        substitution.setTeam(new Team());

        final Player player = substitution.getPlayer();
        substitution.setPlayer(null);
        assertFalse(substitution.isValid(), "Null player should not be valid.");
        substitution.setPlayer(player);

        // It's unlikely but possible the generated players have the same id.
        substitution.setPlayerIn(substitution.getPlayer());
        assertFalse(substitution.isValid(), "Same player in and out should not be valid.");
        substitution.setPlayerIn(null);
        assertFalse(substitution.isValid(), "Null player in should not be valid.");
        substitution.setPlayerIn(new Player());

        assertTrue(substitution.isValid(), "Substitution should be valid.");
    }

}
