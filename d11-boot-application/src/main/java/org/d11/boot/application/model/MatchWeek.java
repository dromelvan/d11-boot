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
     * Max value for elapsed.
     */
    public static final int MAX_ELAPSED = 10;

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
    @ManyToOne
    @JoinColumn(name = "season_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Season season;

    /**
     * The team that was at the top of the league table at the time of this match week.
     */
    @ManyToOne
    @JoinColumn(name = "premier_league_leader_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private TeamMatchWeekStat premierLeagueLeader;

    /**
     * The D11 team that was at the top of the D11 league table at the time of this match week.
     */
    @ManyToOne
    @JoinColumn(name = "d11_league_leader_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11TeamMatchWeekStat d11LeagueLeader;

    /**
     * The player that scored the most points for this match week.
     */
    @OneToOne
    @JoinColumn(name = "most_valuable_player_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private PlayerMatchStat mostValuablePlayer;

    /**
     * List of matches that are played in this match week, ordered by datetime.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Match> matches = new ArrayList<>();

    /**
     * List of D11 matches that are played in this match week, ordered by date.
     */
    @OneToMany(mappedBy = "matchWeek", cascade = CascadeType.ALL)
    @OrderBy("datetime ASC")
    @EqualsAndHashCode.Exclude
    private List<D11Match> d11Matches = new ArrayList<>();

}
