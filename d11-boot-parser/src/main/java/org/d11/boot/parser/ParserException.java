package org.d11.boot.parser;

/**
 * Exception thrown when parsing fails in an unrecoverable way.
 */
public class ParserException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new parser exception.
     *
     * @param message Exception message.
     */
    public ParserException(final String message) {
        super(message);
    }

}
