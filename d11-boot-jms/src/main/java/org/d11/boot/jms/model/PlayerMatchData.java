package org.d11.boot.jms.model;

import lombok.Data;

import java.beans.Transient;

/**
 * Data for a player in a parsed match page.
 */
@Data
public class PlayerMatchData extends JmsModel {

    /**
     * Name divider.
     */
    public static final String NAME_DIVIDER = " ";

    /**
     * Id of the player.
     */
    private Long playerId;
    /**
     * WhoScored id of the player.
     */
    private Integer playerWhoscoredId;
    /**
     * Name of the player.
     */
    private String playerName;
    /**
     * Player country id.
     */
    private Long countryId;
    /**
     * Player position id.
     */
    private Long positionId;
    /**
     * WhoScored id of the team the player played for.
     */
    private Integer teamWhoscoredId;
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
    /**
     * Player height.
     */
    private int height;

    /**
     * Gets the first name of the player.
     *
     * @return The first name of the player.
     */
    @Transient
    public String getFirstName() {
        final int index = this.playerName.indexOf(NAME_DIVIDER);
        if(index > 0) {
            return this.playerName.substring(0, index).trim();
        }
        return "";
    }

    /**
     * Gets the last name of the player.
     *
     * @return The last name of the player.
     */
    @Transient
    public String getLastName() {
        final int index = this.playerName.indexOf(NAME_DIVIDER);
        if(index > 0) {
            return this.playerName.substring(index).trim();
        }
        return this.playerName;
    }

    /**
     * Player was/was not in the starting lineup.
     *
     * @return True if the player started the match, false otherwise.
     */
    @Transient
    public boolean isStartingLineup() {
        return this.lineup == Lineup.STARTING_LINEUP;
    }

}