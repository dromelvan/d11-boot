package org.d11.boot.application.model;

import com.google.common.collect.ComparisonChain;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.PositiveOrZero;

/**
 * Base class for classes that contain player stats.
 */
@Data
@MappedSuperclass
public class PlayerStat extends D11Entity implements Comparable<PlayerStat> {

    /**
     * Number of goals scored.
     */
    @PositiveOrZero
    private int goals;
    /**
     * Number of goal assists made.
     */
    @PositiveOrZero
    private int goalAssists;
    /**
     * Number of own goals scored.
     */
    @PositiveOrZero
    private int ownGoals;
    /**
     * Number of goals conceded.
     */
    @PositiveOrZero
    private int goalsConceded;
    /**
     * Rating from ratings source.
     */
    @PositiveOrZero
    private int rating;
    /**
     * D11 points.
     */
    private int points;

    /**
     * Resets all stats.
     */
    public void reset() {
        this.goals = 0;
        this.goalAssists = 0;
        this.ownGoals = 0;
        this.goalsConceded = 0;
        this.rating = 0;
        this.points = 0;
    }

    /**
     * Initializes a player stat from another player stat.
     *
     * @param playerStat The player stat with values that will be copied to this one.
     */
    protected void init(final PlayerStat playerStat) {
        this.goals = playerStat.getGoals();
        this.goalAssists = playerStat.getGoalAssists();
        this.ownGoals = playerStat.getOwnGoals();
        this.goalsConceded = playerStat.getGoalsConceded();
        this.rating = playerStat.getRating();
        this.points = playerStat.getPoints();
    }

    /**
     * Sets stats from the provided player match stat.
     *
     * @param playerMatchStat The player match stats that will be set in this player stat.
     */
    public void updateStats(final PlayerMatchStat playerMatchStat) {
        this.goals += playerMatchStat.getGoals();
        this.goalAssists += playerMatchStat.getGoalAssists();
        this.ownGoals += playerMatchStat.getOwnGoals();
        this.goalsConceded += playerMatchStat.getGoalsConceded();
        this.rating += playerMatchStat.getRating();
        this.points += playerMatchStat.getPoints();
    }

    @Override
    public int compareTo(final PlayerStat playerStat) {
        return ComparisonChain.start()
                .compare(playerStat.getPoints(), getPoints())
                .compare(playerStat.getRating(), getRating())
                .result();
    }
}
