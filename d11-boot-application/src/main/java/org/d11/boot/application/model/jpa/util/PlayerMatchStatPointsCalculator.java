package org.d11.boot.application.model.jpa.util;

import org.d11.boot.application.model.jpa.PlayerMatchStat;

/**
 * Interface for calculating player match stats points.
 */
public interface PlayerMatchStatPointsCalculator {

    /**
     * Calculates points for a player match stats.
     *
     * @param playerMatchStat The player match stats points will be calculated for.
     * @return The number of points the player has amassed for the match.
     */
    int calculatePoints(PlayerMatchStat playerMatchStat);

}
