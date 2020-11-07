-- We need the old data to be in a 'data' schema.
INSERT INTO d11_match_week
SELECT id, d11_league_id, match_day_id, match_day_number, date, created_at, updated_at from data.d11_match_days;
SELECT setval('d11_match_week_id_seq', (SELECT last_value FROM data.d11_match_days_id_seq));
