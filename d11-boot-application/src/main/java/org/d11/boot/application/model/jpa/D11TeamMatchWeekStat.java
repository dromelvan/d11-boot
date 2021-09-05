package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Stats for one D11 team for one match week.
 */
@Data
@Entity
@Table(name = "d11_team_match_week_stat")
public class D11TeamMatchWeekStat extends TableStat {

    /**
     * The D11 team this D11 team match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The match week this D11 team match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "match_week_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MatchWeek matchWeek;

    /**
     * Resets and updates the stats for this match week only. The stats are taken from the match the team played.
     */
    public void updateStats() {
        reset();

        final D11Match match = this.matchWeek.getD11MatchForTeamId(getD11Team().getId());

        if(match.isStarted()) {
            setMatchesPlayed(1);
            setMatchesWon(match.isWinner(getD11Team()) ? 1 : 0);
            setMatchesDrawn(match.isDraw() ? 1 : 0);
            setMatchesLost(match.isLoser(getD11Team()) ? 1 : 0);
            setGoalsFor(match.getGoalsFor(getD11Team()));
            setGoalsAgainst(match.getGoalsAgainst(getD11Team()));
            setGoalDifference(getGoalsFor() - getGoalsAgainst());
            setPoints(match.getPoints(getD11Team()));
            if(match.getHomeD11Team().equals(getD11Team())) {
                setHomeMatchesPlayed(getMatchesPlayed());
                setHomeMatchesWon(getMatchesWon());
                setHomeMatchesDrawn(getMatchesDrawn());
                setHomeMatchesLost(getMatchesLost());
                setHomeGoalsFor(getGoalsFor());
                setHomeGoalsAgainst(getGoalsAgainst());
                setHomeGoalDifference(getGoalDifference());
                setHomePoints(getPoints());
            } else {
                setAwayMatchesPlayed(getMatchesPlayed());
                setAwayMatchesWon(getMatchesWon());
                setAwayMatchesDrawn(getMatchesDrawn());
                setAwayMatchesLost(getMatchesLost());
                setAwayGoalsFor(getGoalsFor());
                setAwayGoalsAgainst(getGoalsAgainst());
                setAwayGoalDifference(getGoalDifference());
                setAwayPoints(getPoints());
            }
        }
    }

}
