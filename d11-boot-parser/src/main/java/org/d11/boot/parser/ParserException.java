package org.d11.boot.parser;

import java.io.Serial;

/**
 * Exception thrown when parsing fails in an unrecoverable way.
 */
public class ParserException extends Exception {

    @Serial
    private static final long serialVersionUID = 138602076934374906L;

    /**
     * Creates a new parser exception.
     *
     * @param message Exception message.
     */
    public ParserException(final String message) {
        super(message);
    }

    /**
     * Creates a new parser exception.
     *
     * @param source Source exception.
     */
    public ParserException(final Exception source) {
        super(source);
    }

    /**
     * Creates a new parser exception by formatting the message.
     *
     * @param message Exception message that will be formatted.
     * @param args    Arguments that will be formatted into the message.
     */
    public ParserException(final String message, final Object... args) {
        super(String.format(message, args));
    }

}
