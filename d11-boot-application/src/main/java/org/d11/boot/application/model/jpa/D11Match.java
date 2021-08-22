package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.jpa.converter.StatusConverter;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A D11 match.
 */
@Data
@Entity
@Table(name = "d11_match")
public class D11Match extends D11Entity implements Comparable<D11Match> {

    /**
     * Max length for elapsed time string.
     */
    public static final int ELAPSED_TIME_MAX_LENGTH = 10;

    /**
     * D11 match kickoff time.
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
     * The match week the D11 match is played in.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;
    /**
     * List of player match stats for this D11 match.
     */
    @OneToMany(mappedBy = "d11Match", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

    @Override
    public int compareTo(@Nonnull final D11Match d11Match) {
        return getDatetime().compareTo(d11Match.getDatetime());
    }

}
