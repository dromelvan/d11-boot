package org.d11.boot.application.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Card tests.
 */
public class CardTests extends D11EasyRandomTests {

    /**
     * Tests card validity.
     */
    @Test
    public void isValid() {
        final Card card = generate(Card.class);

        assertTrue(card.isValid(), "New card should be valid.");

        card.setTime(-1);
        assertFalse(card.isValid(), "Negative time should not be valid.");
        card.setTime(MatchEvent.MAX_MATCH_EVENT_TIME + 1);
        assertFalse(card.isValid(), "Too high time should not be valid.");
        card.setTime(0);

        card.setAddedTime(-1);
        assertFalse(card.isValid(), "Negative added time should not be valid.");
        card.setAddedTime(0);

        card.setMatch(null);
        assertFalse(card.isValid(), "Null match should not be valid.");
        card.setMatch(new Match());

        card.setTeam(null);
        assertFalse(card.isValid(), "Null team should not be valid.");
        card.setTeam(new Team());

        card.setPlayer(null);
        assertFalse(card.isValid(), "Null player should not be valid.");
        card.setPlayer(new Player());

        card.setCardType(null);
        assertFalse(card.isValid(), "Null card type should not be valid.");
        card.setCardType(CardType.YELLOW);

        assertTrue(card.isValid(), "Card should be valid.");
    }

}
