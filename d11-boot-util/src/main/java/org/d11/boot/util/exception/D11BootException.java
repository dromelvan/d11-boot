package org.d11.boot.util.exception;

import lombok.Getter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Base class for internal D11 Boot exceptions.
 */
public class D11BootException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6674434957011485568L;

    /**
     * The date and time the exception happened.
     */
    @Getter
    private final LocalDateTime dateTime = LocalDateTime.now();

    /**
     * Error code representation of the error.
     */
    @Getter
    private final ErrorCode errorCode;

    /**
     * Creates a new exception.
     *
     * @param message The exception message.
     */
    public D11BootException(final String message) {
        super(message);
        // Eventually all exceptions should be initialized with error code and this constructor should be removed
        this.errorCode = ErrorCode.DEFAULT;
    }

    /**
     * Creates a new exception.
     *
     * @param errorCode The exception error code.
     */
    public D11BootException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
