package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Table stats for one team for one match week.
 */
@Data
@Entity
public class TeamTableStat extends TableStat {

    /**
     * The team this team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The Premier League this team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "premier_league_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PremierLeague premierLeague;

    /**
     * The match week this team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

}
