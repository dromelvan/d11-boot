package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.d11.boot.spring.model.converter.StatusConverter;
import org.d11.boot.util.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A match week in the premier league.
 */
@Data
@Entity
public class MatchWeek extends D11Entity implements Comparable<MatchWeek> {

    /**
     * Max match week number.
     */
    public static final int MAX_MATCH_WEEK_NUMBER = 38;

    /**
     * Max value for elapsed.
     */
    public static final int MAX_ELAPSED = 10;

    /**
     * Match week comparator.
     */
    private static final Comparator<MatchWeek> COMPARATOR = Comparator.comparingInt(MatchWeek::getMatchWeekNumber);

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
     * The number of finished matches in this match week, representing the elapsed time of the match week.
     */
    @Min(0)
    @Max(MAX_ELAPSED)
    private int elapsed;

    /**
     * Match week status.
     */
    @NotNull
    @Convert(converter = StatusConverter.class)
    private Status status = Status.PENDING;

    /**
     * The season this match week belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

    /**
     * List of matches that are played in this match week.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Match> matches = new ArrayList<>();

    /**
     * List of D11 matches that are played in this match week.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<D11Match> d11Matches = new ArrayList<>();

    /**
     * Get started status.
     *
     * @return True if the match week is started, false if not.
     */
    public boolean isStarted() {
        return !this.status.equals(Status.PENDING) && !this.status.equals(Status.POSTPONED);
    }

    /**
     * Gets a match in this match week by team.
     *
     * @param team The team.
     * @return Optional with the match the team participates in this match week or empty optional if no such match
     *         exists.
     */
    public Optional<Match> getMatchByTeam(final Team team) {
        return this.matches.stream()
                .filter(match -> match.isParticipant(team))
                .findAny();
    }

    /**
     * Gets a D11 match in this match week by team.
     *
     * @param d11Team The D11 team.
     * @return Optional with the D11 match the D11 team participates in this match week or empty optional if no such
     *         D11 match exists.
     */
    public Optional<D11Match> getD11MatchByD11Team(final D11Team d11Team) {
        return this.d11Matches.stream()
                .filter(d11Match -> d11Match.isParticipant(d11Team))
                .findAny();
    }

    @Override
    public int compareTo(final MatchWeek matchWeek) {
        return COMPARATOR.compare(this, matchWeek);
    }

}
