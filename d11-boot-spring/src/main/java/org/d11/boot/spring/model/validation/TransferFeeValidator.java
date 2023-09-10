package org.d11.boot.spring.model.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.d11.boot.spring.model.Transfer;

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
     * Gets transfer fee validity status. The fee has to be positive and evenly dividable by 5.
     *
     * @param transferFee The transfer fee.
     * @return True if the fee is valid, false otherwise.
     */
    public boolean isValid(final Integer transferFee) {
        return transferFee != null
                && transferFee > 0
                && transferFee % Transfer.FEE_DIVISOR == 0;
    }

}
