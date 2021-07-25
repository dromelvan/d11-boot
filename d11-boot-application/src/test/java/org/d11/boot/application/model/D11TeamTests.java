package org.d11.boot.application.model;

import org.d11.boot.api.model.D11TeamDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 team tests.
 */
//@SuppressWarnings("checkstyle:ExecutableStatementCount")
public class D11TeamTests extends D11EasyRandomTests {

    /**
     * Tests D11 team validity.
     */
    @Test
    public void isValid() {
        final D11Team d11Team = generate(D11Team.class);

        assertTrue(d11Team.isValid(), "New D11 team should be valid.");

        d11Team.setName("");
        assertFalse(d11Team.isValid(), "Empty name should not be valid.");
        d11Team.setName(null);
        assertFalse(d11Team.isValid(), "Null name should not be valid.");
        d11Team.setName("Name");

        d11Team.setShortName("");
        assertFalse(d11Team.isValid(), "Empty shortName should not be valid.");
        d11Team.setShortName(null);
        assertFalse(d11Team.isValid(), "Null shortName should not be valid.");
        d11Team.setShortName("ShortName");

        d11Team.setCode("");
        assertFalse(d11Team.isValid(), "Empty code should not be valid.");
        d11Team.setCode(null);
        assertFalse(d11Team.isValid(), "Null code should not be valid.");
        d11Team.setCode("ABCD");
        assertFalse(d11Team.isValid(), "Too long code should not be valid.");
        d11Team.setCode("AB");
        assertFalse(d11Team.isValid(), "Too short code should not be valid.");
        d11Team.setCode("ABC");

        d11Team.setCoOwner(null);
        assertTrue(d11Team.isValid(), "Null co owner should not be valid.");
        d11Team.setOwner(new User());

        assertTrue(d11Team.isValid(), "D11 team should be valid.");
    }

    /**
     * Tests mapping between Team and TeamDTO.
     */
    @Test
    public void map() {
        final D11Team d11Team = generate(D11Team.class);

        final D11TeamDTO d11TeamDTO = map(d11Team, D11TeamDTO.class);
        final D11Team mappedD11Team = map(d11TeamDTO, D11Team.class);

        assertEquals(d11Team, mappedD11Team, "D11 team should equal mapped D11 team.");
    }
}
