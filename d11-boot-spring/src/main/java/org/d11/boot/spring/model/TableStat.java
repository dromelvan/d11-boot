package org.d11.boot.spring.model;

import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.d11.boot.spring.model.converter.FormMatchPointsConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for entities that contain table stats.
 */
@Data
@MappedSuperclass
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
    private List<Integer> formMatchPoints = new ArrayList<>();

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
    }

    /**
     * Adds the stats in the provided table stat to this table stat.
     *
     * @param previousTableStat The table stats that will be added to this table stat.
     */
    public void updateCumulativeStats(final TableStat previousTableStat) {
        if (previousTableStat != null) {
            this.matchesPlayed += previousTableStat.getMatchesPlayed();
            this.matchesWon += previousTableStat.getMatchesWon();
            this.matchesDrawn += previousTableStat.getMatchesDrawn();
            this.matchesLost += previousTableStat.getMatchesLost();
            this.goalsFor += previousTableStat.getGoalsFor();
            this.goalsAgainst += previousTableStat.getGoalsAgainst();
            this.goalDifference += previousTableStat.getGoalDifference();
            this.points += previousTableStat.getPoints();
            this.formPoints += previousTableStat.getFormPoints();
        }
    }

}
