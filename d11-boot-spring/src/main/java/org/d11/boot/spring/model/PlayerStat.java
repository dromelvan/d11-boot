package org.d11.boot.spring.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Comparator;

/**
 * Base class for classes that contain player stats.
 */
@Data
@MappedSuperclass
public class PlayerStat extends D11Entity implements Comparable<PlayerStat> {

    /**
     * Player stat comparator.
     */
    private static final Comparator<PlayerStat> COMPARATOR = Comparator
            .comparing(PlayerStat::getPoints)
            .thenComparing(PlayerStat::getRating);

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
     * Adds stats from the provided player stat.
     *
     * @param playerStat The player stats that will be added to this player stat.
     */
    public void addStats(final PlayerStat playerStat) {
        this.goals += playerStat.getGoals();
        this.goalAssists += playerStat.getGoalAssists();
        this.ownGoals += playerStat.getOwnGoals();
        this.goalsConceded += playerStat.getGoalsConceded();
        this.rating += playerStat.getRating();
        this.points += playerStat.getPoints();
    }

    @Override
    public int compareTo(final PlayerStat playerStat) {
        return COMPARATOR.compare(playerStat, this);
    }

}
