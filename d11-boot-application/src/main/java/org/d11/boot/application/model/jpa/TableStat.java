package org.d11.boot.application.model.jpa;

import lombok.Data;
import org.d11.boot.application.model.jpa.converter.FormMatchPointsConverter;

import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

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
     * List of points won the last five individual matches.
     */
    @NotNull
    @Convert(converter = FormMatchPointsConverter.class)
    private List<Integer> formMatchPoints;
    /**
     * Table ranking.
     */
    @PositiveOrZero
    private int ranking;
    /**
     * Table ranking after the previous match. Can be used to indicate if the team has climbed or dropped in the
     * table in table views.
     */
    @PositiveOrZero
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
    @PositiveOrZero
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
    @PositiveOrZero
    private int awayRanking;

    /**
     * Resets all stats to 0.
     */
    public void reset() {
        this.matchesPlayed = 0;
        this.matchesWon = 0;
        this.matchesDrawn = 0;
        this.matchesLost = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
        this.points = 0;
        this.formPoints = 0;
        this.formMatchPoints.clear();
        this.ranking = 0;
        this.previousRanking = 0;
        this.homeMatchesPlayed = 0;
        this.homeMatchesWon = 0;
        this.homeMatchesDrawn = 0;
        this.homeMatchesLost = 0;
        this.homeGoalsFor = 0;
        this.homeGoalsAgainst = 0;
        this.homeGoalDifference = 0;
        this.homePoints = 0;
        this.homeRanking = 0;
        this.awayMatchesPlayed = 0;
        this.awayMatchesWon = 0;
        this.awayMatchesDrawn = 0;
        this.awayMatchesLost = 0;
        this.awayGoalsFor = 0;
        this.awayGoalsAgainst = 0;
        this.awayGoalDifference = 0;
        this.awayPoints = 0;
        this.awayRanking = 0;
    }

    /**
     * Adds the stats in the provided table stat to this table stat.
     *
     * @param previousTableStat The table stats that will be added to this table stat.
     */
    public void updateCumulativeStats(final TableStat previousTableStat) {
        if(previousTableStat != null) {
            setMatchesPlayed(previousTableStat.getMatchesPlayed() + getMatchesPlayed());
            setMatchesWon(previousTableStat.getMatchesWon() + getMatchesWon());
            setMatchesDrawn(previousTableStat.getMatchesDrawn() + getMatchesDrawn());
            setMatchesLost(previousTableStat.getMatchesLost() + getMatchesLost());
            setGoalsFor(previousTableStat.getGoalsFor() + getGoalsFor());
            setGoalsAgainst(previousTableStat.getGoalsAgainst() + getGoalsAgainst());
            setGoalDifference(previousTableStat.getGoalDifference() + getGoalDifference());
            setPoints(previousTableStat.getPoints() + getPoints());
            setFormPoints(previousTableStat.getFormPoints() + getFormPoints());
            setHomeMatchesPlayed(previousTableStat.getHomeMatchesPlayed() + getHomeMatchesPlayed());
            setHomeMatchesWon(previousTableStat.getHomeMatchesWon() + getHomeMatchesWon());
            setHomeMatchesDrawn(previousTableStat.getHomeMatchesDrawn() + getHomeMatchesDrawn());
            setHomeMatchesLost(previousTableStat.getHomeMatchesLost() + getHomeMatchesLost());
            setHomeGoalsFor(previousTableStat.getHomeGoalsFor() + getHomeGoalsFor());
            setHomeGoalsAgainst(previousTableStat.getHomeGoalsAgainst() + getHomeGoalsAgainst());
            setHomeGoalDifference(previousTableStat.getHomeGoalDifference() + getHomeGoalDifference());
            setHomePoints(previousTableStat.getHomePoints() + getHomePoints());
            setAwayMatchesPlayed(previousTableStat.getAwayMatchesPlayed() + getAwayMatchesPlayed());
            setAwayMatchesWon(previousTableStat.getAwayMatchesWon() + getAwayMatchesWon());
            setAwayMatchesDrawn(previousTableStat.getAwayMatchesDrawn() + getAwayMatchesDrawn());
            setAwayMatchesLost(previousTableStat.getAwayMatchesLost() + getAwayMatchesLost());
            setAwayGoalsFor(previousTableStat.getAwayGoalsFor() + getAwayGoalsFor());
            setAwayGoalsAgainst(previousTableStat.getAwayGoalsAgainst() + getAwayGoalsAgainst());
            setAwayGoalDifference(previousTableStat.getAwayGoalDifference() + getAwayGoalDifference());
            setAwayPoints(previousTableStat.getAwayPoints() + getAwayPoints());
        }
    }

}
