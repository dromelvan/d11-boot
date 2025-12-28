package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 Team tests.
 */
class D11TeamTests extends EasyRandomTests {

    /**
     * Tests D11Team::isValid.
     */
    @Test
    void testIsValid() {
        final D11Team d11Team = generate(D11Team.class);

        assertTrue(d11Team.isValid());

        d11Team.setName("");
        assertFalse(d11Team.isValid());
        d11Team.setName(null);
        assertFalse(d11Team.isValid());
        d11Team.setName("Name");

        d11Team.setShortName("");
        assertFalse(d11Team.isValid());
        d11Team.setShortName(null);
        assertFalse(d11Team.isValid());
        d11Team.setShortName("ShortName");

        d11Team.setCode("");
        assertFalse(d11Team.isValid());
        d11Team.setCode(null);
        assertFalse(d11Team.isValid());
        d11Team.setCode("ABCD");
        assertFalse(d11Team.isValid());
        d11Team.setCode("AB");
        assertFalse(d11Team.isValid());
        d11Team.setCode("ABC");

        d11Team.setCoOwner(null);
        assertTrue(d11Team.isValid());
        d11Team.setOwner(new User());

        assertTrue(d11Team.isValid());
    }

    /**
     * Tests D11Team::isAdministratedBy.
     */
    @Test
    void testIsAdministratedBy() {
        final D11Team d11Team = generate(D11Team.class);

        final User owner = d11Team.getOwner()
                .setAdministrator(false);
        final User coOwner = d11Team.getCoOwner()
                .setAdministrator(false);
        final User user = new User();
        final User administrator = new User()
                .setAdministrator(true);

        assertTrue(d11Team.isAdministratedBy(owner));
        assertTrue(d11Team.isAdministratedBy(coOwner));
        assertFalse(d11Team.isAdministratedBy(user));
        assertTrue(d11Team.isAdministratedBy(administrator));
    }

}
