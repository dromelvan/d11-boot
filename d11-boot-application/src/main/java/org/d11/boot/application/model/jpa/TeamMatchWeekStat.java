package org.d11.boot.application.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Stats for one team for one match week.
 */
@Data
@Entity
public class TeamMatchWeekStat extends TableStat {

    /**
     * The team this team match week stat belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The match week this team match week stat belongs to.
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

        final Match match = this.matchWeek.getMatchForTeamId(this.team.getId());

        if(match.isStarted()) {
            setMatchesPlayed(1);
            setMatchesWon(match.isWinner(getTeam()) ? 1 : 0);
            setMatchesDrawn(match.isDraw() ? 1 : 0);
            setMatchesLost(match.isLoser(getTeam()) ? 1 : 0);
            setGoalsFor(match.getGoalsFor(getTeam()));
            setGoalsAgainst(match.getGoalsAgainst(getTeam()));
            setGoalDifference(getGoalsFor() - getGoalsAgainst());
            setPoints(match.getPoints(getTeam()));
            if(match.getHomeTeam().equals(getTeam())) {
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
