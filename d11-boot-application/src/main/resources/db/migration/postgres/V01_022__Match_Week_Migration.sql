-- We need the old data to be in a 'data' schema.
INSERT INTO match_week
SELECT id, premier_league_id, match_day_number, date, status, created_at, updated_at FROM data.match_days;
SELECT setval('match_week_id_seq', (SELECT last_value FROM data.match_days_id_seq));
