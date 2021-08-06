package org.d11.boot.application.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.PositiveOrZero;

/**
 * Base class for classes that provide summaries for player stats.
 */
@Data
@MappedSuperclass
public class PlayerStatSummary extends PlayerStat {

    /**
     * Player ranking.
     */
    @PositiveOrZero
    private int ranking;
    /**
     * Average points per appearance (game started or substituted on).
     */
    private int pointsPerAppearance;
    /**
     * Total number of clean sheets.
     */
    @PositiveOrZero
    private int cleanSheets;
    /**
     * Total number of yellow cards.
     */
    @PositiveOrZero
    private int yellowCards;
    /**
     * Total number red cards.
     */
    @PositiveOrZero
    private int redCards;
    /**
     * Total number of times the player was substituted on.
     */
    @PositiveOrZero
    private int substitutionsOn;
    /**
     * Total number of times the player was substituted off.
     */
    @PositiveOrZero
    private int substitutionsOff;
    /**
     * Total number of times the player was the man of the match.
     */
    @PositiveOrZero
    private int manOfTheMatch;
    /**
     * Total number of times the player was a shared man of the match.
     */
    @PositiveOrZero
    private int sharedManOfTheMatch;
    /**
     * Total number of games started.
     */
    @PositiveOrZero
    private int gamesStarted;
    /**
     * Total number of games started on the bench.
     */
    @PositiveOrZero
    private int gamesSubstitute;
    /**
     * Total number of games the player could have participated in but didn't.
     */
    @PositiveOrZero
    private int gamesDidNotParticipate;
    /**
     * Total number of minutes played.
     */
    @PositiveOrZero
    private int minutesPlayed;

}
