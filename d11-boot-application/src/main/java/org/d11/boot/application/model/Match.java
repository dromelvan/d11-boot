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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Premier League match.
 */
@Data
@Entity
public class Match extends D11Entity {

    /**
     * Max length for elapsed time string.
     */
    public static final int ELAPSED_TIME_MAX_LENGTH = 10;

    /**
     * Match id on whoscored.
     */
    @PositiveOrZero
    private int whoscoredId;
    /**
     * Match kickoff time.
     */
    @NotNull
    private LocalDateTime datetime;
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
     * Elapsed string. This can be either the number of minutes played or N/A, HT, FT or whatever
     * else WhoScored sees fit to put in their elapsed time data.
     */
    @NotNull
    @Size(min = 1, max = ELAPSED_TIME_MAX_LENGTH)
    private String elapsed;
    /**
     * The status of the match.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The home team of the match.
     */
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team homeTeam;
    /**
     * The away team of the match.
     */
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team awayTeam;
    /**
     * The match week the match is played in.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;
    /**
     * The stadium the match is played in.
     */
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Stadium stadium;

    /**
     * List of goals that were scored in this match.
     */
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time, addedTime ASC")
    @EqualsAndHashCode.Exclude
    private List<Goal> goals = new ArrayList<>();

    /**
     * List of cards that were shown in this match.
     */
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time, addedTime ASC")
    @EqualsAndHashCode.Exclude
    private List<Card> cards = new ArrayList<>();

}
