package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Lineup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player season stat tests.
 */
class PlayerSeasonStatTests extends EasyRandomTests {

    /**
     * Tests PlayerSeasonStat::isValid.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void testIsValid() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);

        assertTrue(playerSeasonStat.isValid());

        playerSeasonStat.setShirtNumber(null);
        assertTrue(playerSeasonStat.isValid());
        playerSeasonStat.setShirtNumber(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setShirtNumber(0);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setShirtNumber(1);

        playerSeasonStat.setFee(-1);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setFee(1);

        playerSeasonStat.setWinCount(null);
        assertTrue(playerSeasonStat.isValid());
        playerSeasonStat.setWinCount(0);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setWinCount(1);

        playerSeasonStat.setPlayer(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setPlayer(new Player());

        playerSeasonStat.setSeason(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setSeason(new Season());

        playerSeasonStat.setTeam(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setTeam(new Team());

        playerSeasonStat.setD11Team(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setD11Team(new D11Team());

        playerSeasonStat.setPosition(null);
        assertFalse(playerSeasonStat.isValid());
        playerSeasonStat.setPosition(new Position());

        assertTrue(playerSeasonStat.isValid());
    }

    /**
     * Tests PlayerStatSummary::reset.
     */
    @Test
    void reset() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        playerSeasonStat.getFormMatchPoints().addAll(Arrays.asList(1, 1, 1, 1, 1));

        playerSeasonStat.reset();

        assertEquals(0, playerSeasonStat.getFormPoints());
        assertTrue(playerSeasonStat.getFormMatchPoints().isEmpty());
    }

    /**
     * Tests PlayerSeasonStat::updateStats.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void updateStats() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        final List<PlayerMatchStat> playerMatchStats = generateList(PlayerMatchStat.class);

        // Reset one player match stat to make sure we're calculating 'did not participate' correctly as well
        playerMatchStats.get(0).reset();
        final int matchesParticipated = playerMatchStats.size() - 1;

        final int goals = playerMatchStats.stream().mapToInt(PlayerMatchStat::getGoals).sum();
        final int goalAssists = playerMatchStats.stream().mapToInt(PlayerMatchStat::getGoalAssists).sum();
        final int ownGoals = playerMatchStats.stream().mapToInt(PlayerMatchStat::getOwnGoals).sum();
        final int goalsConceded = playerMatchStats.stream().mapToInt(PlayerMatchStat::getGoalsConceded).sum();
        final int rating = playerMatchStats.stream().mapToInt(PlayerMatchStat::getRating).sum() / matchesParticipated;
        final int points = playerMatchStats.stream().mapToInt(PlayerMatchStat::getPoints).sum();
        final int pointsPerAppearance = points / matchesParticipated;
        final int cleanSheets = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.isParticipant() && playerMatchStat.getGoalsConceded() == 0)
                .count();
        final int yellowCards = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getYellowCardTime() > 0)
                .count();
        final int redCards = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getRedCardTime() > 0)
                .count();
        final int substitutionsOn = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getSubstitutionOnTime() > 0)
                .count();
        final int substitutionsOff = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getSubstitutionOffTime() > 0)
                .count();
        final int manOfTheMatch = (int) playerMatchStats.stream().filter(PlayerMatchStat::isManOfTheMatch).count();
        final int sharedManOfTheMatch = (int) playerMatchStats.stream().filter(PlayerMatchStat::isSharedManOfTheMatch)
                .count();
        final int gamesStarted = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getLineup().equals(Lineup.STARTING_LINEUP))
                .count();
        final int gamesSubstitute = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getLineup().equals(Lineup.SUBSTITUTE))
                .count();
        final int gamesDidNotParticipate = (int) playerMatchStats.stream()
                .filter(playerMatchStat -> playerMatchStat.getLineup().equals(Lineup.DID_NOT_PARTICIPATE))
                .count();
        final int minutesPlayed = playerMatchStats.stream().mapToInt(PlayerMatchStat::getMinutesPlayed).sum();

        playerSeasonStat.updateStats(playerMatchStats);

        assertEquals(goals, playerSeasonStat.getGoals());
        assertEquals(goalAssists, playerSeasonStat.getGoalAssists());
        assertEquals(ownGoals, playerSeasonStat.getOwnGoals());
        assertEquals(goalsConceded, playerSeasonStat.getGoalsConceded());
        assertEquals(rating, playerSeasonStat.getRating());
        assertEquals(points, playerSeasonStat.getPoints());
        assertEquals(pointsPerAppearance, playerSeasonStat.getPointsPerAppearance());
        assertEquals(cleanSheets, playerSeasonStat.getCleanSheets());
        assertEquals(yellowCards, playerSeasonStat.getYellowCards());
        assertEquals(redCards, playerSeasonStat.getRedCards());
        assertEquals(substitutionsOn, playerSeasonStat.getSubstitutionsOn());
        assertEquals(substitutionsOff, playerSeasonStat.getSubstitutionsOff());
        assertEquals(manOfTheMatch, playerSeasonStat.getManOfTheMatch());
        assertEquals(sharedManOfTheMatch, playerSeasonStat.getSharedManOfTheMatch());
        assertEquals(gamesStarted, playerSeasonStat.getGamesStarted());
        assertEquals(gamesSubstitute, playerSeasonStat.getGamesSubstitute());
        assertEquals(gamesDidNotParticipate, playerSeasonStat.getGamesDidNotParticipate());
        assertEquals(minutesPlayed, playerSeasonStat.getMinutesPlayed());
    }

}
