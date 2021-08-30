package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.application.model.jpa.converter.StatusConverter;
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
