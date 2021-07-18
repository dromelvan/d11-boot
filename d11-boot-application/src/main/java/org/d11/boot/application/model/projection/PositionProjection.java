package org.d11.boot.application.model.projection;

/**
 * Position projection containing name, code and sort order.
 */
public interface PositionProjection {

    /**
     * Gets the name of the position.
     *
     * @return The name of the position.
     */
    String getName();

    /**
     * Gets the code of the position.
     *
     * @return The code of the position.
     */
    String getCode();

    /**
     * Gets the sort order of the position.
     *
     * @return The sort order of the position.
     */
    int getSortOrder();

}
