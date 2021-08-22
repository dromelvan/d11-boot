package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.PlayerStatSummary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player stat summary tests.
 */
public class PlayerStatSummaryTests extends D11EasyRandomTests {

    /**
     * Tests player stat summary validity.
     */
    @Test
    @SuppressWarnings({ "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS" })
    public void isValid() {
        final PlayerStatSummary playerStatSummary = generate(PlayerStatSummary.class);

        playerStatSummary.prePersist();

        assertTrue(playerStatSummary.isValid(), "New player stat summary should be valid.");

        playerStatSummary.setRanking(-1);
        assertFalse(playerStatSummary.isValid(), "Negative ranking should not be valid.");
        playerStatSummary.setRanking(1);

        playerStatSummary.setCleanSheets(-1);
        assertFalse(playerStatSummary.isValid(), "Negative clean sheets should not be valid.");
        playerStatSummary.setCleanSheets(1);

        playerStatSummary.setYellowCards(-1);
        assertFalse(playerStatSummary.isValid(), "Negative yellow cards should not be valid.");
        playerStatSummary.setYellowCards(1);

        playerStatSummary.setRedCards(-1);
        assertFalse(playerStatSummary.isValid(), "Negative red cards should not be valid.");
        playerStatSummary.setRedCards(1);

        playerStatSummary.setSubstitutionsOn(-1);
        assertFalse(playerStatSummary.isValid(), "Negative substitutions on should not be valid.");
        playerStatSummary.setSubstitutionsOn(1);

        playerStatSummary.setSubstitutionsOff(-1);
        assertFalse(playerStatSummary.isValid(), "Negative substitutions off should not be valid.");
        playerStatSummary.setSubstitutionsOff(1);

        playerStatSummary.setManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid(), "Negative man of the match should not be valid.");
        playerStatSummary.setManOfTheMatch(1);

        playerStatSummary.setSharedManOfTheMatch(-1);
        assertFalse(playerStatSummary.isValid(), "Negative shared man of the match should not be valid.");
        playerStatSummary.setSharedManOfTheMatch(1);

        playerStatSummary.setGamesStarted(-1);
        assertFalse(playerStatSummary.isValid(), "Negative games started should not be valid.");
        playerStatSummary.setGamesStarted(1);

        playerStatSummary.setGamesSubstitute(-1);
        assertFalse(playerStatSummary.isValid(), "Negative games substitute should not be valid.");
        playerStatSummary.setGamesSubstitute(1);

        playerStatSummary.setGamesDidNotParticipate(-1);
        assertFalse(playerStatSummary.isValid(), "Negative games did not participate should not be valid.");
        playerStatSummary.setGamesDidNotParticipate(1);

        playerStatSummary.setMinutesPlayed(-1);
        assertFalse(playerStatSummary.isValid(), "Negative minutes played should not be valid.");
        playerStatSummary.setMinutesPlayed(1);

        assertTrue(playerStatSummary.isValid(), "Player stat summary should be valid.");
    }

}
