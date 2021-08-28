package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * Represents data for a player. Example:
 * {
 *      "playerId":23089,
 *      "shirtNo":11,
 *      "name":"Rui Patrício",
 *      "position":"GK",
 *      "height":190,
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
    private int playerId;
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
     * Player height.
     */
    private int height;
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

    public Long getPositionId() {
        switch (this.position.toUpperCase()) {
            case "GK":
                return 1L;
            case "DC":
            case "DL":
            case "DR":
                return 3L;
            case "MC":
            case "ML":
            case "MR":
            case "DMC":
            case "DML":
            case "DMR":
            case "AMC":
            case "AML":
            case "AMR":
                return 4L;
            case "FW":
            case "FWL":
            case "FWR":
                // Subs become forwards so new unverified players don't get bonuses/minuses until they're fixed manually.
            case "SUB":
                return 5L;
            default:
                return 6L;
        }
    }

}
