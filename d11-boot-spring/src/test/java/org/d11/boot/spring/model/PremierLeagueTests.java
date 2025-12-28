package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Premier League tests.
 */
class PremierLeagueTests extends EasyRandomTests {

    /**
     * Tests PremierLeague::isValid.
     */
    @Test
    void testIsValid() {
        final PremierLeague premierLeague = generate(PremierLeague.class);

        assertTrue(premierLeague.isValid());

        premierLeague.setName("");
        assertFalse(premierLeague.isValid());
        premierLeague.setName(null);
        assertFalse(premierLeague.isValid());
        premierLeague.setName("Name");

        premierLeague.setSeason(null);
        assertFalse(premierLeague.isValid());
        premierLeague.setSeason(new Season());

        assertTrue(premierLeague.isValid());
    }

}
