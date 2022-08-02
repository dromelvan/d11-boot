package org.d11.boot.application.model.validation;

import org.d11.boot.application.model.Transfer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates a transfer fee, making sure it is positive and divisible by 0.5.
 */
public class TransferFeeValidator implements ConstraintValidator<TransferFee, Integer> {

    @Override
    public void initialize(final TransferFee transferFee) {
        // No need for initialization
    }

    @Override
    public boolean isValid(final Integer transferFee, final ConstraintValidatorContext constraintValidatorContext) {
        return transferFee > 0
                && transferFee % Transfer.FEE_DIVISOR == 0;
    }

}
