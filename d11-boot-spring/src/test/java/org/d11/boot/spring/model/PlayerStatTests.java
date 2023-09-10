package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player stat tests.
 */
class PlayerStatTests extends EasyRandomTests {

    /**
     * Tests PlayerStat::isValid.
     */
    @Test
    void isValid() {
        final PlayerStat playerStat = generate(PlayerStat.class);

        assertTrue(playerStat.isValid(), "PlayerStat::isValid");

        playerStat.setGoals(-1);
        assertFalse(playerStat.isValid(), "PlayerStat::isValid goals negative");
        playerStat.setGoals(1);

        playerStat.setGoalAssists(-1);
        assertFalse(playerStat.isValid(), "PlayerStat::isValid goal assists negative");
        playerStat.setGoalAssists(1);

        playerStat.setOwnGoals(-1);
        assertFalse(playerStat.isValid(), "PlayerStat::isValid own goals negative");
        playerStat.setOwnGoals(1);

        playerStat.setGoalsConceded(-1);
        assertFalse(playerStat.isValid(), "PlayerStat::isValid goals conceded negative");
        playerStat.setGoalsConceded(1);

        playerStat.setRating(-1);
        assertFalse(playerStat.isValid(), "PlayerStat::isValid rating negative");
        playerStat.setRating(1);

        assertTrue(playerStat.isValid(), "PlayerStat::isValid valid");
    }

    /**
     * Tests PlayerStat::reset.
     */
    @Test
    void reset() {
        final PlayerStat playerStat = generate(PlayerStat.class);

        playerStat.reset();

        assertEquals(0, playerStat.getGoals(), "PlayerStat::reset goals equals");
        assertEquals(0, playerStat.getGoalAssists(), "PlayerStat::reset goal assists equals");
        assertEquals(0, playerStat.getOwnGoals(), "PlayerStat::reset own goals equals");
        assertEquals(0, playerStat.getGoalsConceded(), "PlayerStat::reset goals conceded equals");
        assertEquals(0, playerStat.getRating(), "PlayerStat::reset rating equals");
        assertEquals(0, playerStat.getPoints(), "PlayerStat::reset points equals");
    }

    /**
     * Tests PlayerStat::init.
     */
    @Test
    void init() {
        final PlayerStat source = generate(PlayerStat.class);
        final PlayerStat destination = generate(PlayerStat.class);

        destination.init(source);

        assertEquals(source.getGoals(), destination.getGoals(), "PlayerStat::init goals equals");
        assertEquals(source.getGoalAssists(), destination.getGoalAssists(), "PlayerStat::init goal assists equals");
        assertEquals(source.getOwnGoals(), destination.getOwnGoals(), "PlayerStat::init own goals equals");
        assertEquals(source.getGoalsConceded(), destination.getGoalsConceded(),
                     "PlayerStat::init goals conceded equals");
        assertEquals(source.getRating(), destination.getRating(), "PlayerStat::init rating equals");
        assertEquals(source.getPoints(), destination.getPoints(), "PlayerStat::init points equals");
    }

    /**
     * Tests PlayerStat::addStats.
     */
    @Test
    void addStats() {
        final PlayerStat source = generate(PlayerStat.class);
        final PlayerStat destination = generate(PlayerStat.class);

        final int goals = source.getGoals() + destination.getGoals();
        final int goalAssists = source.getGoalAssists() + destination.getGoalAssists();
        final int ownGoals = source.getOwnGoals() + destination.getOwnGoals();
        final int goalsConceded = source.getGoalsConceded() + destination.getGoalsConceded();
        final int rating = source.getRating() + destination.getRating();
        final int points = source.getPoints() + destination.getPoints();

        destination.addStats(source);

        assertEquals(goals, destination.getGoals(), "PlayerStat::addStats goals equals");
        assertEquals(goalAssists, destination.getGoalAssists(), "PlayerStat::addStats goal assists equals");
        assertEquals(ownGoals, destination.getOwnGoals(), "PlayerStat::addStats own goals equals");
        assertEquals(goalsConceded, destination.getGoalsConceded(), "PlayerStat::addStats goals conceded equals");
        assertEquals(rating, destination.getRating(), "PlayerStat::addStats rating equals");
        assertEquals(points, destination.getPoints(), "PlayerStat::addStats points equals");
    }

    /**
     * Tests PlayerStat::compareTo.
     */
    @Test
    @SuppressWarnings("checkstyle:MagicNumber")
    void compareTo() {
        final PlayerStat playerStat1 = generate(PlayerStat.class);
        final PlayerStat playerStat2 = generate(PlayerStat.class);
        final PlayerStat playerStat3 = generate(PlayerStat.class);
        final PlayerStat playerStat4 = generate(PlayerStat.class);

        playerStat1.setPoints(10);
        playerStat2.setPoints(5);
        playerStat2.setRating(1000);
        playerStat3.setPoints(5);
        playerStat3.setRating(500);
        playerStat4.setPoints(1);

        final List<PlayerStat> sorted = Arrays.asList(playerStat1, playerStat2, playerStat3, playerStat4);
        final List<PlayerStat> unsorted = Arrays.asList(playerStat2, playerStat4, playerStat1, playerStat3);

        Collections.sort(unsorted);

        assertEquals(sorted, unsorted, "PlayerStat::compareTo equals");
    }

}
