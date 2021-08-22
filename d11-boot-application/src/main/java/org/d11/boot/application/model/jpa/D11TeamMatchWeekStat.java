package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.jpa.D11Team;
import org.d11.boot.application.model.jpa.MatchWeek;
import org.d11.boot.application.model.jpa.TableStat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Stats for one D11 team for one match week.
 */
@Data
@Entity
@Table(name = "d11_team_match_week_stat")
public class D11TeamMatchWeekStat extends TableStat {

    /**
     * The D11 team this D11 team match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The match week this D11 team match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

}
