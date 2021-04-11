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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PremierLeague premierLeague;

    /**
     * The D11 match week this match week is played in.
     */
    @OneToOne(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11MatchWeek d11MatchWeek;

    /**
     * List of team table stats for this match week, ordered by ranking.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @OrderBy("ranking ASC")
    @EqualsAndHashCode.Exclude
    private List<TeamTableStat> teamTableStats = new ArrayList<>();

    /**
     * List of matches that are played in this match week, ordered by datetime.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @OrderBy("datetime ASC")
    @EqualsAndHashCode.Exclude
    private List<Match> matches = new ArrayList<>();

    /**
     * The team that was at the top of the league table at the time of this match week.
     *
     * @return The leading team at the time of this match week or null if no leader could be found.
     */
    public Team getLeader() {
        if(this.teamTableStats != null && !this.teamTableStats.isEmpty()) {
            return this.teamTableStats.get(0).getTeam();
        }
        return null;
    }

    /**
     * The number of finished matches in this match week, representing the elapsed time of the match week.
     *
     * @return The number, between 0 and 10, of finished matches this match week, representing the elapsed time.
     */
    public int getElapsed() {
        int elapsed = 0;
        for(final Match match : this.matches) {
            if(match.getStatus() == Status.FINISHED) {
                ++elapsed;
            }
        }
        return elapsed;
    }

}
