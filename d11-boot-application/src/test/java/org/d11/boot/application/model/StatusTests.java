package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Status tests.
 */
public class StatusTests {

    /**
     * Tests status name.
     */
    @Test
    public void name() {
        for(final Status status : Status.values()) {
            assertEquals(status.getName(), status.toString(), "Name should equal ToString.");
        }
    }

    /**
     * Tests status withId() method.
     */
    @Test
    public void withId() {
        for(final Status status : Status.values()) {
            assertEquals(status, Status.withId(status.getId()), "WithId should return correct status.");
        }
        assertNull(Status.withId(-1), "WithId should return null for non existing id.");
    }

}
