package org.d11.boot.util.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Base class for internal D11 Boot exceptions.
 */
@NoArgsConstructor
public class D11BootException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6674434957011485568L;

    /**
     * The date and time the exception happened.
     */
    @Getter
    private final LocalDateTime dateTime = LocalDateTime.now();

    /**
     * Creates a new exception.
     *
     * @param message The exception message.
     */
    public D11BootException(final String message) {
        super(message);
    }

}
