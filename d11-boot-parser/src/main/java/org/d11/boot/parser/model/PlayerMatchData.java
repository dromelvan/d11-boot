package org.d11.boot.parser.model;

import lombok.Data;

/**
 * Data for a player in a parsed match page.
 */
@Data
public class PlayerMatchData extends ParserModel {

    /**
     * WhoScored id of the player.
     */
    private Long playerWhoscoredId;
    /**
     * Name of the player.
     */
    private String playerName;
    /**
     * WhoScored id of the team the player played for.
     */
    private Long teamWhoscoredId;
    /**
     * Name of the team the player played for.
     */
    private String teamName;
    /**
     * Lineup status of the player in the match.
     */
    private Lineup lineup;
    /**
     * The match minute the player was substituted on. 0 if the player wasn't substituted on.
     */
    private Integer substitutionOnTime;
    /**
     * The match minute the player was substituted off. 0 if the player wasn't substituted off.
     */
    private Integer substitutionOffTime;
    /**
     * The number of goals scored by the player.
     */
    private Integer goals;
    /**
     * The number of goal assists made by the player.
     */
    private Integer goalAssists;
    /**
     * The number of own goals scored by the player.
     */
    private Integer ownGoals;
    /**
     * The number of goals conceded by the player.
     */
    private Integer goalsConceded;
    /**
     * The match minute the player received a yellow card. 0 if the player didn't receive a yellow card.
     */
    private Integer yellowCardTime;
    /**
     * The match minute the player received a red card. 0 if the player didn't receive a red card.
     */
    private Integer redCardTime;
    /**
     * The player was/wasn't the man of the match.
     */
    private Boolean manOfTheMatch;
    /**
     * The player was/wasn't a shared man of the match.
     */
    private Boolean sharedManOfTheMatch;
    /**
     * The player rating.
     */
    private Integer rating;
    /**
     * The position played in the match.
     */
    private String playedPosition;

}