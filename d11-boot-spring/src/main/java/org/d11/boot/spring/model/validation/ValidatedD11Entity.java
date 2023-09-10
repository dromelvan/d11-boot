package org.d11.boot.spring.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for validated D11 entities.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = D11EntityValidator.class)
public @interface ValidatedD11Entity {

    /**
     * Error message for an invalid entity.
     *
     * @return Error message for an invalid entity.
     */
    String message() default "Invalid entity";

    /**
     * Parameter for customizing the targeted groups.
     *
     * @return Targeted groups.
     */
    Class<?>[] groups() default {};

    /**
     * Parameter for extensibility purposes.
     *
     * @return Payload.
     */
    Class<? extends Payload>[] payload() default {};

}
