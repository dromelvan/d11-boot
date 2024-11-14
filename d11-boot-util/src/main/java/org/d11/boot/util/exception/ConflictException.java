package org.d11.boot.util.exception;

import java.io.Serial;

/**
 * Exception thrown when there is a conflict between the current state of the application and some changes that a
 * request is trying to make.
 */
public class ConflictException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 380167986637606100L;

    /**
     * Creates a new Conflict exception.
     *
     * @param message Exception message.
     */
    public ConflictException(final String message) {
        super(message);
    }

    /**
     * Creates a new Conflict exception.
     *
     * @param errorCode The exception error code.
     */
    public ConflictException(final ErrorCode errorCode) {
        super(errorCode);
    }

}
