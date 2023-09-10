package org.d11.boot.spring.model.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Year interval validator tests.
 */
class YearIntervalValidatorTests {

    /**
     * Tests YearIntervalValidator::isValid.
     */
    @Test
    void testIsValid() {
        final YearIntervalValidator validator = new YearIntervalValidator();

        assertFalse(validator.isValid("FOO", null), "YearIntervalValidator::isValid pattern invalid");
        assertFalse(validator.isValid("1234-4321", null), "YearIntervalValidator::isValid interval invalid");
        assertTrue(validator.isValid("1234-1235", null), "earIntervalValidator::isValid valid");
    }

}
