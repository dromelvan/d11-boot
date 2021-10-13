package org.d11.boot.jms.model;

import lombok.Data;

/**
 * Data for a goal in a parsed match page.
 */
@Data
public class GoalData extends JmsModel {

    /**
     * Whoscored id of the player that scored the goal.
     */
    private Integer playerWhoscoredId;
    /**
     * Name of the player that scored the goal.
     */
    private String playerName;
    /**
     * Whoscored id of the team that scored the goal.
     */
    private Integer teamWhoscoredId;
    /**
     * Name of the team that scored the goal.
     */
    private String teamName;
    /**
     * The match minute the goal was scored.
     */
    private Integer time;
    /**
     * Penalty or no penalty goal.
     */
    private boolean penalty;
    /**
     * Own goal or no own goal.
     */
    private boolean ownGoal;

}
