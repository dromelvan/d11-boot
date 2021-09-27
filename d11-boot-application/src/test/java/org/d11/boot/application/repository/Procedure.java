package org.d11.boot.application.repository;

import lombok.extern.slf4j.Slf4j;

/**
 * Dummy stored procedures for H2.
 */
@Slf4j
@SuppressWarnings({ "PMD.UseUtilityClass", "PMD.ClassNamingConventions" })
public class Procedure {

    /**
     * Mocks the update_stats_by_season_id stored procedure.
     *
     * @param seasonId Id of the season that stats should be updated for.
     */
    public static void updateStatsBySeasonId(final Long seasonId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_previous_rankings_by_match_week_id stored procedure.
     *
     * @param matchWeekId Id of the match week that previous rankings should be updated for.
     */
    public static void updatePreviousRankingsByMatchWeekId(final Long matchWeekId) {
        // Just a dummy method to make tests pass.
    }

}

