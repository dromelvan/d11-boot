package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Stats for one D11 team for one season.
 */
@Data
@Entity
@Table(name = "d11_team_season_stat")
public class D11TeamSeasonStat extends TableStat {

    /**
     * The nth league win for this D11 team, if it won this season.
     */
    @Positive
    private Integer winCount;

    /**
     * The D11 team this D11 team season stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The season this D11 team season stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

}
