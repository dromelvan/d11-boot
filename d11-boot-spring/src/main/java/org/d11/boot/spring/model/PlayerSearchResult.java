package org.d11.boot.spring.model;

/**
 * Player search information projection for one player.
 */
public interface PlayerSearchResult {

    /**
     * Gets the player id.
     *
     * @return Player id.
     */
    Long getId();

    /**
     * Gets the player name.
     *
     * @return Player name.
     */
    String getName();

    /**
     * Gets the player team id.
     *
     * @return Player team id.
     */
    Long getTeamId();

    /**
     * Gets the player team name.
     *
     * @return Player team name.
     */
    String getTeamName();

}
