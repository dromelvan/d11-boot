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
     * Elapsed time divider.
     */
    public static final int ELAPSED_TIME_DIVIDER = 4;
    /**
     * Points goal divider.
     */
    public static final int POINTS_GOAL_DIVIDER = 5;

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

    /**
     * Get started status of the D11 match.
     *
     * @return True if status is not pending or postponed, false if it is.
     */
    public boolean isStarted() {
        return !this.status.equals(Status.PENDING) && !this.status.equals(Status.POSTPONED);
    }

    /**
     * Gets if a specific D11 team is the winner (or leader) of the D11 match.
     *
     * @param d11Team The D11 team that winner status will be checked for.
     * @return True if the D11 team is the D11 match winner (or leader).
     */
    public boolean isWinner(final D11Team d11Team) {
        return d11Team.equals(this.homeD11Team) && this.homeTeamGoals > this.awayTeamGoals
                || d11Team.equals(this.awayD11Team) && this.awayTeamGoals > this.homeTeamGoals;
    }

    /**
     * Gets if a specific D11 team is the loser of the D11 match (or is losing).
     *
     * @param d11Team The D11 team that loser status will be checked for.
     * @return True if the D11 team is the D11 match loser (or is losing).
     */
    public boolean isLoser(final D11Team d11Team) {
        return d11Team.equals(this.homeD11Team) && this.homeTeamGoals < this.awayTeamGoals
                || d11Team.equals(this.awayD11Team) && this.awayTeamGoals < this.homeTeamGoals;
    }

    /**
     * Get if the D11 match is a draw.
     *
     * @return True if the D11 match is a draw, false is not.
     */
    public boolean isDraw() {
        return this.homeTeamGoals == this.awayTeamGoals;
    }

    /**
     * Gets the number of goals a D11 team has scored in the match.
     *
     * @param d11Team The D11 team that goal count will be checked for.
     * @return The number of goals scored by the D11 team.
     */
    public int getGoalsFor(final D11Team d11Team) {
        return d11Team.equals(this.homeD11Team) ? this.homeTeamGoals : this.awayTeamGoals;
    }

    /**
     * Gets the number of goals a D11 team has conceded in the match.
     *
     * @param d11Team The D11 team that goals conceded count will be checked for.
     * @return The number of goals conceded by the D11 team.
     */
    public int getGoalsAgainst(final D11Team d11Team) {
        return d11Team.equals(this.homeD11Team) ? this.awayTeamGoals : this.homeTeamGoals;
    }

    /**
     * Gets the number of points gained by a D11 team in the D11 match.
     *
     * @param d11Team The D11 team that points will be checked for.
     * @return The number of points gained by the D11 team.
     */
    public int getPoints(final D11Team d11Team) {
        if(isWinner(d11Team)) {
            return Match.WIN_POINTS;
        } else if(isDraw()) {
            return Match.DRAW_POINTS;
        }
        return Match.LOSS_POINTS;
    }

    /**
     * Updates points, goals, datetime, elapsed time and status.
     */
    @SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.NPathComplexity",
                        "checkstyle:CyclomaticComplexity", "checkstyle:NPathComplexity" })
    public void update() {
        reset();

        int activeCount = 0;
        int fullTimeCount = 0;
        int finishedCount = 0;
        LocalDateTime localDateTime = null;

        for(final PlayerMatchStat playerMatchStat : this.playerMatchStats) {
            if(playerMatchStat.getMatch().isStarted()) {
                if(playerMatchStat.getD11Team().equals(this.homeD11Team)) {
                    this.homeTeamPoints += playerMatchStat.getPoints();
                } else {
                    this.awayTeamPoints += playerMatchStat.getPoints();
                }
            }
            final Status matchStatus = playerMatchStat.getMatch().getStatus();
            if(matchStatus.equals(Status.ACTIVE)) {
                ++activeCount;
            } else if(matchStatus.equals(Status.FULL_TIME)) {
                ++fullTimeCount;
            } else if(matchStatus.equals(Status.FINISHED)) {
                ++finishedCount;
            }
            if(localDateTime == null || playerMatchStat.getMatch().getDatetime().isBefore(localDateTime)) {
                localDateTime = playerMatchStat.getMatch().getDatetime();
            }
        }
        this.datetime = localDateTime;

        final String fullTimeElapsed = "FT";
        final int playerCount = this.playerMatchStats.size();
        if(finishedCount == playerCount) {
            this.status = Status.FINISHED;
            this.elapsed = fullTimeElapsed;
        } else if(fullTimeCount + finishedCount == playerCount) {
            this.status = Status.FULL_TIME;
            this.elapsed = fullTimeElapsed;
        } else if(activeCount + fullTimeCount + finishedCount > 0) {
            this.status = Status.ACTIVE;
            this.elapsed = String.valueOf((activeCount + fullTimeCount + finishedCount) * ELAPSED_TIME_DIVIDER);
        }

        this.homeTeamGoals = this.homeTeamPoints > 0 ? this.homeTeamPoints / POINTS_GOAL_DIVIDER + 1 : 0;
        this.awayTeamGoals = this.awayTeamPoints > 0 ? this.awayTeamPoints / POINTS_GOAL_DIVIDER + 1 : 0;
    }

    /**
     * Sets previous home D11 team and away D11 team goals and points to current home D11 team and away D11 team goals
     * and points and then sets current home D11 team and away D11 team goals and points to 0.
     */
    public void reset() {
        this.status = Status.PENDING;
        this.elapsed = "N/A";
        this.previousHomeTeamGoals = this.homeTeamGoals;
        this.previousHomeTeamPoints = this.homeTeamPoints;
        this.previousAwayTeamGoals = this.awayTeamGoals;
        this.previousAwayTeamPoints = this.awayTeamPoints;
        this.homeTeamGoals = 0;
        this.homeTeamPoints = 0;
        this.awayTeamGoals = 0;
        this.awayTeamPoints = 0;
    }

    @Override
    public int compareTo(@Nonnull final D11Match d11Match) {
        return getDatetime().compareTo(d11Match.getDatetime());
    }

}
