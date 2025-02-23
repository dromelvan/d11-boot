DROP VIEW IF EXISTS transfer_context;

CREATE OR REPLACE VIEW transfer_context
AS
WITH current_season AS (
    SELECT id
    FROM season
    ORDER BY date DESC
    LIMIT 1
), current_transfer_day AS (
    SELECT id AS transfer_day_id
    FROM transfer_day
    ORDER BY datetime DESC
    LIMIT 1
)
SELECT
    pss.player_id,
    p.id AS position_id,
    pss.d11_team_id AS player_d11_team_id,
    cs.id as season_id,
    ctd.transfer_day_id,
    tl.id AS transfer_listing_id,
    tb.id AS transfer_bid_id,
    dts.d11_team_id,
    dt.owner_id,
    dt.co_owner_id,
    dts.ranking,
    COALESCE(pss_count.player_count, 0) AS player_count,
    COALESCE(pss_fee_sum.fee_sum, 0) AS fee_sum,
    COALESCE(t.transfer_count, 0) AS transfer_count,
    COALESCE(pss_position_count.position_count, 0) AS position_count
FROM d11_team_season_stat dts
    JOIN d11_team dt ON dts.d11_team_id = dt.id
    JOIN current_season cs on true
    JOIN position p ON true
    LEFT JOIN (
                SELECT plcpss.d11_team_id,
                       count(*) AS player_count
                FROM player_season_stat plcpss
                WHERE plcpss.season_id = ( SELECT current_season.id FROM current_season )
                GROUP BY plcpss.d11_team_id
            ) pss_count
        ON dts.d11_team_id = pss_count.d11_team_id
    LEFT JOIN (
                SELECT fspss.d11_team_id,
                       sum(fspss.fee) AS fee_sum
                FROM player_season_stat fspss
                WHERE fspss.season_id = ( SELECT current_season.id FROM current_season )
                GROUP BY fspss.d11_team_id
            ) pss_fee_sum
        ON dts.d11_team_id = pss_fee_sum.d11_team_id
    LEFT JOIN (
                SELECT tct.d11_team_id,
                       count(*) AS transfer_count
                FROM transfer tct
                    JOIN transfer_day td ON tct.transfer_day_id = td.id
                    JOIN transfer_window tw ON td.transfer_window_id = tw.id
                    JOIN match_week mw ON tw.match_week_id = mw.id
                WHERE mw.season_id = ( SELECT current_season.id FROM current_season) AND tw.draft = false
                GROUP BY tct.d11_team_id
            ) t
        ON dts.d11_team_id = t.d11_team_id
    LEFT JOIN (
                SELECT pocpss.d11_team_id,
                       pocpss.position_id,
                       count(*) AS position_count
                FROM player_season_stat pocpss
                WHERE pocpss.season_id = ( SELECT current_season.id FROM current_season)
                GROUP BY pocpss.d11_team_id, pocpss.position_id
            ) pss_position_count
        ON dts.d11_team_id = pss_position_count.d11_team_id AND p.id = pss_position_count.position_id
    LEFT JOIN player_season_stat pss ON pss.position_id = p.id AND pss.season_id = ( SELECT current_season.id FROM current_season )
    LEFT JOIN current_transfer_day ctd ON true
    LEFT JOIN transfer_listing tl ON tl.player_id = pss.player_id AND tl.transfer_day_id = ctd.transfer_day_id
    LEFT JOIN transfer_bid tb ON tb.player_id = pss.player_id AND tb.transfer_day_id = ctd.transfer_day_id
WHERE dts.season_id = ( SELECT current_season.id FROM current_season);
