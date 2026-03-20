-- player_match_week_fee -----------------------------------------------------------------

CREATE OR REPLACE VIEW player_match_week_fee AS
WITH player_match_week AS (
    SELECT
        pms.player_id,
        mw.id                                                   AS match_week_id,
        mw.season_id,
        pms.d11_team_id,
        MAX(CASE WHEN dt.dummy THEN 1 ELSE 0 END)               AS is_dummy
    FROM player_match_stat pms
    JOIN match      m  ON m.id  = pms.match_id
    JOIN match_week mw ON mw.id = m.match_week_id
    JOIN d11_team   dt ON dt.id = pms.d11_team_id
    GROUP BY pms.player_id, mw.id, mw.season_id, pms.d11_team_id
),
transfer_at_match_week AS (
    SELECT
        t.player_id,
        mw.season_id,
        mw.id AS match_week_id,
        t.fee
    FROM transfer        t
    JOIN transfer_day    td ON td.id = t.transfer_day_id
    JOIN transfer_window tw ON tw.id = td.transfer_window_id
    JOIN match_week      mw ON mw.id = tw.match_week_id
),
joined AS (
    SELECT
        pmw.*,
        tamw.fee AS transfer_fee
    FROM player_match_week pmw
    LEFT JOIN transfer_at_match_week tamw
        ON  tamw.player_id     = pmw.player_id
        AND tamw.season_id     = pmw.season_id
        AND tamw.match_week_id = pmw.match_week_id
),
with_fee_group AS (
    SELECT
        *,
        COUNT(transfer_fee) OVER (
            PARTITION BY player_id, season_id
            ORDER BY match_week_id
            ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
        ) AS fee_group
    FROM joined
)
SELECT
    player_id,
    match_week_id,
    season_id,
    d11_team_id,
    is_dummy,
    fee_group,
    CASE
        WHEN is_dummy = 1 THEN 0
        ELSE COALESCE(
            MAX(transfer_fee) OVER (PARTITION BY player_id, season_id, fee_group),
            0
        )
    END AS fee
FROM with_fee_group;

-- player_match_week_points --------------------------------------------------------------

CREATE OR REPLACE VIEW player_match_week_points AS
SELECT
    pms.player_id,
    mw.id           AS match_week_id,
    mw.season_id,
    SUM(pms.points) AS points
FROM player_match_stat pms
JOIN match      m  ON m.id  = pms.match_id
JOIN match_week mw ON mw.id = m.match_week_id
GROUP BY pms.player_id, mw.id, mw.season_id;

-- player_transfer_segment ---------------------------------------------------------------

CREATE OR REPLACE VIEW player_transfer_segment AS
WITH segment_match_weeks AS (
    SELECT
        pmwf.player_id,
        pmwf.season_id,
        pmwf.d11_team_id,
        pmwf.fee_group,
        pmwf.fee,
        pmwf.match_week_id,
        COALESCE(pmwp.points, 0) AS points
    FROM player_match_week_fee pmwf
    LEFT JOIN player_match_week_points pmwp
        ON  pmwp.player_id     = pmwf.player_id
        AND pmwp.match_week_id = pmwf.match_week_id
    WHERE pmwf.fee > 0
),
segments AS (
    SELECT
        player_id,
        season_id,
        d11_team_id,
        fee,
        MIN(match_week_id) AS start_match_week_id,
        MAX(match_week_id) AS end_match_week_id,
        SUM(points)        AS points
    FROM segment_match_weeks
    GROUP BY player_id, season_id, d11_team_id, fee, fee_group
),
season_cost AS (
    SELECT
        season_id,
        CAST(SUM(fee) AS NUMERIC) / NULLIF(SUM(points), 0) AS cost_per_point
    FROM segments
    GROUP BY season_id
)
SELECT
    s.player_id,
    s.season_id,
    s.d11_team_id,
    s.start_match_week_id,
    s.end_match_week_id,
    s.fee,
    s.points,
    sc.cost_per_point                                                                       AS point_cost,
    CAST(ROUND(s.points * sc.cost_per_point) AS INTEGER)                                    AS "value",
    ROUND(
        (s.points * sc.cost_per_point) / NULLIF(s.fee, 0),
        2
    )                                                                                       AS return_ratio
FROM segments s
JOIN season_cost sc ON sc.season_id = s.season_id
ORDER BY s.player_id, s.season_id, s.start_match_week_id;
