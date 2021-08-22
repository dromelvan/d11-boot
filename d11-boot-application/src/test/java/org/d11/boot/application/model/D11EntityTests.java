package org.d11.boot.application.model;

import org.d11.boot.application.model.jpa.D11Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * D11 entity tests.
 */
public class D11EntityTests {

    /**
     * Tests pre persist functionality.
     */
    @Test
    public void prePersist() {
        final D11Entity d11Entity = new D11Entity();
        d11Entity.setCreatedAt(null);
        d11Entity.setUpdatedAt(null);

        d11Entity.prePersist();

        assertNotNull(d11Entity.getCreatedAt(), "PrePersist CreatedAt should not be null.");
        assertNotNull(d11Entity.getUpdatedAt(), "PrePersist UpdatedAt should not be null.");
        assertEquals(d11Entity.getCreatedAt(), d11Entity.getUpdatedAt(),
                     "PrePersist CreatedAt should equal UpdatedAt.");
    }

    /**
     * Tests pre update functionality.
     */
    @Test
    public void preUpdate() {
        final D11Entity d11Entity = new D11Entity();
        d11Entity.setUpdatedAt(null);

        d11Entity.preUpdate();

        assertNotNull(d11Entity.getUpdatedAt(), "PreUpdate UpdatedAt should not be null.");
    }

}
