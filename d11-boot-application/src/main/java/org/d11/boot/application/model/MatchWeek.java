package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * A match week in the premier league.
 */
@Data
@Entity
public class MatchWeek extends D11Entity {

    /**
     * Max match week number.
     */
    public static final int MAX_MATCH_WEEK_NUMBER = 38;

    /**
     * Match week number for this match week.
     */
    @Min(1)
    @Max(MAX_MATCH_WEEK_NUMBER)
    private int matchWeekNumber;
    /**
     * Start date for the match week.
     */
    @NotNull
    private LocalDate date;
    /**
     * Match week status.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The Premier League this match week belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "premier_league_id")
    @NotNull
    private PremierLeague premierLeague;

    /**
     * The D11 match week this match week is played in..
     */
    @OneToOne(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11MatchWeek d11MatchWeek;

}
