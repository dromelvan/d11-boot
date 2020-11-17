-- Delete existing data
DELETE FROM match WHERE id > 0;
SELECT setval('match_id_seq', 1);

DELETE FROM d11_match_week WHERE id > 0;
SELECT setval('d11_match_week_id_seq', 1);

DELETE FROM match_week WHERE id > 0;
SELECT setval('match_week_id_seq', 1);

DELETE FROM d11_league WHERE id > 0;
SELECT setval('d11_league_id_seq', 1);

DELETE FROM premier_league WHERE id > 0;
SELECT setval('premier_league_id_seq', 1);

DELETE FROM season WHERE id > 0;
SELECT setval('season_id_seq', 1);

DELETE FROM d11_team WHERE id > 0;
SELECT setval('d11_team_id_seq', 1);

DELETE FROM team WHERE id > 0;
SELECT setval('team_id_seq', 1);

DELETE FROM player WHERE id > 0;
SELECT setval('player_id_seq', 1);

DELETE FROM country WHERE id > 0;
SELECT setval('country_id_seq', 1);

DELETE FROM "position" WHERE id > 0;
SELECT setval('position_id_seq', 1);

DELETE FROM stadium WHERE id > 0;
SELECT setval('stadium_id_seq', 1);

DELETE FROM application_user WHERE id > 0;
SELECT setval('application_user_id_seq', 1);

-- We need the old data to be in a 'data' schema.
-- Seasons
INSERT INTO season
SELECT * FROM data.seasons;
SELECT setval('season_id_seq', (SELECT last_value FROM data.seasons_id_seq));

-- Premier Leagues
INSERT INTO premier_league
SELECT * FROM data.premier_leagues;
SELECT setval('premier_league_id_seq', (SELECT last_value FROM data.premier_leagues_id_seq));

-- D11 Leagues
INSERT INTO d11_league
SELECT * FROM data.d11_leagues;
SELECT setval('d11_league_id_seq', (SELECT last_value FROM data.d11_leagues_id_seq));

-- Stadia
INSERT INTO stadium
SELECT id, name, city, capacity, opened, photo_file_name, created_at, updated_at FROM data.stadia;
SELECT setval('stadium_id_seq', (SELECT last_value FROM data.stadia_id_seq));

UPDATE stadium
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;

-- Teams
INSERT INTO team
SELECT id, stadium_id, whoscored_id, name, short_name, code, nickname, established, motto, colour, dummy, club_crest_file_name, created_at, updated_at FROM data.teams;
SELECT setval('team_id_seq', (SELECT last_value FROM data.teams_id_seq));

UPDATE team
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;

-- Users
INSERT INTO application_user
select id, name, email, encrypted_password, administrator, created_at, updated_at from data.users;
SELECT setval('application_user_id_seq', (SELECT last_value FROM data.users_id_seq));

-- D11 Teams
INSERT INTO d11_team
SELECT id, owner_id, co_owner_id, name, short_name, code, dummy, club_crest_file_name, created_at, updated_at FROM data.d11_teams;
SELECT setval('d11_team_id_seq', (SELECT last_value FROM data.d11_teams_id_seq));

UPDATE d11_team
SET photo_file_name = concat(id, '.png')
WHERE photo_file_name IS NOT NULL;

-- Countries
INSERT INTO country
SELECT * FROM data.countries;
SELECT setval('country_id_seq', (SELECT last_value FROM data.countries_id_seq));

-- Players
INSERT INTO player
SELECT id, country_id, (CASE WHEN whoscored_id IS NULL THEN 0 ELSE whoscored_id END), first_name, last_name, full_name, parameterized_name, date_of_birth, height, player_photo_file_name, created_at, updated_at FROM data.players;
SELECT setval('player_id_seq', (SELECT last_value FROM data.players_id_seq));

UPDATE player
SET whoscored_id = 0 WHERE whoscored_id = 1 and id != 142;

-- Positions
INSERT INTO "position"
SELECT * FROM data.positions;
SELECT setval('position_id_seq', (SELECT last_value FROM data.positions_id_seq));

-- Match weeks
INSERT INTO match_week
SELECT id, premier_league_id, match_day_number, date, status, created_at, updated_at FROM data.match_days;
SELECT setval('match_week_id_seq', (SELECT last_value FROM data.match_days_id_seq));

-- D11 match weeks
INSERT INTO d11_match_week
SELECT id, d11_league_id, match_day_id, match_day_number, date, created_at, updated_at from data.d11_match_days;
SELECT setval('d11_match_week_id_seq', (SELECT last_value FROM data.d11_match_days_id_seq));

-- Matches
INSERT INTO match
select id, home_team_id, away_team_id, match_day_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals,
       previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at from data.matches;
SELECT setval('match_id_seq', (SELECT last_value FROM data.matches_id_seq));
