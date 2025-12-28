package org.d11.boot.spring.model.validation;

import org.d11.boot.spring.model.D11Entity;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * D11 entity validator tests.
 */
class D11EntityValidatorTests {

    /**
     * Tests D11EntityValidator::isValid.
     */
    @Test
    void testIsValid() {
        final D11EntityValidator validator = new D11EntityValidator();

        assertFalse(validator.isValid(null, null));

        final D11Entity d11Entity = new D11Entity();
        d11Entity.setUpdatedAt(d11Entity.getCreatedAt().minusMinutes(1L));

        assertFalse(validator.isValid(d11Entity, null));

        d11Entity.setUpdatedAt(d11Entity.getCreatedAt().plusMinutes(1L));

        assertTrue(validator.isValid(d11Entity, null));
    }

}
