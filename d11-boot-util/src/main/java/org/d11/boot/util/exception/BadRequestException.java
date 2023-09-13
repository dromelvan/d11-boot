package org.d11.boot.util.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * Exception thrown when a request parameter is missing or otherwise invalid.
 */
public class BadRequestException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 1552381069478461698L;

    /**
     * Name of the parameter that was invalid.
     */
    @Getter
    private final String parameter;

    /**
     * Creates a new Bad Request exception.
     *
     * @param parameter Name of the parameter that is missing or otherwise invalid.
     * @param message Exception message.
     */
    public BadRequestException(final String parameter, final String message) {
        super(message);
        this.parameter = parameter;
    }

}
