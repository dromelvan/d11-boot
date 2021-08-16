package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * Represents player stats for a match. Example:
 * "stats":{
 *     ...
 *     "ratings":{ .. },
 *     ...
 * }
 */
@Data
public class Stats {

    /**
     * List of time <-> rating pairs for a player for a match.
     */
    private Ratings ratings;

}

