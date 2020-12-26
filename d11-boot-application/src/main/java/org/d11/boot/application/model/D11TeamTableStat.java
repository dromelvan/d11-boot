package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Table stats for one D11 team for one D11 match week.
 */
@Data
@Entity
@Table(name = "d11_team_table_stat")
public class D11TeamTableStat extends TableStat {

    /**
     * The D11 team this D11 team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The D11 league this D1 team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_league_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11League d11League;

    /**
     * The D11 match week this D11 team table stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11MatchWeek d11MatchWeek;

}
