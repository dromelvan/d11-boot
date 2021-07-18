package org.d11.boot.application.model.projection;

/**
 * Transfer listing projection containing player, team , D11 team and position.
 */
public interface TransferListingProjection {

    /**
     * Gets the player of the transfer listing.
     *
     * @return The player of the transfer listing.
     */
    PlayerProjection getPlayer();

    /**
     * Gets the team of the transfer listing.
     *
     * @return The team of the transfer listing.
     */
    TeamProjection getTeam();

    /**
     * Gets the D11 team of the transfer listing.
     *
     * @return The D11 team of the transfer listing.
     */
    EntityId getD11Team();

    /**
     * Gets the position of the transfer listing.
     *
     * @return The position of the transfer listing.
     */
    PositionProjection getPosition();

}
