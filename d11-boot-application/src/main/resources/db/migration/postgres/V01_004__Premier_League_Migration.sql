-- We need the old data to be in a 'data' schema.
INSERT INTO premier_league
SELECT * FROM data.premier_leagues;
SELECT setval('premier_league_id_seq', (SELECT last_value FROM data.premier_leagues_id_seq));
