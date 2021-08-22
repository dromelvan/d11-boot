package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.D11Match;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.Lineup;
import org.d11.boot.application.model.jpa.Match;
import org.d11.boot.application.model.jpa.MatchEvent;
import org.d11.boot.application.model.jpa.Player;
import org.d11.boot.application.model.jpa.PlayerMatchStat;
import org.d11.boot.application.model.jpa.Position;
import org.d11.boot.application.model.jpa.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Player match stat tests.
 */
public class PlayerMatchStatTests extends D11EasyRandomTests {

    /**
     * Tests player match stat validity.
     */
    @Test
    @SuppressWarnings({ "checkstyle:ExecutableStatementCount", "checkstyle:JavaNCSS" })
    public void isValid() {
        final PlayerMatchStat playerMatchStat = generate(PlayerMatchStat.class);

        playerMatchStat.prePersist();

        assertTrue(playerMatchStat.isValid(), "New player match stat should be valid.");

        playerMatchStat.setPlayedPosition(null);
        assertFalse(playerMatchStat.isValid(), "Null played position should not be valid.");
        playerMatchStat.setPlayedPosition("");
        assertFalse(playerMatchStat.isValid(), "Empty played position should not be valid.");
        playerMatchStat.setPlayedPosition("POS");

        playerMatchStat.setLineup(null);
        assertFalse(playerMatchStat.isValid(), "Null lineup should not be valid.");
        playerMatchStat.setLineup(Lineup.STARTING_LINEUP);

        playerMatchStat.setSubstitutionOnTime(-1);
        assertFalse(playerMatchStat.isValid(), "Negative substitution on time should not be valid.");
        playerMatchStat.setSubstitutionOnTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "Too high substitution on time should not be valid.");
        playerMatchStat.setSubstitutionOnTime(MatchEvent.MAX_MATCH_EVENT_TIME);

        playerMatchStat.setSubstitutionOffTime(-1);
        assertFalse(playerMatchStat.isValid(), "Negative substitution off time should not be valid.");
        playerMatchStat.setSubstitutionOffTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "Too high substitution off time should not be valid.");
        playerMatchStat.setSubstitutionOffTime(MatchEvent.MAX_MATCH_EVENT_TIME);

        playerMatchStat.setYellowCardTime(-1);
        assertFalse(playerMatchStat.isValid(), "Negative yellow card time should not be valid.");
        playerMatchStat.setYellowCardTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "Too high yellow card time should not be valid.");
        playerMatchStat.setYellowCardTime(MatchEvent.MAX_MATCH_EVENT_TIME);

        playerMatchStat.setRedCardTime(-1);
        assertFalse(playerMatchStat.isValid(), "Negative red card time should not be valid.");
        playerMatchStat.setRedCardTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(playerMatchStat.isValid(), "Too high red card time should not be valid.");
        playerMatchStat.setRedCardTime(MatchEvent.MAX_MATCH_EVENT_TIME);

        playerMatchStat.setPlayer(null);
        assertFalse(playerMatchStat.isValid(), "Null player should not be valid.");
        playerMatchStat.setPlayer(new Player());

        playerMatchStat.setMatch(null);
        assertFalse(playerMatchStat.isValid(), "Null match should not be valid.");
        playerMatchStat.setMatch(new Match());

        playerMatchStat.setD11Match(null);
        assertTrue(playerMatchStat.isValid(), "Null D11 match should be valid.");
        playerMatchStat.setD11Match(new D11Match());

        playerMatchStat.setTeam(null);
        assertFalse(playerMatchStat.isValid(), "Null team should not be valid.");
        playerMatchStat.setTeam(new Team());

        playerMatchStat.setD11Team(null);
        assertFalse(playerMatchStat.isValid(), "Null D11 team should not be valid.");
        playerMatchStat.setD11Team(new D11Team());

        playerMatchStat.setPosition(null);
        assertFalse(playerMatchStat.isValid(), "Null position should not be valid.");
        playerMatchStat.setPosition(new Position());

        assertTrue(playerMatchStat.isValid(), "Player match stat should be valid.");
    }

}
