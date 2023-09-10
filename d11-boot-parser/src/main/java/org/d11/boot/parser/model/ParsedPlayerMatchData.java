package org.d11.boot.parser.model;

import lombok.Data;
import org.d11.boot.util.Lineup;

/**
 * Data for a player in a parsed match page.
 */
@Data
@SuppressWarnings("PMD.TooManyFields")
public class ParsedPlayerMatchData extends ParsedPlayerData {

    /**
     * Player position id.
     */
    private Long positionId;

    /**
     * D11 team id. This is not set by the parser, but it might be a useful property to set when handling parsed player
     * match data.
     */
    private Long teamId;

    /**
     * Team id on whatever site the player match data was parsed from.
     */
    private Long teamSiteId;

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
     * Man of the match status.
     */
    private Boolean manOfTheMatch;

    /**
     * Shared man of the match status.
     */
    private Boolean sharedManOfTheMatch;

    /**
     * Player rating.
     */
    private Integer rating;

    /**
     * The position played in the match.
     */
    private String playedPosition;

}
