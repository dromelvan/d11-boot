package org.d11.boot.application.util;

import org.d11.boot.application.D11BootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown when a request will not be processed due to client error.
 * Results in a HttpStatus.BAD_REQUEST response if not handled.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public static final long serialVersionUID = D11BootApplication.VERSION;

    /**
     * Creates a new exception.
     */
    public BadRequestException() {
        super("Bad Request");
    }

    /**
     * Creates a new exception.
     *
     * @param message Exception message.
     */
    public BadRequestException(final String message) {
        super(message);
    }

}
