package org.d11.boot.application.util;

import org.d11.boot.application.D11BootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown when a user is authorized but not allowed to perform an action.
 * Results in a HttpStatus.FORBIDDEN response if not handled.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    public static final long serialVersionUID = D11BootApplication.VERSION;

    /**
     * Creates a new exception.
     */
    public ForbiddenException() {
        super("Forbidden");
    }

    /**
     * Creates a new exception.
     *
     * @param message Exception message.
     */
    public ForbiddenException(final String message) {
        super(message);
    }

}
