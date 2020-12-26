package org.d11.boot.application.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Base class for entities that contain table stats.
 */
@Data
@MappedSuperclass
@SuppressWarnings("PMD.TooManyFields")
public class TableStat extends D11Entity {

    /**
     * Number of matches played.
     */
    @PositiveOrZero
    private int matchesPlayed;
    /**
     * Number of matches won.
     */
    @PositiveOrZero
    private int matchesWon;
    /**
     * Number of matches drawn.
     */
    @PositiveOrZero
    private int matchesDrawn;
    /**
     * Number of matches lost.
     */
    @PositiveOrZero
    private int matchesLost;
    /**
     * Number of goals scored.
     */
    @PositiveOrZero
    private int goalsFor;
    /**
     * Number of goals conceded.
     */
    @PositiveOrZero
    private int goalsAgainst;
    /**
     * Goal difference.
     */
    private int goalDifference;
    /**
     * Number of points won.
     */
    @PositiveOrZero
    private int points;
    /**
     * Number of points won the last five matches.
     */
    @PositiveOrZero
    private int formPoints;
    /**
     * Table ranking.
     */
    @Positive
    private int ranking;
    /**
     * Table ranking after the previous match. Can be used to indicate if the team has climbed or dropped in the
     * table in table views.
     */
    @Positive
    private int previousRanking;
    /**
     * Number of home matches played.
     */
    @PositiveOrZero
    private int homeMatchesPlayed;
    /**
     * Number of home matches won.
     */
    @PositiveOrZero
    private int homeMatchesWon;
    /**
     * Number of home matches drawn.
     */
    @PositiveOrZero
    private int homeMatchesDrawn;
    /**
     * Number of home matches lost.
     */
    @PositiveOrZero
    private int homeMatchesLost;
    /**
     * Number of home goals scored.
     */
    @PositiveOrZero
    private int homeGoalsFor;
    /**
     * Number of home goals conceded.
     */
    @PositiveOrZero
    private int homeGoalsAgainst;
    /**
     * Home goal difference.
     */
    private int homeGoalDifference;
    /**
     * Number of home points won.
     */
    @PositiveOrZero
    private int homePoints;
    /**
     * Table ranking for home matches.
     */
    @Positive
    private int homeRanking;
    /**
     * Number of away matches played.
     */
    @PositiveOrZero
    private int awayMatchesPlayed;
    /**
     * Number of away matches won.
     */
    @PositiveOrZero
    private int awayMatchesWon;
    /**
     * Number of away matches drawn.
     */
    @PositiveOrZero
    private int awayMatchesDrawn;
    /**
     * Number of away matches lost.
     */
    @PositiveOrZero
    private int awayMatchesLost;
    /**
     * Number of away goals scored.
     */
    @PositiveOrZero
    private int awayGoalsFor;
    /**
     * Number of away goals conceded.
     */
    @PositiveOrZero
    private int awayGoalsAgainst;
    /**
     * Away goal difference.
     */
    private int awayGoalDifference;
    /**
     * Number of away points won.
     */
    @PositiveOrZero
    private int awayPoints;
    /**
     * Table ranking for away matches.
     */
    @Positive
    private int awayRanking;

}
