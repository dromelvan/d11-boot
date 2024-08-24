package org.d11.boot.util.exception;

/**
 * Validation error information holder.
 *
 * @param property Property name.
 * @param error    Validation error.
 */
public record ValidationError(String property, String error) {

}
