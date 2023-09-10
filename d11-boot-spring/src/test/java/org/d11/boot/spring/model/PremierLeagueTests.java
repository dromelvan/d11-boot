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
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final PremierLeague premierLeague = generate(PremierLeague.class);

        assertTrue(premierLeague.isValid(), "PremierLeague::isValid");

        premierLeague.setName("");
        assertFalse(premierLeague.isValid(), "PremierLeague::isValid name empty");
        premierLeague.setName(null);
        assertFalse(premierLeague.isValid(), "PremierLeague::isValid name null");
        premierLeague.setName("Name");

        premierLeague.setSeason(null);
        assertFalse(premierLeague.isValid(), "PremierLeague::isValid season null");
        premierLeague.setSeason(new Season());

        assertTrue(premierLeague.isValid(), "PremierLeague::isValid valid");
    }

}
