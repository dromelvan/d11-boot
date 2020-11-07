-- We need the old data to be in a 'data' schema.
INSERT INTO team
SELECT id, stadium_id, whoscored_id, name, short_name, code, nickname, established, motto, colour, dummy, club_crest_file_name, created_at, updated_at FROM data.teams;
SELECT setval('team_id_seq', (SELECT last_value FROM data.teams_id_seq));

UPDATE team
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;
