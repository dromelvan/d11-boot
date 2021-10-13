package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.converter.StatusConverter;
import org.d11.boot.application.util.NotFoundException;

import javax.annotation.Nonnull;
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
import java.util.Optional;

/**
 * A Premier League match.
 */
@Data
@Entity
public class Match extends D11Entity implements Comparable<Match> {

    /**
     * Max length for elapsed time string.
     */
    public static final int ELAPSED_TIME_MAX_LENGTH = 10;
    /**
     * Points gained for a win.
     */
    public static final int WIN_POINTS = 3;
    /**
     * Points gained for a drawn.
     */
    public static final int DRAW_POINTS = 1;
    /**
     * Points gained for a loss.
     */
    public static final int LOSS_POINTS = 0;

    /**
     * PMD doesn't like having "match" this many times in one class.
     */
    private static final String MAPPED_BY = "match";

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
    @OneToMany(mappedBy = MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy("time, addedTime ASC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Goal> goals = new ArrayList<>();

    /**
     * List of cards that were shown in this match.
     */
    @OneToMany(mappedBy = MAPPED_BY, cascade = CascadeType.ALL)
    @OrderBy("time, addedTime ASC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Card> cards = new ArrayList<>();

    /**
     * List of player match stats for this match.
     */
    @OneToMany(mappedBy = MAPPED_BY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PlayerMatchStat> playerMatchStats = new ArrayList<>();

    /**
     * List of match log messages from when the match was updated.
     */
    @OneToMany(mappedBy = MAPPED_BY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MatchLogMessage> matchLogMessages = new ArrayList<>();

    /**
     * Get if the match is started or not.
     *
     * @return True if the match is started.
     */
    public boolean isStarted() {
        return !this.status.equals(Status.PENDING) && !this.status.equals(Status.POSTPONED);
    }

    /**
     * Gets if a specific team is the winner (or leader) of the match.
     *
     * @param team The team that winner status will be checked for.
     * @return True if the team is the match winner (or leader).
     */
    public boolean isWinner(final Team team) {
        return team.equals(this.homeTeam) && this.homeTeamGoals > this.awayTeamGoals
                || team.equals(this.awayTeam) && this.awayTeamGoals > this.homeTeamGoals;
    }

    /**
     * Gets if a specific team is the loser of the match (or is losing).
     *
     * @param team The team that loser status will be checked for.
     * @return True if the team is the match loser (or is losing).
     */
    public boolean isLoser(final Team team) {
        return team.equals(this.homeTeam) && this.homeTeamGoals < this.awayTeamGoals
                || team.equals(this.awayTeam) && this.awayTeamGoals < this.homeTeamGoals;
    }

    /**
     * Get if the match is a draw.
     *
     * @return True if the match is a draw, false is not.
     */
    public boolean isDraw() {
        return this.homeTeamGoals == this.awayTeamGoals;
    }

    /**
     * Gets the number of goals a team has scored in the match.
     *
     * @param team The team that goal count will be checked for.
     * @return The number of goals scored by the team.
     */
    public int getGoalsFor(final Team team) {
        return team.equals(this.homeTeam) ? this.homeTeamGoals : this.awayTeamGoals;
    }

    /**
     * Gets the number of goals a team has conceded in the match.
     *
     * @param team The team that goals conceded count will be checked for.
     * @return The number of goals conceded by the team.
     */
    public int getGoalsAgainst(final Team team) {
        return team.equals(this.homeTeam) ? this.awayTeamGoals : this.homeTeamGoals;
    }

    /**
     * Gets the number of points gained by a team in the match.
     *
     * @param team The team that points will be checked for.
     * @return The number of points gained by the team.
     */
    public int getPoints(final Team team) {
        if(isWinner(team)) {
            return WIN_POINTS;
        } else if(isDraw()) {
            return DRAW_POINTS;
        }
        return LOSS_POINTS;
    }

    /**
     * Gets a player match stat for a specific player from the match.
     *
     * @param playerId Id of the player whose match stats will be looked up.
     * @return Player match stat for the player.
     */
    public Optional<PlayerMatchStat> getPlayerMatchStatByPlayerId(final long playerId) {
        return getPlayerMatchStats().stream()
                .filter(playerMatchStat -> playerMatchStat.getPlayer().getId().equals(playerId))
                .findFirst();
    }

    /**
     * Gets a team with a specific WhoScored id from the match.
     *
     * @param teamWhoscoredId WhoScored id for the team that will be looked up.
     * @return The home team or the away team depending on which teams WhoScored id is provided.
     */
    public Team getTeamByWhoscoredId(final int teamWhoscoredId) {
        if(getHomeTeam().getWhoscoredId() == teamWhoscoredId) {
            return getHomeTeam();
        } else if(getAwayTeam().getWhoscoredId() == teamWhoscoredId) {
            return getAwayTeam();
        }
        throw new NotFoundException("Team with WhoScored id " + teamWhoscoredId + " not found in match " + getId() + ".");
    }

    /**
     * Sets previous home team and away team goals to current home team and away team goals and then sets current home
     * team and away team goals to 0.
     */
    public void reset() {
        this.goals.clear();
        this.previousHomeTeamGoals = this.homeTeamGoals;
        this.previousAwayTeamGoals = this.awayTeamGoals;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    @Override
    public int compareTo(@Nonnull final Match match) {
        if(getStatus() == Status.POSTPONED && match.getStatus() != Status.POSTPONED) {
            return 1;
        } else if(getStatus() != Status.POSTPONED && match.getStatus() == Status.POSTPONED) {
            return -1;
        }
        return getDatetime().compareTo(match.getDatetime());
    }
}
