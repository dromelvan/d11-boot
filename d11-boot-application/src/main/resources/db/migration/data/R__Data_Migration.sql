-- Delete existing data
DELETE FROM d11_team_season_stat WHERE id > 0;
SELECT setval('d11_team_season_stat_id_seq', 1);

DELETE FROM team_season_stat WHERE id > 0;
SELECT setval('team_season_stat_id_seq', 1);

DELETE FROM player_season_stat WHERE id > 0;
SELECT setval('player_season_stat_id_seq', 1);

DELETE FROM d11_team_match_week_stat WHERE id > 0;
SELECT setval('d11_team_match_week_stat_id_seq', 1);

DELETE FROM team_match_week_stat WHERE id > 0;
SELECT setval('team_match_week_stat_id_seq', 1);

UPDATE match_week SET most_valuable_player_id = null;
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

UPDATE season SET status = 99 WHERE status = 3;
UPDATE season SET status = 3 WHERE status = 2;
UPDATE season SET status = 2 WHERE status = 99;

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
SELECT id, premier_league_id + 1, null, null, null, match_day_number, date, 0, status, created_at, updated_at FROM data.match_days;
SELECT setval('match_week_id_seq', (SELECT last_value FROM data.match_days_id_seq));

UPDATE match_week SET status = 99 WHERE status = 3;
UPDATE match_week SET status = 3 WHERE status = 2;
UPDATE match_week SET status = 2 WHERE status = 99;

-- Matches
INSERT INTO match
SELECT id, home_team_id, away_team_id, match_day_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals,
       previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at FROM data.matches;
SELECT setval('match_id_seq', (SELECT last_value FROM data.matches_id_seq));

UPDATE match SET status = 99 WHERE status = 3;
UPDATE match SET status = 3 WHERE status = 2;
UPDATE match SET status = 2 WHERE status = 99;

UPDATE match SET status = 4 WHERE datetime >= '3000-01-01';
-- Set datetime of postponed matches to match week date at 17:00
UPDATE match SET datetime = (
    SELECT datetime FROM (
        SELECT match.id, match_week.date + '17:00:00'::TIME AS datetime
        FROM match
        JOIN match_week ON match.match_week_id = match_week.id
    ) AS match_date_time
    WHERE match.id = match_date_time.id
) WHERE match.status = 4;

-- D11 Matches
INSERT INTO d11_match
SELECT id, home_d11_team_id, away_d11_team_id, d11_match_day_id, date + '17:00:00'::TIME, home_team_goals, away_team_goals, home_team_points, away_team_points,
       previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at FROM data.d11_matches;
SELECT setval('d11_match_id_seq', (SELECT last_value FROM data.d11_matches_id_seq));

UPDATE d11_match SET status = 99 WHERE status = 3;
UPDATE d11_match SET status = 3 WHERE status = 2;
UPDATE d11_match SET status = 2 WHERE status = 99;
-- Update datetimes. Set all to match week at 17:00 to begin with.
UPDATE d11_match SET datetime = (
    SELECT datetime FROM (
        SELECT d11_match.id, match_week.date + '17:00:00'::TIME AS datetime
        FROM d11_match
        JOIN match_week ON d11_match.match_week_id = match_week.id
    ) AS match_date_time
    WHERE d11_match.id = match_date_time.id
);

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

-- Team match week stats
INSERT INTO team_match_week_stat
SELECT id, team_id, match_day_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       created_at, updated_at FROM data.team_table_stats;
SELECT setval('team_match_week_stat_id_seq', (SELECT last_value FROM data.team_table_stats_id_seq));

-- D11 team match week stats
INSERT INTO d11_team_match_week_stat
SELECT id, d11_team_id, d11_match_day_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       created_at, updated_at FROM data.d11_team_table_stats;
SELECT setval('d11_team_match_week_stat_id_seq', (SELECT last_value FROM data.d11_team_table_stats_id_seq));

-- Team season stats
INSERT INTO team_season_stat
SELECT NEXTVAL('team_season_stat_id_seq') id, subquery.* FROM(
SELECT tts.team_id, season_id, CAST(null AS INTEGER) win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       now()::timestamp created_at, now()::timestamp updated_at
FROM data.team_table_stats tts
    JOIN data.match_days mds ON tts.match_day_id = mds.id
    JOIN data.premier_leagues pls ON mds.premier_league_id = pls.id
WHERE mds.match_day_number = 38
ORDER BY season_id, ranking
) subquery;

-- D11 team season stats
INSERT INTO d11_team_season_stat
SELECT NEXTVAL('d11_team_season_stat_id_seq') id, subquery.* FROM(
SELECT d11tts.d11_team_id, season_id, CAST(null AS INTEGER) win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking,
       home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking,
       away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking,
       now()::timestamp created_at, now()::timestamp updated_at
FROM data.d11_team_table_stats d11tts
    JOIN data.d11_match_days d11mds ON d11tts.d11_match_day_id = d11mds.id
    JOIN data.d11_leagues d11ls ON d11mds.d11_league_id = d11ls.id
WHERE d11mds.match_day_number = 38
ORDER BY season_id, ranking
) subquery;

