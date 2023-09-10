package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 League tests.
 */
class D11LeagueTests extends EasyRandomTests {

    /**
     * Tests D11League::isValid.
     */
    @Test
    @SuppressWarnings("DataFlowIssue")
    void testIsValid() {
        final D11League d11League = generate(D11League.class);

        assertTrue(d11League.isValid(), "D11League::isValid");

        d11League.setName("");
        assertFalse(d11League.isValid(), "D11League::isValid name empty");
        d11League.setName(null);
        assertFalse(d11League.isValid(), "D11League::isValid name null");
        d11League.setName("Name");

        d11League.setSeason(null);
        assertFalse(d11League.isValid(), "D11League::isValid season null");
        d11League.setSeason(new Season());

        assertTrue(d11League.isValid(), "D11League::isValid valid");
    }

}
