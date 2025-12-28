package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Lineup;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player stat summary tests.
 */
class PlayerStatSummaryTests extends EasyRandomTests {

    /**
     * Tests PlayerStatSummary::isValid.
     */
    @Test
    @SuppressWarnings("checkstyle:ExecutableStatementCount")
    void isValid() {
        final PlayerStatSummary playerStatSummary = generate(PlayerStatSummary.class);

        assertTrue(playerStatSummary.isValid());

        playerStatSummary.setRanking(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setRanking(1);

        playerStatSummary.setCleanSheets(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setCleanSheets(1);

        playerStatSummary.setYellowCards(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setYellowCards(1);

        playerStatSummary.setRedCards(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setRedCards(1);

        playerStatSummary.setSubstitutionsOn(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setSubstitutionsOn(1);

        playerStatSummary.setSubstitutionsOff(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setSubstitutionsOff(1);

        playerStatSummary.setManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setManOfTheMatch(1);

        playerStatSummary.setSharedManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setSharedManOfTheMatch(1);

        playerStatSummary.setGamesStarted(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setGamesStarted(1);

        playerStatSummary.setGamesSubstitute(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setGamesSubstitute(1);

        playerStatSummary.setGamesDidNotParticipate(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setGamesDidNotParticipate(1);

        playerStatSummary.setMinutesPlayed(-1);
        assertFalse(playerStatSummary.isValid());
        playerStatSummary.setMinutesPlayed(1);

        assertTrue(playerStatSummary.isValid());

    }

    /**
     * Tests PlayerStatSummary::reset.
     */
    @Test
    void reset() {
        final PlayerStatSummary playerStatSummary = generate(PlayerStatSummary.class);

        playerStatSummary.reset();

        assertEquals(0, playerStatSummary.getRanking());
        assertEquals(0, playerStatSummary.getPointsPerAppearance());
        assertEquals(0, playerStatSummary.getCleanSheets());
        assertEquals(0, playerStatSummary.getYellowCards());
        assertEquals(0, playerStatSummary.getRedCards());
        assertEquals(0, playerStatSummary.getSubstitutionsOn());
        assertEquals(0, playerStatSummary.getSubstitutionsOff());
        assertEquals(0, playerStatSummary.getManOfTheMatch());
        assertEquals(0, playerStatSummary.getSharedManOfTheMatch());
        assertEquals(0, playerStatSummary.getGamesStarted());
        assertEquals(0, playerStatSummary.getGamesSubstitute());
        assertEquals(0, playerStatSummary.getGamesDidNotParticipate());
        assertEquals(0, playerStatSummary.getMinutesPlayed());
    }

    /**
     * Tests PlayerStatSummary::init.
     */
    @Test
    @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity",
                        "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity" })
    void init() {
        final PlayerStatSummary source = generate(PlayerStatSummary.class);
        final PlayerStatSummary destination = generate(PlayerStatSummary.class);

        destination.init(source);

        assertEquals(source.getRanking(), destination.getRanking());
        assertEquals(source.getPointsPerAppearance(), destination.getPointsPerAppearance());
        assertEquals(source.getCleanSheets(), destination.getCleanSheets());
        assertEquals(source.getYellowCards(), destination.getYellowCards());
        assertEquals(source.getRedCards(), destination.getRedCards());
        assertEquals(source.getSubstitutionsOn(), destination.getSubstitutionsOn());
        assertEquals(source.getSubstitutionsOff(), destination.getSubstitutionsOff());
        assertEquals(source.getManOfTheMatch(), destination.getManOfTheMatch());
        assertEquals(source.getSharedManOfTheMatch(), destination.getSharedManOfTheMatch());
        assertEquals(source.getGamesStarted(), destination.getGamesStarted());
        assertEquals(source.getGamesSubstitute(), destination.getGamesSubstitute());
        assertEquals(source.getGamesDidNotParticipate(), destination.getGamesDidNotParticipate());
        assertEquals(source.getMinutesPlayed(), destination.getMinutesPlayed());
    }

    /**
     * Tests PlayerStatSummary::addStats.
     */
    @Test
    @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity",
                        "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity" })
    void addStats() {
        final PlayerMatchStat source = generate(PlayerMatchStat.class);
        final PlayerStatSummary destination = generate(PlayerStatSummary.class);

        final int cleanSheets = destination.getCleanSheets() + (source.getGoalsConceded() > 0 ? 0 : 1);
        final int yellowCards = destination.getYellowCards() + (source.getYellowCardTime() > 0 ? 1 : 0);
        final int redCards = destination.getRedCards() + (source.getRedCardTime() > 0 ? 1 : 0);
        final int substitutionsOn = destination.getSubstitutionsOn() + (source.getSubstitutionOnTime() > 0 ? 1 : 0);
        final int substitutionsOff = destination.getSubstitutionsOff() + (source.getSubstitutionOffTime() > 0 ? 1 : 0);
        final int manOfTheMatch = destination.getManOfTheMatch() + (source.isManOfTheMatch() ? 1 : 0);
        final int sharedManOfTheMatch = destination.getSharedManOfTheMatch() + (source.isSharedManOfTheMatch() ? 1 : 0);
        final int gamesStarted = destination.getGamesStarted() +
                                 (source.getLineup().equals(Lineup.STARTING_LINEUP) ? 1 : 0);
        final int gamesSubstitute = destination.getGamesSubstitute() +
                                    (source.getLineup().equals(Lineup.SUBSTITUTE) ? 1 : 0);
        final int gamesDidNotParticipate = destination.getGamesDidNotParticipate() +
                                           (source.getLineup().equals(Lineup.DID_NOT_PARTICIPATE) ? 1 : 0);
        final int minutesPlayed = destination.getMinutesPlayed() + (source.getMinutesPlayed());

        destination.addStats(source);

        assertEquals(cleanSheets, destination.getCleanSheets());
        assertEquals(yellowCards, destination.getYellowCards());
        assertEquals(redCards, destination.getRedCards());
        assertEquals(substitutionsOn, destination.getSubstitutionsOn());
        assertEquals(substitutionsOff, destination.getSubstitutionsOff());
        assertEquals(manOfTheMatch, destination.getManOfTheMatch());
        assertEquals(sharedManOfTheMatch, destination.getSharedManOfTheMatch());
        assertEquals(gamesStarted, destination.getGamesStarted());
        assertEquals(gamesSubstitute, destination.getGamesSubstitute());
        assertEquals(gamesDidNotParticipate, destination.getGamesDidNotParticipate());
        assertEquals(minutesPlayed, destination.getMinutesPlayed());
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
