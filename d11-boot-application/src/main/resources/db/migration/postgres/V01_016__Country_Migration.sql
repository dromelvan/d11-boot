-- We need the old data to be in a 'data' schema.
INSERT INTO country
SELECT * FROM data.countries;
SELECT setval('country_id_seq', (SELECT last_value FROM data.countries_id_seq));
