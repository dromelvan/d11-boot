package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * Represents data for a player. Example:
 * {
 *      "playerId":23089,
 *      "shirtNo":11,
 *      "name":"Rui Patrício",
 *      "position":"GK",
 *      ...
 *      "isFirstEleven":true,
 *      ...
 *      "stats":{ .. }
 * }
 */
@Data
public class Player {

    /**
     * The multiplier used to get an integer rating from a double rating. Example: 6.78 -> 678.
     */
    private static final int RATING_MULTIPLIER = 100;

    /**
     * Player id.
     */
    private long playerId;
    /**
     * Player shirt number.
     */
    private int shirtNo;
    /**
     * Player name.
     */
    private String name;
    /**
     * Position played by the player this match.
     */
    private String position;
    /**
     * True if the player started, false if the player was on the bench.
     */
    private boolean isFirstEleven;
    /**
     * Player stats for the match.
     */
    private Stats stats;

    /**
     * Gets the rating for the player this match.
     *
     * @return Player rating.
     */
    public int getRating() {
        final Ratings ratings = this.stats.getRatings();
        double lastRating = 0.0;
        if(ratings != null && !ratings.isEmpty()) {
            for(final Double rating : ratings.values()) {
                lastRating = rating;
            }
        }
        return (int) (lastRating * RATING_MULTIPLIER);
    }

}
