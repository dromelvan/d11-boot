package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The season this D11 team season stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

}
