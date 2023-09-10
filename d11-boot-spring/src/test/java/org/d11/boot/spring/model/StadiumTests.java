package org.d11.boot.spring.model;

import org.d11.boot.spring.EasyRandomTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Stadium tests.
 */
class StadiumTests extends EasyRandomTests {

    /**
     * Tests Stadium::isValid.
     */
    @Test
    void testIsValid() {
        final Stadium stadium = generate(Stadium.class);

        assertTrue(stadium.isValid(), "Stadium::isValid");

        stadium.setName("");
        assertFalse(stadium.isValid(), "Stadium::isValid name empty");
        stadium.setName(null);
        assertFalse(stadium.isValid(), "Stadium::isValid name null");
        stadium.setName("Name");

        stadium.setCity("");
        assertFalse(stadium.isValid(), "Stadium::isValid city empty");
        stadium.setCity(null);
        assertFalse(stadium.isValid(), "Stadium::isValid city null");
        stadium.setCity("City");

        stadium.setCapacity(0);
        assertFalse(stadium.isValid(), "Stadium::isValid capacity not positive");
        stadium.setCapacity(1);

        stadium.setOpened(Stadium.MIN_OPENED_YEAR - 1);
        assertFalse(stadium.isValid(), "Stadium::isValid year too low");
        stadium.setOpened(Stadium.MAX_OPENED_YEAR + 1);
        assertFalse(stadium.isValid(), "Stadium::isValid year too high");
        stadium.setOpened(Stadium.MIN_OPENED_YEAR);

        assertTrue(stadium.isValid(), "Stadium::isValid valid");
    }

}
