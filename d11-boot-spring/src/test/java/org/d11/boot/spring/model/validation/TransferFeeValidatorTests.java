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

        assertFalse(validator.isValid(null), "TransferFeeValidator::isValid null");
        assertFalse(validator.isValid(-1), "TransferFeeValidator::isValid negative");
        assertFalse(validator.isValid(0), "TransferFeeValidator::isValid non positive");

        for (int i = 1; i <= Transfer.FEE_DIVISOR * Transfer.FEE_DIVISOR; ++i) {
            if (i % Transfer.FEE_DIVISOR == 0) {
                assertTrue(validator.isValid(i), "TransferFeeValidator::isValid divisible by " + Transfer.FEE_DIVISOR);
            } else {
                assertFalse(validator.isValid(i),
                            "TransferFeeValidator::isValid not divisible by " + Transfer.FEE_DIVISOR);
            }
        }
    }

}
