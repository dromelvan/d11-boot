package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A D11 match.
 */
@Data
@Entity
@Table(name = "d11_match")
public class D11Match extends D11Entity {

    /**
     * Max length for elapsed time string.
     */
    public static final int ELAPSED_TIME_MAX_LENGTH = 10;

    /**
     * D11 Match date This is the date the match finished.
     */
    @NotNull
    private LocalDate date;
    /**
     * Number of goals scored by the home team.
     */
    @PositiveOrZero
    private int homeTeamGoals;
    /**
     * Number of goals scored by the away team.
     */
    @PositiveOrZero
    private int awayTeamGoals;
    /**
     * Number of points scored by the home team.
     */
    private int homeTeamPoints;
    /**
     * Number of points scored by the away team.
     */
    private int awayTeamPoints;
    /**
     * Number of goals scored by the home team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    @PositiveOrZero
    private int previousHomeTeamGoals;
    /**
     * Number of goals scored by the away team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    @PositiveOrZero
    private int previousAwayTeamGoals;
    /**
     * Number of points scored by the home team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    private int previousHomeTeamPoints;
    /**
     * Number of points scored by the away team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    private int previousAwayTeamPoints;
    /**
     * Elapsed string. This is FT, N/A or a number of minutes played depending on how
     * many players in each D11 team have played their matches this match week.
     */
    @NotNull
    @Size(min = 1, max = ELAPSED_TIME_MAX_LENGTH)
    private String elapsed;
    /**
     * The status of the D11 match.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The home D11 team of the D11 match.
     */
    @ManyToOne
    @JoinColumn(name = "home_d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team homeD11Team;
    /**
     * The away D11 team of the D11 match.
     */
    @ManyToOne
    @JoinColumn(name = "away_d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team awayD11Team;
    /**
     * The D11 match week the D11 match is played in.
     */
    @ManyToOne
    @JoinColumn(name = "d11_match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11MatchWeek d11MatchWeek;

}
