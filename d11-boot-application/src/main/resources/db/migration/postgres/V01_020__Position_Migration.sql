-- We need the old data to be in a 'data' schema.
INSERT INTO "position"
SELECT * FROM data.positions;
SELECT setval('position_id_seq', (SELECT last_value FROM data.positions_id_seq));
