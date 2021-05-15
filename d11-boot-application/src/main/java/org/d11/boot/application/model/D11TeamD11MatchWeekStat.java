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
 * Stats for one D11 team for one D11 match week.
 */
@Data
@Entity
@Table(name = "d11_team_d11_match_week_stat")
public class D11TeamD11MatchWeekStat extends TableStat {

    /**
     * The D11 team this D11 team D11 match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The D11 match week this D11 team D11 match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11MatchWeek d11MatchWeek;

}
