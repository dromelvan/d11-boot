-- We need the old data to be in a 'data' schema.
INSERT INTO d11_team
SELECT id, owner_id, co_owner_id, name, short_name, code, dummy, club_crest_file_name, created_at, updated_at FROM data.d11_teams;
SELECT setval('d11_team_id_seq', (SELECT last_value FROM data.d11_teams_id_seq));

UPDATE d11_team
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;
