package org.d11.boot.parser.match.whoscored.v1.model;

import java.io.Serial;
import java.util.LinkedHashMap;

/**
 * Mapping of time to rating for a player for a match. The last rating is the actual rating for the player. Example:
 * "ratings":{
 * "0":6.0,
 * "5":6.01,
 * "7":6.01,
 * "12":5.74,
 * "17":5.74,
 * "33":5.75,
 * "36":5.75,
 * "38":5.8,
 * "42":6.0,
 * "43":5.99,
 * "48":5.72,
 * "52":5.73,
 * "55":5.73,
 * "63":5.74,
 * "72":5.74,
 * "86":6.02,
 * "91":6.02
 * }
 */
@SuppressWarnings("checkstyle:IllegalType")
public class Ratings extends LinkedHashMap<String, Double> {

    @Serial
    private static final long serialVersionUID = 7528153412744577990L;

}
