package org.d11.boot.application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player season stat tests.
 */
public class PlayerSeasonStatTests extends D11EasyRandomTests {

    /**
     * Tests player season stat validity.
     */
    @Test
    public void isValid() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);

        playerSeasonStat.prePersist();

        assertTrue(playerSeasonStat.isValid(), "New player season stat should be valid.");

        playerSeasonStat.setPlayer(null);
        assertFalse(playerSeasonStat.isValid(), "Null player should not be valid.");
        playerSeasonStat.setPlayer(new Player());

        playerSeasonStat.setSeason(null);
        assertFalse(playerSeasonStat.isValid(), "Null season should not be valid.");
        playerSeasonStat.setSeason(new Season());

        playerSeasonStat.setTeam(null);
        assertFalse(playerSeasonStat.isValid(), "Null team should not be valid.");
        playerSeasonStat.setTeam(new Team());

        playerSeasonStat.setD11Team(null);
        assertFalse(playerSeasonStat.isValid(), "Null D11 team should not be valid.");
        playerSeasonStat.setD11Team(new D11Team());

        playerSeasonStat.setPosition(null);
        assertFalse(playerSeasonStat.isValid(), "Null position should not be valid.");
        playerSeasonStat.setPosition(new Position());

        assertTrue(playerSeasonStat.isValid(), "Player stat summary should be valid.");
    }

}
