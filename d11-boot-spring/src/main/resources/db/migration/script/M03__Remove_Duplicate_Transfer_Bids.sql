-- Manual script for removing duplicate transfer bids in production schema before unique constraint migration
DELETE FROM production.transfer_bid
WHERE id NOT IN (
    SELECT MIN(id)
    FROM production.transfer_bid
    GROUP BY transfer_day_id, player_id, d11_team_id
);
