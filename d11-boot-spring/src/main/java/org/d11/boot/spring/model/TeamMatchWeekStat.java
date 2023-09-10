package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

/**
 * Stats for one team for one match week.
 */
@Data
@Entity
public class TeamMatchWeekStat extends TableStat {

    /**
     * The team this team match week stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    /**
     * The match week this team match week stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
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

        final Optional<Match> optional = this.matchWeek.getMatchByTeam(this.team);

        optional.ifPresent(match -> {
            if (match.isStarted()) {
                setMatchesPlayed(1);
                setMatchesWon(match.isWinner(getTeam()) ? 1 : 0);
                setMatchesDrawn(match.isDraw() ? 1 : 0);
                setMatchesLost(match.isLoser(getTeam()) ? 1 : 0);
                setGoalsFor(match.getGoalsFor(getTeam()));
                setGoalsAgainst(match.getGoalsAgainst(getTeam()));
                setGoalDifference(getGoalsFor() - getGoalsAgainst());
                setPoints(match.getPoints(getTeam()));
            }
        });
    }

}
