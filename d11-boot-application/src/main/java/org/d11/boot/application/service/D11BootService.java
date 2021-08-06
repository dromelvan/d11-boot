package org.d11.boot.application.service;

import org.apache.commons.lang3.StringUtils;
import org.d11.boot.application.model.D11Entity;
import org.d11.boot.application.util.MappingProvider;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for services.
 */
public class D11BootService extends MappingProvider {

    /**
     * Validates provided entities.
     *
     * @param entities The entities that will be provided.
     * @return List of validation error messages.
     */
    protected List<String> getValidationErrors(final D11Entity... entities) {
        final List<String> errors = new ArrayList<>();
        for(final D11Entity entity : entities) {
            for(final ConstraintViolation<D11Entity> constraintViolation : entity.validate()) {
                errors.add(StringUtils.capitalize(constraintViolation.getPropertyPath().toString()) + " " + constraintViolation.getMessage());
            }
        }
        return errors;
    }

}
