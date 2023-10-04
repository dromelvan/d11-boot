package org.d11.boot.util.exception;

import java.io.Serial;

/**
 * Exception thrown when a request lacks valid authentication credentials for the requested resource.
 */
public class UnauthorizedException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 7481829637770745990L;

    /**
     * Creates a new Unauthorized exception.
     */
    public UnauthorizedException() {
        super("Unauthorized");
    }

    /**
     * Creates a new Unauthorized exception.
     *
     * @param message Exception message.
     */
    public UnauthorizedException(final String message) {
        super(message);
    }

}
