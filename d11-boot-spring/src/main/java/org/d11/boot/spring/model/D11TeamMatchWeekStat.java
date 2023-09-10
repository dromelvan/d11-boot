package org.d11.boot.spring.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

/**
 * Stats for one D22 team for one match week.
 */
@Data
@Entity
@Table(name = "d11_team_match_week_stat")
public class D11TeamMatchWeekStat extends TableStat {

    /**
     * The D11 team this D11 team match week stat belongs to.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "d11_team_id")
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private D11Team d11Team;

    /**
     * The match week this D11 team match week stat belongs to.
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

        final Optional<D11Match> optional = this.matchWeek.getD11MatchByD11Team(getD11Team());

        optional.ifPresent(d11Match -> {
            if (d11Match.isStarted()) {
                setMatchesPlayed(1);
                setMatchesWon(d11Match.isWinner(getD11Team()) ? 1 : 0);
                setMatchesDrawn(d11Match.isDraw() ? 1 : 0);
                setMatchesLost(d11Match.isLoser(getD11Team()) ? 1 : 0);
                setGoalsFor(d11Match.getGoalsFor(getD11Team()));
                setGoalsAgainst(d11Match.getGoalsAgainst(getD11Team()));
                setGoalDifference(getGoalsFor() - getGoalsAgainst());
                setPoints(d11Match.getPoints(getD11Team()));
            }
        });
    }

}
