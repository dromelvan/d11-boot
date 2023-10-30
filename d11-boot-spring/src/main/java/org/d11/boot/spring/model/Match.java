package org.d11.boot.spring.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.converter.StatusConverter;
import org.d11.boot.util.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A Premier League match.
 */
@Data
@Entity
@NamedEntityGraph(name = Match.MATCH_ASSOCIATIONS,
                  attributeNodes = {
                          @NamedAttributeNode("homeTeam"),
                          @NamedAttributeNode("awayTeam"),
                          @NamedAttributeNode("matchWeek"),
                          @NamedAttributeNode("stadium"),
                          @NamedAttributeNode(value = "goals", subgraph = "goals-subgraph")
                  },
                  subgraphs = {
                          @NamedSubgraph(
                                  name = "goals-subgraph",
                                  attributeNodes = {
                                          @NamedAttributeNode("player"),
                                          @NamedAttributeNode("team")
                                  }
                          )
                  }
)
@SuppressWarnings({ "checkstyle:ClassFanOutComplexity", "checkstyle:Indentation" })
public class Match extends D11Entity implements Comparable<Match> {

    /**
     * Name of the entity graph that includes goals.
     */
    public static final String MATCH_ASSOCIATIONS = "Match.associations";

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
     * Match id on WhoScored.
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
    private int homeTeamGoalsScored;

    /**
     * Number of goals scored by the away team.
     */
    @PositiveOrZero
    private int awayTeamGoalsScored;

    /**
     * Number of goals scored by the home team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    @PositiveOrZero
    private int previousHomeTeamGoalsScored;

    /**
     * Number of goals scored by the away team the previous time the match was updated.
     * This number can be used to keep track of scoring changes since the last update.
     */
    @PositiveOrZero
    private int previousAwayTeamGoalsScored;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "home_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team homeTeam;

    /**
     * The away team of the match.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "away_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team awayTeam;

    /**
     * List of goals that were scored in this match.
     */
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("time, addedTime ASC")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Goal> goals = new ArrayList<>();

    /**
     * The match week the match is played in.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

    /**
     * The stadium the match is played in.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stadium_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Stadium stadium;

    /**
     * Gets team participant status.
     *
     * @param team The team.
     * @return True if the team is the home team or the away team in the match, false otherwise.
     */
    public boolean isParticipant(final Team team) {
        return Objects.equals(this.homeTeam, team)
               || Objects.equals(this.awayTeam, team);
    }

    /**
     * Get started status.
     *
     * @return True if the match is started, false if not.
     */
    public boolean isStarted() {
        return !this.status.equals(Status.PENDING) && !this.status.equals(Status.POSTPONED);
    }

    /**
     * Gets team match winner (or winning) status.
     *
     * @param team The team.
     * @return True if the team is the match winner (or leader), false if not.
     */
    public boolean isWinner(final Team team) {
        return team.equals(this.homeTeam) && this.homeTeamGoalsScored > this.awayTeamGoalsScored
               || team.equals(this.awayTeam) && this.awayTeamGoalsScored > this.homeTeamGoalsScored;
    }

    /**
     * Gets team match loser (or losing) status.
     *
     * @param team The team.
     * @return True if the team is the match loser (or is losing), false if not.
     */
    public boolean isLoser(final Team team) {
        return team.equals(this.homeTeam) && this.homeTeamGoalsScored < this.awayTeamGoalsScored
               || team.equals(this.awayTeam) && this.awayTeamGoalsScored < this.homeTeamGoalsScored;
    }

    /**
     * Gets draw status.
     *
     * @return True if the match is a draw, false is not.
     */
    public boolean isDraw() {
        return this.homeTeamGoalsScored == this.awayTeamGoalsScored;
    }

    /**
     * Gets a list of goals scored by the home team.
     *
     * @return List of goals scored by the home team.
     */
    public List<Goal> getHomeTeamGoals() {
        return this.goals.stream()
                .filter(goal -> goal.getTeam().equals(this.homeTeam))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of goals scored by the away team.
     *
     * @return List of goals scored by the away team.
     */
    public List<Goal> getAwayTeamGoals() {
        return this.goals.stream()
                .filter(goal -> goal.getTeam().equals(this.awayTeam))
                .collect(Collectors.toList());
    }

    /**
     * Gets the number of goals a team has scored in the match.
     *
     * @param team The team.
     * @return The number of goals scored by the team.
     */
    public int getGoalsFor(final Team team) {
        return team.equals(this.homeTeam) ? this.homeTeamGoalsScored : this.awayTeamGoalsScored;
    }

    /**
     * Gets the number of goals a team has conceded in the match.
     *
     * @param team The team.
     * @return The number of goals conceded by the team.
     */
    public int getGoalsAgainst(final Team team) {
        return team.equals(this.homeTeam) ? this.awayTeamGoalsScored : this.homeTeamGoalsScored;
    }

    /**
     * Gets the number of points gained by a team in the match.
     *
     * @param team The team.
     * @return The number of points gained by the team.
     */
    public int getPoints(final Team team) {
        if (isWinner(team)) {
            return WIN_POINTS;
        } else if (isDraw()) {
            return DRAW_POINTS;
        }
        return LOSS_POINTS;
    }

    /**
     * Gets a team by WhoScored id from the match.
     *
     * @param teamWhoscoredId The WhoScored id.
     * @return The home team or the away team depending on which teams WhoScored id is provided.
     */
    public Optional<Team> getTeamByWhoscoredId(final int teamWhoscoredId) {
        if (getHomeTeam().getWhoscoredId() == teamWhoscoredId) {
            return Optional.of(getHomeTeam());
        } else if (getAwayTeam().getWhoscoredId() == teamWhoscoredId) {
            return Optional.of(getAwayTeam());
        }
        return Optional.empty();
    }

    /**
     * Sets previous home team and away team goals to current home team and away team goals and then sets current home
     * team and away team goals to 0.
     */
    public void reset() {
        this.previousHomeTeamGoalsScored = this.homeTeamGoalsScored;
        this.previousAwayTeamGoalsScored = this.awayTeamGoalsScored;
        this.homeTeamGoalsScored = 0;
        this.awayTeamGoalsScored = 0;
    }

    @Override
    public int compareTo(@Nonnull final Match match) {
        if (getStatus() == Status.POSTPONED && match.getStatus() != Status.POSTPONED) {
            return 1;
        } else if (getStatus() != Status.POSTPONED && match.getStatus() == Status.POSTPONED) {
            return -1;
        }
        return getDatetime().compareTo(match.getDatetime());
    }

}
