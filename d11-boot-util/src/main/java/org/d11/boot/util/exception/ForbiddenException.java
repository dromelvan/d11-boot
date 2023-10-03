package org.d11.boot.util.exception;

import java.io.Serial;

/**
 * Exception thrown when the server understands the request but refuses to authorize it.
 */
public class ForbiddenException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 4893759990063074047L;

    /**
     * Creates a new Forbidden exception.
     */
    public ForbiddenException() {
        super("Forbidden");
    }

    /**
     * Creates a new Forbidden exception.
     *
     * @param message Exception message.
     */
    public ForbiddenException(final String message) {
        super(message);
    }

}
