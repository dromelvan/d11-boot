package org.d11.boot.spring.repository;

import lombok.extern.slf4j.Slf4j;

/**
 * Dummy stored procedures for H2.
 */
@Slf4j
public final class H2Procedure {

    /**
     * Does not create new H2 procedures.
     */
    private H2Procedure() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Mocks the update_stats_by_season_id stored procedure.
     *
     * @param seasonId The season id.
     */
    public static void updateStatsBySeasonId(final Long seasonId) {
        LOGGER.info("update_stats_by_season_id({})", seasonId);
    }

    /**
     * Mocks the update_previous_rankings_by_match_week_id stored procedure.
     *
     * @param matchWeekId The match week id.
     */
    public static void updatePreviousRankingsByMatchWeekId(final Long matchWeekId) {
        LOGGER.info("update_previous_rankings_by_match_week_id({})", matchWeekId);
    }

}

