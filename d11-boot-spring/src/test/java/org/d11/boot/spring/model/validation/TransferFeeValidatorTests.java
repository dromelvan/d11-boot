package org.d11.boot.spring.model.validation;

import org.d11.boot.spring.model.Transfer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Transfer fee validator tests.
 */
class TransferFeeValidatorTests {

    /**
     * Tests TransferFeeValidator::isValid.
     */
    @Test
    void testIsValid() {
        final TransferFeeValidator validator = new TransferFeeValidator();

        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(-1));
        assertFalse(validator.isValid(0));

        for (int i = 1; i <= Transfer.FEE_DIVISOR * Transfer.FEE_DIVISOR; ++i) {
            if (i % Transfer.FEE_DIVISOR == 0) {
                assertTrue(validator.isValid(i));
            } else {
                assertFalse(validator.isValid(i));
            }
        }
    }

}
