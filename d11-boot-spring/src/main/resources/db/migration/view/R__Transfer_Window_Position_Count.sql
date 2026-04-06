DROP VIEW IF EXISTS transfer_window_position_count;

CREATE OR REPLACE VIEW transfer_window_position_count AS
SELECT
    transfer_listing_sub_query.transfer_window_id,
    transfer_listing_sub_query.position_id,
    transfer_listing_sub_query.transfer_listing_count,
    COALESCE(transfer_sub_query.transfer_count, 0) AS transfer_count
FROM (
    SELECT
        tw.id AS transfer_window_id,
        tl.position_id,
        COUNT(*) AS transfer_listing_count
    FROM transfer_window tw
        JOIN transfer_day td
            ON td.transfer_window_id = tw.id
        JOIN transfer_listing tl
            ON tl.transfer_day_id = td.id
        JOIN d11_team d11t
            ON tl.d11_team_id = d11t.id
    WHERE td.transfer_day_number = 1
        AND d11t.dummy = false
        AND tw.status > 0
    GROUP BY tw.id, tl.position_id
) transfer_listing_sub_query
LEFT JOIN (
    SELECT
        tw.id AS transfer_window_id,
        tl.position_id,
        COUNT(*) AS transfer_count
    FROM transfer_window tw
        JOIN transfer_day td
            ON td.transfer_window_id = tw.id
        JOIN transfer tr
            ON tr.transfer_day_id = td.id
        JOIN transfer_listing tl
            ON tl.transfer_day_id = td.id
            AND tl.player_id = tr.player_id
    GROUP BY tw.id, tl.position_id
) transfer_sub_query
ON transfer_listing_sub_query.transfer_window_id = transfer_sub_query.transfer_window_id
AND transfer_listing_sub_query.position_id = transfer_sub_query.position_id;
