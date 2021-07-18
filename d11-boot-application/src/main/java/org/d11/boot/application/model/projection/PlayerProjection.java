package org.d11.boot.application.model.projection;

/**
 * Player projection containing only id, name and short name.
 */
public interface PlayerProjection {

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    Long getId();

    /**
     * Gets the name of the name.
     *
     * @return The name of the name.
     */
    String getName();

    /**
     * Gets the short name of the player.
     *
     * @return The short name of the player.
     */
    String getShortName();

}
