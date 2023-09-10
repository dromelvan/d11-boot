package org.d11.boot.spring.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for fields containing transfer fee integers.
 */
@Documented
@Constraint(validatedBy = TransferFeeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransferFee {

    /**
     * Error message for an invalid field.
     *
     * @return Error message for an invalid field.
     */
    String message() default "Invalid transfer fee";

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
