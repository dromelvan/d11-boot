package org.d11.boot.camel.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;

/**
 * Validator that asserts that a value is not missing.
 *
 * @param <T> Annotation type of the validator.
 * @param <U> Type of the object that will be validated.
 */
public class NotMissingValidator<T extends Annotation, U> implements ConstraintValidator<T, U> {

    @Override
    public boolean isValid(final U object, final ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof String) {
            return StringUtils.isNotBlank((String) object);
        }
        return object != null;
    }

}
