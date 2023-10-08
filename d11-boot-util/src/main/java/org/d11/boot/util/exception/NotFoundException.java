package org.d11.boot.util.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * Exception thrown when a requested resource can't be found.
 */
public class NotFoundException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 6333560520228645897L;

    /**
     * Message template.
     */
    private static final String MESSAGE = "%s with id %s was not found";

    /**
     * Resource id.
     */
    @Getter
    private final long id;

    /**
     * Resource name.
     */
    @Getter
    private final String resource;

    /**
     * Creates a new Not Found exception.
     *
     * @param id    ID of the resource that was not found.
     * @param clazz Class of the resource that was not found.
     */
    public NotFoundException(final long id, final Class<?> clazz) {
        super(String.format(MESSAGE, clazz.getSimpleName(), id));
        this.id = id;
        this.resource = clazz.getSimpleName();
    }

}
