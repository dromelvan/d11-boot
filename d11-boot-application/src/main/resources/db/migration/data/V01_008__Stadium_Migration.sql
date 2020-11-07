-- We need the old data to be in a 'data' schema.
INSERT INTO stadium
SELECT id, name, city, capacity, opened, photo_file_name, created_at, updated_at FROM data.stadia;
SELECT setval('stadium_id_seq', (SELECT last_value FROM data.stadia_id_seq));

UPDATE stadium
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;
