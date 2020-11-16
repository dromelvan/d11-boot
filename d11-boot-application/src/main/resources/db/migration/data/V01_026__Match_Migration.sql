-- We need the old data to be in a 'data' schema.
INSERT INTO match
select id, home_team_id, away_team_id, match_day_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals,
       previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at from data.matches;
SELECT setval('match_id_seq', (SELECT last_value FROM data.matches_id_seq));
