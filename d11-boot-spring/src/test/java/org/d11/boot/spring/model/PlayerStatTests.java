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

        assertTrue(playerStat.isValid());

        playerStat.setGoals(-1);
        assertFalse(playerStat.isValid());
        playerStat.setGoals(1);

        playerStat.setGoalAssists(-1);
        assertFalse(playerStat.isValid());
        playerStat.setGoalAssists(1);

        playerStat.setOwnGoals(-1);
        assertFalse(playerStat.isValid());
        playerStat.setOwnGoals(1);

        playerStat.setGoalsConceded(-1);
        assertFalse(playerStat.isValid());
        playerStat.setGoalsConceded(1);

        playerStat.setRating(-1);
        assertFalse(playerStat.isValid());
        playerStat.setRating(1);

        assertTrue(playerStat.isValid());
    }

    /**
     * Tests PlayerStat::reset.
     */
    @Test
    void reset() {
        final PlayerStat playerStat = generate(PlayerStat.class);

        playerStat.reset();

        assertEquals(0, playerStat.getGoals());
        assertEquals(0, playerStat.getGoalAssists());
        assertEquals(0, playerStat.getOwnGoals());
        assertEquals(0, playerStat.getGoalsConceded());
        assertEquals(0, playerStat.getRating());
        assertEquals(0, playerStat.getPoints());
    }

    /**
     * Tests PlayerStat::init.
     */
    @Test
    void init() {
        final PlayerStat source = generate(PlayerStat.class);
        final PlayerStat destination = generate(PlayerStat.class);

        destination.init(source);

        assertEquals(source.getGoals(), destination.getGoals());
        assertEquals(source.getGoalAssists(), destination.getGoalAssists());
        assertEquals(source.getOwnGoals(), destination.getOwnGoals());
        assertEquals(source.getGoalsConceded(), destination.getGoalsConceded());
        assertEquals(source.getRating(), destination.getRating());
        assertEquals(source.getPoints(), destination.getPoints());
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

        assertEquals(goals, destination.getGoals());
        assertEquals(goalAssists, destination.getGoalAssists());
        assertEquals(ownGoals, destination.getOwnGoals());
        assertEquals(goalsConceded, destination.getGoalsConceded());
        assertEquals(rating, destination.getRating());
        assertEquals(points, destination.getPoints());
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

        assertEquals(sorted, unsorted);
    }

}
