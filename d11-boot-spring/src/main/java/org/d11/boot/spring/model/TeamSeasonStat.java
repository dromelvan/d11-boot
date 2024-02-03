package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Stats for one team for one season.
 */
@Data
@Entity
public class TeamSeasonStat extends TableStat {

    /**
     * The nth league win for this team, if it won this season.
     */
    @Positive
    private Integer winCount;

    /**
     * Number of points the team has been penalized.
     */
    @PositiveOrZero
    private int pointsPenalty;

    /**
     * The team this team season stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The season this team season stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

    /**
     * Resets all stats to 0.
     */
    public void reset() {
        super.reset();
        this.winCount = 0;
        this.pointsPenalty = 0;
    }

    /**
     * Adds the stats in the provided table stat to this table stat.
     *
     * @param previousTableStat The table stats that will be added to this table stat.
     */
    public void updateCumulativeStats(final TableStat previousTableStat) {
        super.updateCumulativeStats(previousTableStat);
        if (previousTableStat != null) {
            final TeamSeasonStat previousTeamSeasonStat = (TeamSeasonStat)previousTableStat;
            this.winCount += previousTeamSeasonStat.getWinCount();
            this.pointsPenalty += previousTeamSeasonStat.getPointsPenalty();
        }
    }

}
