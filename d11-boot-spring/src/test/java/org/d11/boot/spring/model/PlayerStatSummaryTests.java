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

        assertTrue(playerStatSummary.isValid(), "PlayerStatSummary::isValid");

        playerStatSummary.setRanking(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid ranking negative");
        playerStatSummary.setRanking(1);

        playerStatSummary.setCleanSheets(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid clean sheets negative");
        playerStatSummary.setCleanSheets(1);

        playerStatSummary.setYellowCards(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid yellow cards negative");
        playerStatSummary.setYellowCards(1);

        playerStatSummary.setRedCards(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid red cards negative");
        playerStatSummary.setRedCards(1);

        playerStatSummary.setSubstitutionsOn(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid substitutions on negative");
        playerStatSummary.setSubstitutionsOn(1);

        playerStatSummary.setSubstitutionsOff(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid substitutions off negative");
        playerStatSummary.setSubstitutionsOff(1);

        playerStatSummary.setManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid man of the match negative");
        playerStatSummary.setManOfTheMatch(1);

        playerStatSummary.setSharedManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid shared man of the match negative");
        playerStatSummary.setSharedManOfTheMatch(1);

        playerStatSummary.setGamesStarted(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid games started negative");
        playerStatSummary.setGamesStarted(1);

        playerStatSummary.setGamesSubstitute(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid games substitute negative");
        playerStatSummary.setGamesSubstitute(1);

        playerStatSummary.setGamesDidNotParticipate(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid games did not participate negative");
        playerStatSummary.setGamesDidNotParticipate(1);

        playerStatSummary.setMinutesPlayed(-1);
        assertFalse(playerStatSummary.isValid(), "PlayerStatSummary::isValid minutes played negative");
        playerStatSummary.setMinutesPlayed(1);

        assertTrue(playerStatSummary.isValid(), "PlayerStatSummary::isValid valid");

    }

    /**
     * Tests PlayerStatSummary::reset.
     */
    @Test
    void reset() {
        final PlayerStatSummary playerStatSummary = generate(PlayerStatSummary.class);

        playerStatSummary.reset();

        assertEquals(0, playerStatSummary.getRanking(), "PlayerStatSummary::reset ranking equals");
        assertEquals(0, playerStatSummary.getPointsPerAppearance(),
                     "PlayerStatSummary::reset points per appearance equals");
        assertEquals(0, playerStatSummary.getCleanSheets(), "PlayerStatSummary::reset clean sheets equals");
        assertEquals(0, playerStatSummary.getYellowCards(), "PlayerStatSummary::reset yellow cards equals");
        assertEquals(0, playerStatSummary.getRedCards(), "PlayerStatSummary::reset red cards equals");
        assertEquals(0, playerStatSummary.getSubstitutionsOn(), "PlayerStatSummary::reset substitutions on equals");
        assertEquals(0, playerStatSummary.getSubstitutionsOff(), "PlayerStatSummary::reset substitutions off equals");
        assertEquals(0, playerStatSummary.getManOfTheMatch(), "PlayerStatSummary::reset man of the match equals");
        assertEquals(0, playerStatSummary.getSharedManOfTheMatch(),
                     "PlayerStatSummary::reset shared man of the match equals");
        assertEquals(0, playerStatSummary.getGamesStarted(), "PlayerStatSummary::reset games started equals");
        assertEquals(0, playerStatSummary.getGamesSubstitute(), "PlayerStatSummary::reset games substitute equals");
        assertEquals(0, playerStatSummary.getGamesDidNotParticipate(),
                     "PlayerStatSummary::reset games did not participate equals");
        assertEquals(0, playerStatSummary.getMinutesPlayed(), "PlayerStatSummary::reset minutes played equals");
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

        assertEquals(source.getRanking(), destination.getRanking(), "PlayerStatSummary::init ranking equals");
        assertEquals(source.getPointsPerAppearance(), destination.getPointsPerAppearance(),
                     "PlayerStatSummary::init points per appearance equals");
        assertEquals(source.getCleanSheets(), destination.getCleanSheets(),
                     "PlayerStatSummary::init clean sheets equals");
        assertEquals(source.getYellowCards(), destination.getYellowCards(),
                     "PlayerStatSummary::init yellow cards equals");
        assertEquals(source.getRedCards(), destination.getRedCards(), "PlayerStatSummary::init red cards equals");
        assertEquals(source.getSubstitutionsOn(), destination.getSubstitutionsOn(),
                     "PlayerStatSummary::init substitutions on equals");
        assertEquals(source.getSubstitutionsOff(), destination.getSubstitutionsOff(),
                     "PlayerStatSummary::init substitutions off equals");
        assertEquals(source.getManOfTheMatch(), destination.getManOfTheMatch(),
                     "PlayerStatSummary::init man of the match equals");
        assertEquals(source.getSharedManOfTheMatch(), destination.getSharedManOfTheMatch(),
                     "PlayerStatSummary::init shared man of the match equals");
        assertEquals(source.getGamesStarted(), destination.getGamesStarted(),
                     "PlayerStatSummary::init games started equals");
        assertEquals(source.getGamesSubstitute(), destination.getGamesSubstitute(),
                     "PlayerStatSummary::init games substitute equals");
        assertEquals(source.getGamesDidNotParticipate(), destination.getGamesDidNotParticipate(),
                     "PlayerStatSummary::init games did not participate equals");
        assertEquals(source.getMinutesPlayed(), destination.getMinutesPlayed(),
                     "PlayerStatSummary::init minutes played equals");
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

        assertEquals(cleanSheets, destination.getCleanSheets(), "PlayerStatSummary::addStats clean sheets equals");
        assertEquals(yellowCards, destination.getYellowCards(), "PlayerStatSummary::addStats yellow cards equals");
        assertEquals(redCards, destination.getRedCards(), "PlayerStatSummary::addStats red cards equals");
        assertEquals(substitutionsOn, destination.getSubstitutionsOn(),
                     "PlayerStatSummary::addStats substitutions on equals");
        assertEquals(substitutionsOff, destination.getSubstitutionsOff(),
                     "PlayerStatSummary::addStats substitutions off equals");
        assertEquals(manOfTheMatch, destination.getManOfTheMatch(),
                     "PlayerStatSummary::addStats man of the match equals");
        assertEquals(sharedManOfTheMatch, destination.getSharedManOfTheMatch(),
                     "PlayerStatSummary::addStats shared man of the match equals");
        assertEquals(gamesStarted, destination.getGamesStarted(), "PlayerStatSummary::addStats games started equals");
        assertEquals(gamesSubstitute, destination.getGamesSubstitute(),
                     "PlayerStatSummary::addStats games substitute equals");
        assertEquals(gamesDidNotParticipate, destination.getGamesDidNotParticipate(),
                     "PlayerStatSummary::addStats games did not participate equals");
        assertEquals(minutesPlayed, destination.getMinutesPlayed(),
                     "PlayerStatSummary::addStats minutes played equals");
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
