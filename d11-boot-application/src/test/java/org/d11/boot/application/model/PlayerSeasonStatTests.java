package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.PlayerSeasonStat;
import org.d11.boot.application.model.jpa.Position;
import org.d11.boot.application.model.jpa.Season;
import org.d11.boot.application.model.jpa.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        playerSeasonStat.setValue(-1);
        assertFalse(playerSeasonStat.isValid(), "Negative value should not be valid.");
        playerSeasonStat.setValue(1);

        playerSeasonStat.setWinCount(null);
        assertTrue(playerSeasonStat.isValid(), "Null win count should be valid.");
        playerSeasonStat.setWinCount(0);
        assertFalse(playerSeasonStat.isValid(), "Non positive win count should be valid.");
        playerSeasonStat.setWinCount(1);

        playerSeasonStat.setFormMatchPoints(null);
        assertFalse(playerSeasonStat.isValid(), "Null form match points should not be valid.");
        playerSeasonStat.setFormMatchPoints(new ArrayList<>());

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
