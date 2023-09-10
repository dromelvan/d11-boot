package org.d11.boot.util.exception;

import org.apache.commons.lang3.StringUtils;

import java.io.Serial;

/**
 * Exception thrown when a requested resource can't be found.
 */
public class NotFoundException extends D11BootException {

    @Serial
    private static final long serialVersionUID = 6333560520228645897L;

    /**
     * Creates a new Not Found exception.
     */
    public NotFoundException() {
        super("Requested resource was not found.");
    }

    /**
     * Creates a new Not Found exception.
     *
     * @param message Exception message.
     */
    public NotFoundException(final String message) {
        super(message);
    }

    /**
     * Creates a new Not Found exception.
     *
     * @param id    Id of the resource that was not found.
     * @param clazz Class of the resource that was not found.
     */
    public NotFoundException(final long id, final Class<?> clazz) {
        super(getResourceName(clazz) + " with id " + id + " was not found.");
    }

    /**
     * Turns a TitleCasedClassName for a resource into "Title cased class name".
     *
     * @param clazz Class of the resource that was not found.
     * @return Reformatted resource name.
     */
    private static String getResourceName(final Class<?> clazz) {
        final String[] splits = StringUtils.splitByCharacterTypeCamelCase(clazz.getSimpleName());
        return StringUtils.capitalize(StringUtils.lowerCase(StringUtils.join(splits, " ")));
    }

}
