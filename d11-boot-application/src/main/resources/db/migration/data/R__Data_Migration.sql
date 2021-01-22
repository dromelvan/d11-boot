-- Delete existing data
DELETE FROM player_season_stat WHERE id > 0;
SELECT setval('player_season_stat_id_seq', 1);

DELETE FROM d11_team_table_stat WHERE id > 0;
SELECT setval('d11_team_table_stat_id_seq', 1);

DELETE FROM team_table_stat WHERE id > 0;
SELECT setval('team_table_stat_id_seq', 1);

DELETE FROM player_match_stat WHERE id > 0;
SELECT setval('player_match_stat_id_seq', 1);

DELETE FROM substitution WHERE id > 0;
SELECT setval('substitution_id_seq', 1);

DELETE FROM card WHERE id > 0;
SELECT setval('card_id_seq', 1);

DELETE FROM goal WHERE id > 0;
SELECT setval('goal_id_seq', 1);

DELETE FROM d11_match WHERE id > 0;
SELECT setval('d11_match_id_seq', 1);

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

UPDATE player
SET photo_file_name = CONCAT(parameterized_name, '-', id, '.png')
WHERE photo_file_name IS NOT NULL;

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
SELECT id, d11_league_id, match_day_id, match_day_number, date, created_at, updated_at FROM data.d11_match_days;
SELECT setval('d11_match_week_id_seq', (SELECT last_value FROM data.d11_match_days_id_seq));

-- Matches
INSERT INTO match
SELECT id, home_team_id, away_team_id, match_day_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals,
       previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at FROM data.matches;
SELECT setval('match_id_seq', (SELECT last_value FROM data.matches_id_seq));

-- D11 Matches
INSERT INTO d11_match
SELECT id, home_d11_team_id, away_d11_team_id, d11_match_day_id, date, home_team_goals, away_team_goals, home_team_points, away_team_points,
       previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at FROM data.d11_matches;
SELECT setval('d11_match_id_seq', (SELECT last_value FROM data.d11_matches_id_seq));

-- Goals
INSERT INTO goal
-- Apparently match events aren't deleted in v3 when re-uploading match stats, they just have their match id set to null.
SELECT * FROM data.goals where match_id is not null;
SELECT setval('goal_id_seq', (SELECT last_value FROM data.goals_id_seq));

-- Cards
INSERT INTO card
SELECT * FROM data.cards where match_id is not null;
SELECT setval('card_id_seq', (SELECT last_value FROM data.cards_id_seq));

-- Substitutions
INSERT INTO substitution
SELECT * FROM data.substitutions where match_id is not null;
SELECT setval('substitution_id_seq', (SELECT last_value FROM data.substitutions_id_seq));

-- Player match stats
INSERT INTO player_match_stat
SELECT pms.id, player_id, match_id, d11m.id as d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time, goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points, pms.created_at, pms.updated_at
FROM data.player_match_stats pms
     JOIN data.matches m ON pms.match_id = m.id
     JOIN data.d11_match_days d11md ON m.match_day_id = d11md.match_day_id
     LEFT JOIN data.d11_matches d11m ON d11m.d11_match_day_id = d11md.id AND (d11m.home_d11_team_id = pms.d11_team_id OR d11m.away_d11_team_id = pms.d11_team_id);
SELECT setval('player_match_stat_id_seq', (SELECT last_value FROM data.player_match_stats_id_seq));

-- Team table stats
INSERT INTO team_table_stat
SELECT id, team_id, premier_league_id, match_day_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       created_at, updated_at FROM data.team_table_stats;
SELECT setval('team_table_stat_id_seq', (SELECT last_value FROM data.team_table_stats_id_seq));

-- D11 team table stats
INSERT INTO d11_team_table_stat
SELECT id, d11_team_id, d11_league_id, d11_match_day_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       created_at, updated_at FROM data.d11_team_table_stats;
SELECT setval('d11_team_table_stat_id_seq', (SELECT last_value FROM data.d11_team_table_stats_id_seq));

-- Player season stats
INSERT INTO player_season_stat
SELECT psi.id, psi.player_id, psi.season_id, team_id, d11_team_id, position_id, value, ranking, points, form_points, points_per_appearance, goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards,
       substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, psi.created_at, psi.updated_at
       FROM data.player_season_infos psi JOIN data.player_season_stats pss ON psi.player_id = pss.player_id AND psi.season_id = pss.season_id;
SELECT setval('player_season_stat_id_seq', (SELECT last_value FROM data.player_season_stats_id_seq));
