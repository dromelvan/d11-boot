package org.d11.boot.parser.model;

import lombok.Data;

/**
 * Data for a goal in a parsed match page.
 */
@Data
public class ParsedGoalData extends ParsedData {

    /**
     * D11 player id of the player who scored the goal. This is not set by the parser, but it might be a useful property
     * to set when handling parsed goal data.
     */
    private Long playerId;

    /**
     * Player id of the player who scored the goal on whatever site the goal data was parsed from.
     */
    private Long playerSiteId;

    /**
     * Name of the player that scored the goal.
     */
    private String playerName;

    /**
     * D11 team id of the team that scored the goal. This is not set by the parser, but it might be a useful property to
     * set when handling parsed goal match data.
     */
    private Long teamId;

    /**
     * Team id of the team who scored the goal on whatever site the goal data was parsed from.
     */
    private Long teamSiteId;

    /**
     * Name of the team that scored the goal.
     */
    private String teamName;

    /**
     * The match minute the goal was scored.
     */
    private Integer time;

    /**
     * Penalty status.
     */
    private boolean penalty;

    /**
     * Own goal status.
     */
    private boolean ownGoal;

}
