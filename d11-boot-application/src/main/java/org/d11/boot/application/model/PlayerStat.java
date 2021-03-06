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

    @Override
    public int compareTo(final PlayerStat playerStat) {
        return ComparisonChain.start()
                .compare(playerStat.getPoints(), getPoints())
                .compare(playerStat.getRating(), getRating())
                .result();
    }
}