-- Player season stats
INSERT INTO player_season_stat
SELECT psi.id, psi.player_id, psi.season_id, team_id, d11_team_id, position_id, CAST(null AS INTEGER) win_count, value, ranking, points, form_points, points_per_appearance, goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards,
       substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, psi.created_at, psi.updated_at
       FROM data.player_season_infos psi JOIN data.player_season_stats pss ON psi.player_id = pss.player_id AND psi.season_id = pss.season_id
       -- Not sure if it's a good idea to skip dummy rows. We'll see.
       WHERE pss.ranking > 0;
SELECT setval('player_season_stat_id_seq', (SELECT MAX(id) FROM player_season_stat));




-- Update match week premier league leader
UPDATE match_week
SET premier_league_leader_id = (
    SELECT team_match_week_stat_id FROM (
        SELECT match_week.id AS match_week_id, team_match_week_stat.id AS team_match_week_stat_id
        FROM match_week
        JOIN team_match_week_stat ON match_week.id = team_match_week_stat.match_week_id
        WHERE team_match_week_stat.ranking = 1
    ) premier_league_leader_query
    WHERE premier_league_leader_query.match_week_id = match_week.id
);

-- Update match week D11 league leader
UPDATE match_week
SET d11_league_leader_id = (
    SELECT d11_team_match_week_stat_id FROM (
        SELECT match_week.id AS match_week_id, d11_team_match_week_stat.id AS d11_team_match_week_stat_id
        FROM match_week
        JOIN d11_team_match_week_stat ON match_week.id = d11_team_match_week_stat.match_week_id
        WHERE d11_team_match_week_stat.ranking = 1
        ) d11_league_leader_query
    WHERE d11_league_leader_query.match_week_id = match_week.id
);

-- Update match week elapsed
UPDATE match_week
SET elapsed = (
    SELECT elapsed FROM (
        SELECT match_week_id, COUNT(*) AS elapsed
        FROM match
        WHERE status = 3
        GROUP BY match_week_id
    ) elapsed_query
    WHERE elapsed_query.match_week_id = match_week.id
) WHERE EXISTS (SELECT * FROM match WHERE status = 3 and match.match_week_id = match_week.id);

-- Update match week most valuable player
UPDATE match_week
SET most_valuable_player_id = (
    SELECT player_match_stat.id
    FROM player_match_stat
    JOIN match ON player_match_stat.match_id = match.id
    WHERE match.match_week_id = match_week.id
    ORDER BY points DESC, rating DESC
    LIMIT 1
);

-- Update team season stat win count
UPDATE team_season_stat update_table
SET win_count = (
SELECT win_count FROM(
    SELECT tss.id,
           (SELECT COUNT(*)
            FROM team_season_stat
            WHERE ranking = 1
              AND team_id = tss.team_id
              AND id <= tss.id) win_count
    FROM team_season_stat tss
    WHERE ranking = 1
      AND id = update_table.id
    ORDER BY season_id) subquery);

-- Update D11 team season stat win count
UPDATE d11_team_season_stat update_table
SET win_count = (
SELECT win_count FROM(
    SELECT d11tss.id,
           (SELECT COUNT(*)
            FROM d11_team_season_stat
            WHERE ranking = 1
              AND d11_team_id = d11tss.d11_team_id
              AND id <= d11tss.id) win_count
    FROM d11_team_season_stat d11tss
    WHERE ranking = 1
      AND id = update_table.id
    ORDER BY season_id) subquery);

-- Update player season stat win count
UPDATE player_season_stat update_table
SET win_count = (
SELECT win_count FROM(
    SELECT pss.id,
           (SELECT COUNT(*)
            FROM player_season_stat
            WHERE ranking = 1
              AND player_id = pss.player_id
              AND season_id <= pss.season_id) win_count
    FROM player_season_stat pss
    WHERE ranking = 1
      AND id = update_table.id
    ORDER BY season_id) subquery);

-- Get the datetime of the first Premier League match that affects each D11 match an set that as datetimes. A bit convoluted.
SELECT d11_match.id, match.datetime
INTO TEMPORARY TABLE d11_match_datetimes
FROM match
JOIN player_match_stat ON match.id = player_match_stat.match_id
JOIN d11_match ON d11_match.id = player_match_stat.d11_match_id
GROUP BY d11_match.id, match.datetime
ORDER BY d11_match.id, match.datetime;

UPDATE d11_match
SET datetime = (
    SELECT MIN(datetime) FROM d11_match_datetimes WHERE id = d11_match.id
) WHERE EXISTS (SELECT * FROM player_match_stat WHERE d11_match_id = d11_match.id);
