-- We need the old data to be in a 'data' schema.
INSERT INTO d11_league
SELECT * FROM data.d11_leagues;
SELECT setval('d11_league_id_seq', (SELECT last_value FROM data.d11_leagues_id_seq));
