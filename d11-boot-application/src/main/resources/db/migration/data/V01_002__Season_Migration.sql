-- We need the old data to be in a 'data' schema.
INSERT INTO season
SELECT * FROM data.seasons;
SELECT setval('season_id_seq', (SELECT last_value FROM data.seasons_id_seq));
