CREATE OR REPLACE FUNCTION get_top_player_improvements_per_season_with_value(top_n INT)
RETURNS TABLE (
    player_id INT,
    season_id INT,
    from_team INT,
    to_team INT,
    from_points INT,
    to_points INT,
    from_player_value INT,
    to_player_value INT,
    from_value_for_money NUMERIC,
    to_value_for_money NUMERIC,
    points_delta INT,
    from_start_mw INT,
    from_end_mw INT,
    to_start_mw INT,
    to_end_mw INT
)
LANGUAGE sql
AS $$
WITH ranked_segments AS (
    SELECT
        *,
        LEAD(total_points) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS next_points,
        LEAD(d11_team_id) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS next_team,
        LEAD(start_match_week_id) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS next_start_mw,
        LEAD(end_match_week_id) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS next_end_mw,
        LEAD(player_value) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS to_player_value,
        LEAD(value_for_money) OVER (
            PARTITION BY player_id, season_id
            ORDER BY start_match_week_id
        ) AS to_value_for_money
    FROM player_value_segments
),
improvements AS (
    SELECT
        player_id,
        season_id,
        d11_team_id AS from_team,
        next_team AS to_team,
        total_points AS from_points,
        next_points AS to_points,
        player_value AS from_player_value,
        to_player_value,
        value_for_money AS from_value_for_money,
        to_value_for_money,
        (next_points - total_points) AS points_delta,
        start_match_week_id AS from_start_mw,
        end_match_week_id AS from_end_mw,
        next_start_mw AS to_start_mw,
        next_end_mw AS to_end_mw
    FROM ranked_segments
    WHERE next_points IS NOT NULL
      AND next_points - total_points > 0
),
ranked_improvements AS (
    SELECT
        *,
        ROW_NUMBER() OVER (
            PARTITION BY season_id
            ORDER BY points_delta DESC
        ) AS rn
    FROM improvements
)
SELECT *
FROM ranked_improvements
WHERE rn <= top_n
ORDER BY season_id, points_delta DESC;
$$;
