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
    @SuppressWarnings({"DataFlowIssue", "checkstyle:JavaNCSS", "checkstyle:ExecutableStatementCount"})
    void testIsValid() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        assertTrue(playerMatchStat.isValid(), "PlayerMatchStat::isValid");

        playerMatchStat.setPlayedPosition(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid played position null");
        playerMatchStat.setPlayedPosition("");
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid played position empty");
        playerMatchStat.setPlayedPosition("1234");
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid played position too long");
        playerMatchStat.setPlayedPosition("POS");

        playerMatchStat.setLineup(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid lineup null");
        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);

        playerMatchStat.setSubstitutionOnTime(-1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid substitution on time negative");
        playerMatchStat.setSubstitutionOnTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid substitution on time too high");
        playerMatchStat.setSubstitutionOnTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setSubstitutionOffTime(-1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid substitution off time negative");
        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid substitution off time too high");
        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setYellowCardTime(-1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid yellow card time negative");
        playerMatchStat.setYellowCardTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid yellow card time too high");
        playerMatchStat.setYellowCardTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setRedCardTime(-1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid red card time negative");
        playerMatchStat.setRedCardTime(PlayerMatchStat.MAX_MATCH_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid red card time too high");
        playerMatchStat.setRedCardTime(PlayerMatchStat.MAX_MATCH_TIME);

        playerMatchStat.setPlayer(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid player null");
        playerMatchStat.setPlayer(new Player());

        playerMatchStat.setMatch(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid match null");
        playerMatchStat.setMatch(new Match());

        playerMatchStat.setD11Match(null);
        assertTrue(playerMatchStat.isValid(), "PlayerMatchStat::isValid D11 match null");
        playerMatchStat.setD11Match(new D11Match());

        playerMatchStat.setTeam(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid team null");
        playerMatchStat.setTeam(new Team());

        playerMatchStat.setD11Team(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid D11 team null");
        playerMatchStat.setD11Team(new D11Team());

        playerMatchStat.setPosition(null);
        assertFalse(playerMatchStat.isValid(), "PlayerMatchStat::isValid position null");
        playerMatchStat.setPosition(new Position());

        assertTrue(playerMatchStat.isValid(), "PlayerMatchStat::isValid valid");
    }

    /**
     * Tests PlayerMatchStat::isParticipant.
     */
    @Test
    void testIsParticipant() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.setLineup(Lineup.DID_NOT_PARTICIPATE);
        playerMatchStat.setSubstitutionOnTime(0);

        assertFalse(playerMatchStat.isParticipant(),
                    "PlayerMatchStat::isParticipant lineup DID_NOT_PARTICIPATE and substitution on time 0");

        playerMatchStat.setLineup(Lineup.SUBSTITUTE);

        assertFalse(playerMatchStat.isParticipant(),
                    "PlayerMatchStat::isParticipant lineup SUBSTITUTE and substitution on time 0");

        playerMatchStat.setSubstitutionOnTime(1);

        assertTrue(playerMatchStat.isParticipant(),
                   "PlayerMatchStat::isParticipant lineup SUBSTITUTE and substitution on time positive");

        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);
        playerMatchStat.setSubstitutionOnTime(0);

        assertTrue(playerMatchStat.isParticipant(),
                   "PlayerMatchStat::isParticipant lineup STARTING_LINEUP and substitution on time 0");
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

        assertEquals(0, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup DID_NOT_PARTICIPATE and on time 0 and off time 0");

        playerMatchStat.setLineup(Lineup.SUBSTITUTE);
        playerMatchStat.setSubstitutionOnTime(0);
        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(0, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup SUBSTITUTE and on time 0 and off time 0");

        playerMatchStat.setSubstitutionOnTime(1);
        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup SUBSTITUTE and on time 1 and off time 0");

        playerMatchStat.setSubstitutionOffTime(PlayerMatchStat.MAX_MATCH_TIME - 1);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1 - 1, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup SUBSTITUTE and on time 1 and off time 89");

        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);
        playerMatchStat.setSubstitutionOnTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME - 1, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup STARTING_LINEUP and on time 0 and off time 89");

        playerMatchStat.setSubstitutionOffTime(0);

        assertEquals(PlayerMatchStat.MAX_MATCH_TIME, playerMatchStat.getMinutesPlayed(),
                     "PlayerMatchStat::getMinutesPlayed lineup STARTING_LINEUP and on time 0 and off time 0");
    }

    /**
     * Tests PlayerMatchStat::reset.
     */
    @Test
    void testReset() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.getPosition().setDefender(false);

        playerMatchStat.reset();

        assertEquals("NA", playerMatchStat.getPlayedPosition(), "PlayerMatchStat::reset played position equals");
        assertEquals(Lineup.DID_NOT_PARTICIPATE, playerMatchStat.getLineup(), "PlayerMatchStat::reset lineup equals");
        assertEquals(0, playerMatchStat.getSubstitutionOnTime(), "PlayerMatchStat::reset substitution on time equals");
        assertEquals(0, playerMatchStat.getSubstitutionOffTime(),
                     "PlayerMatchStat::reset substitution off time equals");
        assertEquals(0, playerMatchStat.getYellowCardTime(), "PlayerMatchStat::reset yellow card time equals");
        assertEquals(0, playerMatchStat.getRedCardTime(), "PlayerMatchStat::reset red card time equals");
        assertFalse(playerMatchStat.isManOfTheMatch(), "PlayerMatchStat::reset man of the match");
        assertFalse(playerMatchStat.isSharedManOfTheMatch(), "PlayerMatchStat::reset shared man of the match");
        assertEquals(0, playerMatchStat.getPoints(), "PlayerMatchStat::reset points non defender equals");

        playerMatchStat.getPosition().setDefender(true);
        playerMatchStat.reset();

        assertEquals(-1, playerMatchStat.getPoints(), "PlayerMatchStat::reset points defender equals");
    }

}
