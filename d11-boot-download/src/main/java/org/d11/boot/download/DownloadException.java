package org.d11.boot.download;

import java.io.Serial;

/**
 * Exception thrown when downloading fails in an unrecoverable way.
 */
public class DownloadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2861281082887058108L;

    /**
     * Creates a new download exception.
     *
     * @param message Exception message.
     */
    public DownloadException(final String message) {
        super(message);
    }

    /**
     * Creates a new download exception.
     *
     * @param message Exception message.
     * @param cause   Exception cause.
     */
    public DownloadException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
