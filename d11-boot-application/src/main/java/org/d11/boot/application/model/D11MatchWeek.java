package org.d11.boot.application.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A match week in a D11 league.
 */
@Data
@Entity
@Table(name = "d11_match_week")
public class D11MatchWeek extends D11Entity {

    /**
     * Max value for elapsed.
     */
    public static final int MAX_ELAPSED = 220;

    /**
     * Match week number for this match week.
     */
    @Min(1)
    @Max(MatchWeek.MAX_MATCH_WEEK_NUMBER)
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
     * The D11 league this D11 match week belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_league_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11League d11League;
    /**
     * The Premier League match week this D11 match week is played in.
     */
    @OneToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

    /**
     * List of D11 team table stats for this D11 match week, ordered by ranking.
     */
    @OneToMany(mappedBy = "d11MatchWeek", cascade = CascadeType.ALL)
    @OrderBy("ranking ASC")
    @EqualsAndHashCode.Exclude
    private List<D11TeamTableStat> d11TeamTableStats = new ArrayList<>();

    /**
     * List of D11 matches that are played in this D11 match week, ordered by date.
     */
    @OneToMany(mappedBy = "d11MatchWeek", cascade = CascadeType.ALL)
    @OrderBy("date ASC")
    @EqualsAndHashCode.Exclude
    private List<D11Match> d11Matches = new ArrayList<>();

    /**
     * The D11 team that was at the top of the D11 league table at the time of this D11match week.
     */
    @ManyToOne
    @JoinColumn(name = "league_leader_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team leagueLeader;

}
