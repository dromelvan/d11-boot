package org.d11.boot.application.model;

import org.d11.boot.application.model.validation.D11EntityValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

/**
 * D11 entity validator tests.
 */
public class D11EntityValidatorTests {

    /**
     * Tests base entity validity.
     */
    @Test
    public void isValid() {
        final D11EntityValidator d11EntityValidator = new D11EntityValidator();
        final D11Entity d11Entity = new D11Entity();
        final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

        assertFalse(d11EntityValidator.isValid(null, constraintValidatorContext), "Null entity should not be valid.");

        d11Entity.setCreatedAt(LocalDateTime.now());
        d11Entity.setUpdatedAt(d11Entity.getCreatedAt().minus(1, ChronoUnit.MILLIS));
        assertFalse(d11EntityValidator.isValid(d11Entity, constraintValidatorContext), "Entity should be valid.");
    }

}
