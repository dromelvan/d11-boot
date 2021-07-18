package org.d11.boot.application.model.projection;

/**
 * Team projection containing id, name and short name.
 */
public interface TeamProjection {

    /**
     * Gets the id of the team.
     *
     * @return The id of the team.
     */
    Long getId();

    /**
     * Gets the name of the team.
     *
     * @return The name of the team.
     */
    String getName();

    /**
     * Gets the short name of the team.
     *
     * @return The short name of the team.
     */
    String getShortName();

}
