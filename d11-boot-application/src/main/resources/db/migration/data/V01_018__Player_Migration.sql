-- We need the old data to be in a 'data' schema.
INSERT INTO player
SELECT id, country_id, (CASE WHEN whoscored_id IS NULL THEN 0 ELSE whoscored_id END), first_name, last_name, full_name, parameterized_name, date_of_birth, height, player_photo_file_name, created_at, updated_at FROM data.players;
SELECT setval('player_id_seq', (SELECT last_value FROM data.players_id_seq));

UPDATE player
SET whoscored_id = 0 WHERE whoscored_id = 1 and id != 142;
