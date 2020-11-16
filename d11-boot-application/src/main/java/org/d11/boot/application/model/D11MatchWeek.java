package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * A match week in a D11 league.
 */
@Data
@Entity
@Table(name = "d11_match_week")
public class D11MatchWeek extends D11Entity {

    /**
     * Match week number for this match week.
     */
    @Min(1)
    @Max(MatchWeek.MAX_MATCH_WEEK_NUMBER)
    private int matchWeekNumber;
    /**
     * Start date for the match week.
     */
    @NotNull
    private LocalDate date;

    /**
     * The D11 league this D11 match week belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_league_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11League d11League;
    /**
     * The Premier League match week this D11 match week is played in.
     */
    @OneToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

}
