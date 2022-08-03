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
        return isValid(transferFee);
    }

    /**
     * Checks if an integer is a valid fee.
     *
     * @param transferFee The fee that will be validated.
     * @return True if the fee is valid, false otherwise.
     */
    public boolean isValid(final Integer transferFee) {
        return transferFee != null
                && transferFee > 0
                && transferFee % Transfer.FEE_DIVISOR == 0;
    }

}
