package org.d11.boot.application.model.validation;

import org.d11.boot.application.model.D11Entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates the created and updated timestamps of a database entity.
 */
public class D11EntityValidator implements ConstraintValidator<ValidatedD11Entity, D11Entity> {

    @Override
    public boolean isValid(final D11Entity d11Entity, final ConstraintValidatorContext context) {
        return d11Entity != null
               && d11Entity.getCreatedAt() != null
               && d11Entity.getUpdatedAt() != null
               && !d11Entity.getCreatedAt().isAfter(d11Entity.getUpdatedAt());
    }

}
