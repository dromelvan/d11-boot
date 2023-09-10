package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

}
