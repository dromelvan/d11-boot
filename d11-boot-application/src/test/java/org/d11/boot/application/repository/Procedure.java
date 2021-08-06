package org.d11.boot.application.repository;

import lombok.extern.slf4j.Slf4j;

/**
 * Dummy stored procedures for H2.
 */
@Slf4j
@SuppressWarnings({ "PMD.UseUtilityClass", "PMD.ClassNamingConventions" })
public class Procedure {

    /**
     * Mocks the update_player_season_stat_rankings stored procedure.
     *
     * @param seasonId Id of the season that player season stat rankings should be updated for.
     */
    public static void updatePlayerSeasonStatRanking(final Long seasonId) {
        // Just a dummy method to make tests pass.
    }

}
