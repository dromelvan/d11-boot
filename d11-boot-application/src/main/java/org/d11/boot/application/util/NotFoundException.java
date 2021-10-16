package org.d11.boot.application.util;

import org.d11.boot.D11BootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown when a resource could not be found.
 * Results in a HttpStatus.NOT_FOUND response if not handled.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public static final long serialVersionUID = D11BootApplication.VERSION;

    /**
     * Creates a new exception.
     */
    public NotFoundException() {
        super("Not Found");
    }

    /**
     * Creates a new exception.
     *
     * @param message Exception message.
     */
    public NotFoundException(final String message) {
        super(message);
    }

}
