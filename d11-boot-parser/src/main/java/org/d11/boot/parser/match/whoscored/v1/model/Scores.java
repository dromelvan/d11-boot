package org.d11.boot.parser.match.whoscored.v1.model;

import lombok.Data;

/**
 * Represents an object containing half-time, full time and running scores for a team. Example:
 * "scores":{
 *      "halftime":1,
 *      "fulltime":1,
 *      "running":1
 * }
 */
@Data
public class Scores {

    /**
     * The half-time score for a team.
     */
    private Integer halftime;

    /**
     * The full time score for a team.
     */
    private Integer fulltime;

    /**
     * The running score for a team.
     */
    private Integer running;

}
