package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.d11.boot.util.Lineup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player match stat tests.
 */
class PlayerMatchStatTests extends EasyRandomTests {

    /**
     * Tests PlayerMatchStat::isValid.
     */
    @Test
    @SuppressWarnings({"checkstyle:JavaNCSS", "checkstyle:ExecutableStatementCount"})
    void testIsValid() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        assertTrue(playerMatchStat.isValid());

        playerMatchStat.setPlayedPosition(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setPlayedPosition("");
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setPlayedPosition("1234");
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setPlayedPosition("POS");

        playerMatchStat.setLineup(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);

        playerMatchStat.setSubstitutionOnTime(-1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setSubstitutionOnTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setSubstitutionOnTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setSubstitutionOffTime(-1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setYellowCardTime(-1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setYellowCardTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setYellowCardTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setRedCardTime(-1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setRedCardTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setRedCardTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setPlayer(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setPlayer(new Player());

        playerMatchStat.setMatch(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setMatch(new Match());

        playerMatchStat.setD11Match(null);
        assertTrue(playerMatchStat.isValid());
        playerMatchStat.setD11Match(new D11Match());

        playerMatchStat.setTeam(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setTeam(new Team());

        playerMatchStat.setD11Team(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setD11Team(new D11Team());

        playerMatchStat.setPosition(null);
        assertFalse(playerMatchStat.isValid());
        playerMatchStat.setPosition(new Position());

        assertTrue(playerMatchStat.isValid());
    }

    /**
     * Tests PlayerMatchStat::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.setLineup(Lineup.DID_NOT_PARTICIPATE);
        playerMatchStat.setSubstitutionOnTime(0);

        assertFalse(playerMatchStat.isParticipant());

        playerMatchStat.setLineup(Lineup.SUBSTITUTE);

        assertFalse(playerMatchStat.isParticipant());

        playerMatchStat.setSubstitutionOnTime(1);

        assertTrue(playerMatchStat.isParticipant());

        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);
        playerMatchStat.setSubstitutionOnTime(0);

        assertTrue(playerMatchStat.isParticipant());
    }

    /**
     * Tests PlayerMatchStat::getMinutesPlayed.
     */
    @Test
    void testGetMinutesPlayed() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.setLineup(Lineup.DID_NOT_PARTICIPATE);
        playerMatchStat.setSubstitutionOnTime(0);
        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(0, playerMatchStat.getMinutesPlayed());

        playerMatchStat.setLineup(Lineup.SUBSTITUTE);
        playerMatchStat.setSubstitutionOnTime(0);
        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(0, playerMatchStat.getMinutesPlayed());

        playerMatchStat.setSubstitutionOnTime(1);
        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1, playerMatchStat.getMinutesPlayed());

        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME - 1);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1 - 1, playerMatchStat.getMinutesPlayed());

        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);
        playerMatchStat.setSubstitutionOnTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1, playerMatchStat.getMinutesPlayed());

        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME, playerMatchStat.getMinutesPlayed());
    }

    /**
     * Tests PlayerMatchStat::reset.
     */
    @Test
    void testReset() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.getPosition().setDefender(false);

        playerMatchStat.reset();

        assertEquals("NA", playerMatchStat.getPlayedPosition());
        assertEquals(Lineup.DID_NOT_PARTICIPATE, playerMatchStat.getLineup());
        assertEquals(0, playerMatchStat.getSubstitutionOnTime());
        assertEquals(0, playerMatchStat.getSubstitutionOffTime());
        assertEquals(0, playerMatchStat.getYellowCardTime());
        assertEquals(0, playerMatchStat.getRedCardTime());
        assertFalse(playerMatchStat.isManOfTheMatch());
        assertFalse(playerMatchStat.isSharedManOfTheMatch());
        assertEquals(0, playerMatchStat.getPoints());

        playerMatchStat.getPosition().setDefender(true);
        playerMatchStat.reset();

        assertEquals(-1, playerMatchStat.getPoints());
    }

}
