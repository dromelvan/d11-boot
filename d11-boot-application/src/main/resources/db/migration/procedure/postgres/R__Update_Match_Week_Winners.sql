-- Update match week premier league leader
CREATE OR REPLACE PROCEDURE update_match_week_premier_league_leader(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.match_week
    SET premier_league_leader_id = (
        SELECT team_match_week_stat_id FROM (
            SELECT ${flyway:defaultSchema}.match_week.id AS match_week_id, ${flyway:defaultSchema}.team_match_week_stat.id AS team_match_week_stat_id
            FROM ${flyway:defaultSchema}.match_week
            JOIN ${flyway:defaultSchema}.team_match_week_stat ON ${flyway:defaultSchema}.match_week.id = ${flyway:defaultSchema}.team_match_week_stat.match_week_id
            WHERE ${flyway:defaultSchema}.team_match_week_stat.ranking = 1
            ) premier_league_leader_query
        WHERE premier_league_leader_query.match_week_id = ${flyway:defaultSchema}.match_week.id
    ) WHERE ${flyway:defaultSchema}.match_week.id = update_match_week_id;
END;$$;

-- Update match week D11 league leader
CREATE OR REPLACE PROCEDURE update_match_week_d11_league_leader(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.match_week
    SET d11_league_leader_id = (
        SELECT d11_team_match_week_stat_id FROM (
            SELECT ${flyway:defaultSchema}.match_week.id AS match_week_id, ${flyway:defaultSchema}.d11_team_match_week_stat.id AS d11_team_match_week_stat_id
            FROM ${flyway:defaultSchema}.match_week
            JOIN ${flyway:defaultSchema}.d11_team_match_week_stat ON ${flyway:defaultSchema}.match_week.id = ${flyway:defaultSchema}.d11_team_match_week_stat.match_week_id
            WHERE ${flyway:defaultSchema}.d11_team_match_week_stat.ranking = 1
        ) d11_league_leader_query
        WHERE d11_league_leader_query.match_week_id = ${flyway:defaultSchema}.match_week.id
    ) WHERE ${flyway:defaultSchema}.match_week.id = update_match_week_id;
END;$$;

-- Update match week most valuable player
CREATE OR REPLACE PROCEDURE update_match_week_most_valuable_player(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.match_week
    SET most_valuable_player_id = (
        SELECT ${flyway:defaultSchema}.player_match_stat.id
        FROM ${flyway:defaultSchema}.player_match_stat
        JOIN ${flyway:defaultSchema}.match ON ${flyway:defaultSchema}.player_match_stat.match_id = ${flyway:defaultSchema}.match.id
        WHERE ${flyway:defaultSchema}.match.match_week_id = ${flyway:defaultSchema}.match_week.id
        ORDER BY points DESC, rating DESC
        LIMIT 1
    ) WHERE ${flyway:defaultSchema}.match_week.id = update_match_week_id;
END;$$;
