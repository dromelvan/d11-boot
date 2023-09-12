package org.d11.boot.camel.validator;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Future date time validator tests.
 */
class FutureDateTimeValidatorTests {

    /**
     * Tests FutureDateTimeValidator::isValid.
     */
    @Test
    void testTsValid() {
        final FutureDateTimeValidator validator = new FutureDateTimeValidator();

        assertFalse(validator.isValid(null, null), "FutureDateTimeValidator::isValid null");
        assertFalse(validator.isValid(LocalDateTime.now(), null), "FutureDateTimeValidator::isValid now");
        assertTrue(validator.isValid(LocalDateTime.now().plusDays(1L), null),
                   "FutureDateTimeValidator::isValid tomorrow");
    }

}
