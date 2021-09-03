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

    /**
     * Mocks the update_team_match_week_stat_rankings stored procedure.
     *
     * @param matchWeekId Id of the match week that team match week stat rankings should be updated for.
     */
    public static void updateTeamMatchWeekStatRanking(final Long matchWeekId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_team_match_week_stat_previous_rankings stored procedure.
     *
     * @param seasonId Id of the season that team match week stat rankings should be updated for.
     */
    public static void updateTeamMatchWeekStatPreviousRanking(final Long seasonId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_team_season_stat_ranking stored procedure.
     *
     * @param seasonId Id of the season that team season stat rankings should be updated for.
     */
    public static void updateTeamSeasonStatRanking(final Long seasonId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_match_week_premier_league_leader stored procedure.
     *
     * @param matchWeekId Id of the match week that Premier League leader should be updated for.
     */
    public static void updateMatchWeekPremierLeagueLeader(final Long matchWeekId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_match_week_d11_league_leader stored procedure.
     *
     * @param matchWeekId Id of the match week that D11 league leader should be updated for.
     */
    public static void updateMatchWeekD11LeagueLeader(final Long matchWeekId) {
        // Just a dummy method to make tests pass.
    }

    /**
     * Mocks the update_match_week_most_valuable_player stored procedure.
     *
     * @param matchWeekId Id of the match week that most valuable player should be updated for.
     */
    public static void updateMatchWeekMostValuablePlayer(final Long matchWeekId) {
        // Just a dummy method to make tests pass.
    }

}
