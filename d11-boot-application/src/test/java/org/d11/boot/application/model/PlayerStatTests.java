package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.PlayerStat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player stat tests.
 */
public class PlayerStatTests extends D11EasyRandomTests {

    /**
     * Tests player stat validity.
     */
    @Test
    public void isValid() {
        final PlayerStat playerStat = generate(PlayerStat.class);

        playerStat.prePersist();

        assertTrue(playerStat.isValid(), "New player stat should be valid.");

        playerStat.setGoals(-1);
        assertFalse(playerStat.isValid(), "Negative goals should not be valid.");
        playerStat.setGoals(1);

        playerStat.setGoalAssists(-1);
        assertFalse(playerStat.isValid(), "Negative goal assists should not be valid.");
        playerStat.setGoalAssists(1);

        playerStat.setOwnGoals(-1);
        assertFalse(playerStat.isValid(), "Negative own goals should not be valid.");
        playerStat.setOwnGoals(1);

        playerStat.setGoalsConceded(-1);
        assertFalse(playerStat.isValid(), "Negative goals conceded should not be valid.");
        playerStat.setGoalsConceded(1);

        playerStat.setRating(-1);
        assertFalse(playerStat.isValid(), "Negative rating should not be valid.");
        playerStat.setRating(1);

        assertTrue(playerStat.isValid(), "Player stat should be valid.");
    }

}
