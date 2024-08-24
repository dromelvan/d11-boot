package org.d11.boot.util.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Exception thrown when a request parameter is missing or otherwise invalid.
 */
public class BadRequestException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 1552381069478461698L;

    /**
     * Validation errors.
     */
    private final List<ValidationError> validationErrors = new ArrayList<>();

    /**
     * Creates a new Bad Request exception.
     *
     * @param parameter Name of the parameter that is missing or otherwise invalid.
     * @param message   Exception message.
     */
    public BadRequestException(final String parameter, final String message) {
        super(message);
        this.validationErrors.add(new ValidationError(parameter, message));
    }

    /**
     * Creates a new Bad Request exception.
     *
     * @param message          Exception message.
     * @param validationErrors List of validation messages.
     */
    public BadRequestException(final String message, final List<ValidationError> validationErrors) {
        super(message);
        this.validationErrors.addAll(validationErrors);
    }

    /**
     * Gets a list of validation errors.
     *
     * @return List of validation errors.
     */
    public List<ValidationError> getValidationErrors() {
        return new ArrayList<>(this.validationErrors);
    }

    /**
     * Gets the name of the bad parameter. This only really makes sense if there's only one validation error.
     *
     * @return Name of the bad parameter.
     */
    public String getParameter() {
        return this.validationErrors.stream()
                .findAny()
                .map(ValidationError::property)
                .orElse(null);
    }

}
