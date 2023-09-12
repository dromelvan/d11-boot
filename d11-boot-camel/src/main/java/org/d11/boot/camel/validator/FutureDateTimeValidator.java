package org.d11.boot.camel.validator;

import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

/**
 * Validator that asserts that a datetime is in the future.
 */
public class FutureDateTimeValidator extends NotMissingValidator<ValidFutureDateTime, LocalDateTime> {

    @Override
    public boolean isValid(final LocalDateTime localDateTime,
                           final ConstraintValidatorContext constraintValidatorContext) {
        return super.isValid(localDateTime, constraintValidatorContext)
                && localDateTime.isAfter(LocalDateTime.now());
    }

}
