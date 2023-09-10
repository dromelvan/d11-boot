package org.d11.boot.spring.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.d11.boot.spring.model.D11Entity;

/**
 * Validates the created and updated timestamps of a database entity.
 */
public class D11EntityValidator implements ConstraintValidator<ValidatedD11Entity, D11Entity> {

    @Override
    public boolean isValid(final D11Entity d11Entity, final ConstraintValidatorContext context) {
        return d11Entity != null
               && !d11Entity.getCreatedAt().isAfter(d11Entity.getUpdatedAt());
    }

}
