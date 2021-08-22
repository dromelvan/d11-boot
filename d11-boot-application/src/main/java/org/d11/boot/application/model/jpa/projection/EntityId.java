package org.d11.boot.application.model.jpa.projection;

/**
 * Projection containing only the id of an entity.
 */
public interface EntityId {

    /**
     * Gets the id of the entity.
     *
     * @return The id of the entity.
     */
    Long getId();

}
