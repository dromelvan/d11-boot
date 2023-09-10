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
    @SuppressWarnings({ "DataFlowIssue", "checkstyle:ExecutableStatementCount" })
    void testIsValid() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);

        assertTrue(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid");

        playerSeasonStat.setShirtNumber(null);
        assertTrue(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid shirt number null");
        playerSeasonStat.setShirtNumber(-1);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid shirt number negative");
        playerSeasonStat.setShirtNumber(0);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid shirt number non positive");
        playerSeasonStat.setShirtNumber(1);

        playerSeasonStat.setFee(-1);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid fee negative");
        playerSeasonStat.setFee(1);

        playerSeasonStat.setWinCount(null);
        assertTrue(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid win count null");
        playerSeasonStat.setWinCount(0);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid win count negative");
        playerSeasonStat.setWinCount(1);

        playerSeasonStat.setPlayer(null);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid player null");
        playerSeasonStat.setPlayer(new Player());

        playerSeasonStat.setSeason(null);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid season null");
        playerSeasonStat.setSeason(new Season());

        playerSeasonStat.setTeam(null);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid team null");
        playerSeasonStat.setTeam(new Team());

        playerSeasonStat.setD11Team(null);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid D11 team null");
        playerSeasonStat.setD11Team(new D11Team());

        playerSeasonStat.setPosition(null);
        assertFalse(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid position null");
        playerSeasonStat.setPosition(new Position());

        assertTrue(playerSeasonStat.isValid(), "PlayerSeasonStat::isValid valid");
    }

    /**
     * Tests PlayerStatSummary::reset.
     */
    @Test
    void reset() {
        final PlayerSeasonStat playerSeasonStat = generate(PlayerSeasonStat.class);
        playerSeasonStat.getFormMatchPoints().addAll(Arrays.asList(1, 1, 1, 1, 1));

        playerSeasonStat.reset();

        assertEquals(0, playerSeasonStat.getFormPoints(), "PlayerSeasonStat::form points equals");
        assertTrue(playerSeasonStat.getFormMatchPoints().isEmpty(), "PlayerSeasonStat::reset form match points empty");
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

        assertEquals(goals, playerSeasonStat.getGoals(), "PlayerSeasonStat::updateStats goals equals");
        assertEquals(goalAssists, playerSeasonStat.getGoalAssists(),
                     "PlayerSeasonStat::updateStats goal assists equals");
        assertEquals(ownGoals, playerSeasonStat.getOwnGoals(), "PlayerSeasonStat::updateStats own goals equals");
        assertEquals(goalsConceded, playerSeasonStat.getGoalsConceded(),
                     "PlayerSeasonStat::updateStats goals conceded equals");
        assertEquals(rating, playerSeasonStat.getRating(), "PlayerSeasonStat::updateStats rating equals");
        assertEquals(points, playerSeasonStat.getPoints(), "PlayerSeasonStat::updateStats points equals");
        assertEquals(pointsPerAppearance, playerSeasonStat.getPointsPerAppearance(),
                     "PlayerSeasonStat::updateStats points per appearance equals");
        assertEquals(cleanSheets, playerSeasonStat.getCleanSheets(),
                     "PlayerSeasonStat::updateStats clean sheets equals");
        assertEquals(yellowCards, playerSeasonStat.getYellowCards(),
                     "PlayerSeasonStat::updateStats yellow cards equals");
        assertEquals(redCards, playerSeasonStat.getRedCards(),
                     "PlayerSeasonStat::updateStats red cards equals");
        assertEquals(substitutionsOn, playerSeasonStat.getSubstitutionsOn(),
                     "PlayerSeasonStat::updateStats substitutions on equals");
        assertEquals(substitutionsOff, playerSeasonStat.getSubstitutionsOff(),
                     "PlayerSeasonStat::updateStats substitutions off equals");
        assertEquals(manOfTheMatch, playerSeasonStat.getManOfTheMatch(),
                     "PlayerSeasonStat::updateStats man of the match equals");
        assertEquals(sharedManOfTheMatch, playerSeasonStat.getSharedManOfTheMatch(),
                     "PlayerSeasonStat::updateStats shared man of the match equals");
        assertEquals(gamesStarted, playerSeasonStat.getGamesStarted(),
                     "PlayerSeasonStat::updateStats games started equals");
        assertEquals(gamesSubstitute, playerSeasonStat.getGamesSubstitute(),
                     "PlayerSeasonStat::updateStats games substitute equals");
        assertEquals(gamesDidNotParticipate, playerSeasonStat.getGamesDidNotParticipate(),
                     "PlayerSeasonStat::updateStats games did not participate equals");
        assertEquals(minutesPlayed, playerSeasonStat.getMinutesPlayed(),
                     "PlayerSeasonStat::updateStats minutes played equals");
    }

}
