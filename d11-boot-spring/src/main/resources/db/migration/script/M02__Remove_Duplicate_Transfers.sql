-- Manual script for removing duplicate transfers in production schema before unique constraint migration
DELETE FROM production.transfer
WHERE id NOT IN (
    SELECT MIN(id)
    FROM production.transfer
    GROUP BY transfer_day_id, player_id
);
