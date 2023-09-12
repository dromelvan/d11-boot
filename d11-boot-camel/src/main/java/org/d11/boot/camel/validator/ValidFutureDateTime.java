package org.d11.boot.camel.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that adds a valid future datetime constraint to a field.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateTimeValidator.class)
@Documented
public @interface ValidFutureDateTime {

    /**
     * Error message for invalid fields.
     *
     * @return Error message for invalid fields.
     */
    String message() default "must be in the future";

    /**
     * Groups for validation restriction. Required but not used.
     *
     * @return Groups for validation restriction.
     */
    Class<?>[] groups() default {};

    /**
     * Payloads for bean validation API clients. Required but not used.
     *
     * @return Payloads for bean validation API clients.
     */
    Class<? extends Payload>[] payload() default {};

}
