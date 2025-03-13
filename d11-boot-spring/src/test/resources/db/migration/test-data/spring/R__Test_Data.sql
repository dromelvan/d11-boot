-- User
INSERT INTO application_user (name, email, encrypted_password, administrator, confirm_registration_token, reset_password_token)
    VALUES('User', 'user@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', false, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111');
INSERT INTO application_user (name, email, encrypted_password, administrator, confirm_registration_token, reset_password_token)
    VALUES('Administrator', 'administrator@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', true, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222');
INSERT INTO application_user (name, email, encrypted_password, administrator, confirm_registration_token, reset_password_token)
    VALUES('Unconfirmed', 'unconfirmed@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', false, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '33333333-3333-3333-3333-333333333333');
INSERT INTO application_user (name, email, encrypted_password, administrator, confirm_registration_token, reset_password_token)
    VALUES('User2', 'user2@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', true, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '33333333-3333-3333-3333-333333333333');

-- Refresh Token
INSERT INTO refresh_token (application_user_id, uuid, expires_at)
VALUES(1, '11111111-1111-1111-1111-111111111111', null);
INSERT INTO refresh_token (application_user_id, uuid, expires_at)
VALUES(1, '22222222-2222-2222-2222-222222222222', NOW()::timestamp + 1);
INSERT INTO refresh_token (application_user_id, uuid, expires_at)
VALUES(2, '33333333-3333-3333-3333-333333333333', null);
INSERT INTO refresh_token (application_user_id, uuid, expires_at)
VALUES(2, '44444444-4444-4444-4444-444444444444', NOW()::timestamp - 1);

-- Country
INSERT INTO country (name, iso) VALUES('CountryA', 'AA');
INSERT INTO country (name, iso) VALUES('CountryB', 'BB');

-- Stadium
INSERT INTO stadium (name, city, capacity, opened, photo_file_name)
VALUES('StadiumA', 'CityA', 11111, 2020, '1.png');
INSERT INTO stadium (name, city, capacity, opened, photo_file_name)
VALUES('StadiumB', 'CityB', 22222, 2020, '2.png');

-- Team
INSERT INTO team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, established, colour, dummy, photo_file_name, url)
VALUES(1, 11, 1, 'None', 'None', 'DUM', 2020, 'None', true, null, 'https://test.com');
INSERT INTO team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, established, colour, dummy, photo_file_name, url)
VALUES(1, 11111, 2, 'TeamA', 'ShortA', 'TMA', 2020, 'ColourA', false, '1.png', 'https://test.com');
INSERT INTO team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, established, colour, dummy, photo_file_name, url)
VALUES(2, 22222, 3, 'TeamB', 'ShortB', 'TMB', 2020, 'ColourB', false, '2.png', 'https://test.com');
INSERT INTO team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, established, colour, dummy, photo_file_name, url)
VALUES(2, 33333, 4, 'TeamC', 'ShortC', 'TMC', 2020, 'ColourC', false, '3.png', 'https://test.com');

-- D11 Team
INSERT INTO d11_team (owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name)
VALUES(null, null, 'None', 'None', 'DUM', true, null);
INSERT INTO d11_team (owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name)
VALUES(1, 4, 'D11TeamA', 'ShortA', 'DTA', false, '1.png');
INSERT INTO d11_team (owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name)
VALUES(2, null, 'D11TeamB', 'ShortB', 'DTB', false, '2.png');
INSERT INTO d11_team (owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name)
VALUES(3, null, 'D11TeamC', 'ShortC', 'DTC', false, '3.png');

-- Player
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(1, 111111, 1, 'FirstA', 'LastA', 'FullA', 'firsta-lasta', '2020-01-01', 111, '1.png', true);
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(2, 222222, 2, 'FirstB', 'LastB', 'FullB', 'firstb-lastb', '2020-01-01', 222, '2.png', true);
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(1, 333333, 3, 'FirstC', 'LastC', 'FullC', 'firstc-lastc', '2020-01-01', 333, '3.png', true);
-- Players used for search tests.
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(1, 444444, 4, 'Foo', 'Bar', null, 'foo-bar', '2020-01-01', 111, '4.png', true);
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(1, 555555, 5, 'Bar', 'Foo', null, 'bar-foo', '2020-01-01', 111, '5.png', true);
INSERT INTO player (country_id, whoscored_id, premier_league_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, verified)
VALUES(1, 666666, 6, '', 'Foo', null, 'foo', '2020-01-01', 111, '6.png', true);

-- Position
INSERT INTO position (name, code, max_count, defender, sort_order)
VALUES('Goalkeeper', 'GK', 1, true, 1);
INSERT INTO position (name, code, max_count, defender, sort_order)
VALUES('Defender', 'D', 4, true, 2);
INSERT INTO position (name, code, max_count, defender, sort_order)
VALUES('Midfielder', 'MF', 4, false, 3);
INSERT INTO position (name, code, max_count, defender, sort_order)
VALUES('Forward', 'F', 2, false, 4);

-- Season
INSERT INTO season (name, d11_team_budget, d11_team_max_transfers, status, date) VALUES('2020-2021', 600, 13, 3, '2020-01-01');
INSERT INTO season (name, d11_team_budget, d11_team_max_transfers, status, date) VALUES('2021-2022', 600, 13, 1, '2021-01-01');

-- Match week
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(1, null, null, null, 1, CURRENT_DATE - 3, 10, 3);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(1, null, null, null, 2, CURRENT_DATE - 2, 10, 3);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 1, CURRENT_DATE - 1, 0, 0);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 2, CURRENT_DATE, 7, 1);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 3, CURRENT_DATE + 1, 0, 0);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 4, CURRENT_DATE + 2, 0, 0);

-- Match
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(1, 2, 1, 1, 1, '2020-01-01 17:00:00', 0, 1, 0, 0, 'FT', 3);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(3, 4, 1, 2, 2, '2020-01-02 17:00:00', 2, 1, 1, 0, 'FT', 3);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(2, 1, 2, 1, 3, '2020-02-01 17:00:00', 0, 1, 0, 0, 'FT', 2);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(4, 3, 2, 2, 4, '2020-02-02 17:00:00', 2, 1, 1, 0, 'FT', 2);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(1, 2, 3, 1, 5, '2021-01-01 17:00:00', 0, 1, 0, 0, 'HT', 1);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(3, 4, 3, 2, 6, '2021-01-02 17:00:00', 2, 1, 1, 0, '55', 1);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(2, 1, 4, 1, 7, '2021-02-01 22:00:00', 0, 1, 0, 0, 'N/A', 0);
INSERT INTO match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals_scored, away_team_goals_scored,
                   previous_home_team_goals_scored, previous_away_team_goals_scored, elapsed, status)
VALUES(4, 3, 4, 2, 8, '2021-02-01 17:00:00', 2, 1, 1, 0, 'N/A', 4);

-- D11 Match
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(1, 2, 1, '2020-01-01', 0, 1, 0, 1, 0, 0, 0, 0, 'FT', 2);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(3, 4, 1, '2020-01-02', 2, 1, 5, 1, 0, 0, 0, 0, 'FT', 2);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(2, 1, 2, '2020-02-01', 0, 1, 0, 1, 0, 0, 0, 0, 'FT', 2);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(4, 3, 2, '2020-02-02', 2, 1, 5, 1, 0, 0, 0, 0, 'FT', 2);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(1, 2, 3, '2021-01-01', 0, 1, 0, 1, 0, 0, 0, 0, 'HT', 1);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(3, 4, 3, '2021-01-02', 2, 1, 5, 1, 0, 0, 0, 0, '55', 1);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(2, 1, 4, '2020-02-01', 0, 1, 0, 1, 0, 0, 0, 0, 'N/A', 0);
INSERT INTO d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals_scored, away_team_goals_scored, home_team_points, away_team_points,
                       previous_home_team_goals_scored, previous_away_team_goals_scored, previous_home_team_points, previous_away_team_points, elapsed, status)
VALUES(4, 3, 4, '2020-02-02', 2, 1, 5, 1, 0, 0, 0, 0, 'N/A', 0);

-- Goal
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(1, 1, 1, 45, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(1, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(1, 2, 2, 60, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(1, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(2, 1, 2, 45, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(2, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(2, 2, 1, 60, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(2, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(3, 1, 1, 45, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(3, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(3, 2, 2, 60, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(3, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(4, 1, 2, 45, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(4, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(4, 2, 1, 60, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(4, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(5, 1, 1, 45, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(5, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(5, 2, 2, 60, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(5, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(6, 1, 2, 45, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(6, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(6, 2, 1, 60, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(6, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(7, 1, 1, 45, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(7, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(7, 2, 2, 60, 0, false, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(7, 2, 2, 90, 0, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(8, 1, 2, 45, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(8, 1, 1, 90, 5, true, false);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(8, 2, 1, 60, 0, false, true);
INSERT INTO goal (match_id, team_id, player_id, time, added_time, penalty, own_goal)
VALUES(8, 2, 2, 90, 0, true, false);

-- Player match stat
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (1, 1, 1, 2, 2, 1, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 700, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (2, 1, 1, 2, 2, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 710, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (3, 1, 1, 3, 3, 3, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 720, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (4, 1, 1, 3, 3, 4, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 730, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (1, 2, 2, 2, 2, 1, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 700, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (2, 2, 2, 2, 2, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 710, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (3, 2, 2, 3, 3, 3, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 720, 0);
INSERT INTO player_match_stat (player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time,
                               goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points)
VALUES (4, 2, 2, 3, 3, 4, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 730, 0);

-- Update match_week most_valuable_player_id after player_match_stats are inserted
UPDATE match_week SET most_valuable_player_id = id where most_valuable_player_id is null;

-- Player season stat
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (1, 1, 1, 2, 1, 1, 1, 50, 1, 100, 50, '-1,2,3', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90);
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (2, 1, 2, 3, 2, null, null, 50, 2, 50, 25, '', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 732, 1, 1, 0, 90);
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (3, 1, 1, 2, 1, 2, 1, 50, 3, 50, 50, '2,3,4,5', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 632, 1, 1, 0, 90);
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (1, 2, 1, 3, 2, 3, 1, 50, 1, 55, 25, '1,2,3,4,5', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90);
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (2, 2, 2, 3, 3, 4, 1, 50, 3, 45, 25, '1,2,3,4,5', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90);
INSERT INTO player_season_stat (player_id, season_id, team_id, d11_team_id, position_id, shirt_number, win_count, fee, ranking, points, form_points, form_match_points, points_per_appearance,
                                goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match,
                                rating, games_started, games_substitute, games_did_not_participate, minutes_played)
VALUES (3, 2, 2, 2, 4, 5, 1, 50, 4, 40, 25, '1,2,3,4,5', 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90);

-- Team match week stat
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (2, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (3, 1, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (2, 2, 2, 1, 0, 1, 3, 3, 0, 3, 3, '3,0', 1, 1);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (3, 2, 1, 1, 0, 1, 3, 3, 0, 3, 3, '0,3', 2, 2);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (3, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (2, 4, 2, 1, 1, 0, 2, 0, 2, 4, 4, '1,3', 1, 2);
INSERT INTO team_match_week_stat (team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points,
                                  form_match_points, ranking, previous_ranking)
VALUES (3, 4, 2, 0, 1, 1, 0, 2, -2, 1, 1, '1,0', 2, 1);
-- Update match_week premier_league_leader_id after team_match_week_stats are inserted
UPDATE match_week SET premier_league_leader_id = id where premier_league_leader_id is null;

-- Team season stat
INSERT INTO team_season_stat (team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                              points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2);
INSERT INTO team_season_stat (team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                              points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 1, null, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1);
INSERT INTO team_season_stat (team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                              points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 2, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2);
INSERT INTO team_season_stat (team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                              points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 2, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1);

-- D11 team match week stat
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 1, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 2, 2, 1, 0, 1, 3, 3, 0, 3, 3, '3,0', 1, 1);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 2, 1, 1, 0, 1, 3, 3, 0, 3, 3, '0,3', 2, 2);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (1, 4, 2, 1, 1, 0, 2, 0, 2, 4, 4, '1,3', 1, 2);
INSERT INTO d11_team_match_week_stat (d11_team_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                      points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 4, 2, 0, 1, 1, 0, 2, -2, 1, 1, '1,0', 2, 1);
-- Update match_week d11_league_leader_id after d11_team_match_week_stats are inserted
UPDATE match_week SET d11_league_leader_id = id where d11_league_leader_id is null;

-- D11 team season stat
INSERT INTO d11_team_season_stat (d11_team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                  points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2);
INSERT INTO d11_team_season_stat (d11_team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                  points, form_points, form_match_points, ranking, previous_ranking)
VALUES (3, 1, null, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1);
INSERT INTO d11_team_season_stat (d11_team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                  points, form_points, form_match_points, ranking, previous_ranking)
VALUES (2, 2, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2);
INSERT INTO d11_team_season_stat (d11_team_id, season_id, win_count, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference,
                                  points, form_points, form_match_points, ranking, previous_ranking)
VALUES (3, 2, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1);

-- Transfer window
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(1, 0, true, 3, CURRENT_DATE - 2);
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(2, 1, false, 3, CURRENT_DATE - 1);
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(3, 0, true, 3, CURRENT_DATE);
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(4, 1, false, 3, CURRENT_DATE + 1);

-- Transfer day
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(1, 1, 3, CURRENT_DATE - 6);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(1, 2, 3, CURRENT_DATE - 5);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(2, 1, 3, CURRENT_DATE - 4);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(2, 2, 3, CURRENT_DATE - 3);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(3, 1, 3, CURRENT_DATE - 2);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(3, 2, 3, CURRENT_DATE - 1);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(4, 1, 1, CURRENT_DATE - 1);
INSERT INTO transfer_day (transfer_window_id, transfer_day_number, status, datetime)
VALUES(4, 2, 0, CURRENT_DATE + 1);

-- Transfer listing
INSERT INTO transfer_listing (transfer_day_id, player_id, team_id, d11_team_id, position_id, ranking, points, form_points, form_match_points, points_per_appearance,
                              goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off,
                              man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, new_player)
VALUES (1, 1, 1, 2, 1, 1, 20, 10, '2,2,2,2,2', 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, false);
INSERT INTO transfer_listing (transfer_day_id, player_id, team_id, d11_team_id, position_id, ranking, points, form_points, form_match_points, points_per_appearance,
                              goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off,
                              man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, new_player)
VALUES (1, 2, 2, 1, 2, 2, 20, 10, '2,2,2,2,2', 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, false);
INSERT INTO transfer_listing (transfer_day_id, player_id, team_id, d11_team_id, position_id, ranking, points, form_points, form_match_points, points_per_appearance,
                              goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off,
                              man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, new_player)
VALUES (7, 1, 1, 2, 1, 1, 20, 10, '2,2,2,2,2', 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, false);
INSERT INTO transfer_listing (transfer_day_id, player_id, team_id, d11_team_id, position_id, ranking, points, form_points, form_match_points, points_per_appearance,
                              goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off,
                              man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, new_player)
VALUES (8, 2, 2, 2, 2, 2, 20, 10, '2,2,2,2,2', 2, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, true);

-- Transfer bid
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 1, 1, 1, 1, 200, 100, true);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 1, 2, 1, 2, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 1, 3, 1, 3, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 2, 1, 2, 1, 200, 100, true);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 2, 2, 2, 2, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(1, 2, 3, 2, 3, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 1, 1, 1, 1, 200, 100, true);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 1, 2, 1, 2, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 1, 3, 1, 3, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 2, 1, 2, 1, 200, 100, true);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 2, 2, 2, 2, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(2, 2, 3, 2, 3, 100, 50, false);
INSERT INTO transfer_bid (transfer_day_id, player_id, d11_team_id, player_ranking, d11_team_ranking, fee, active_fee, successful)
VALUES(8, 2, 3, 2, 3, 100, 50, false);

-- Transfer
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(1, 1, 3, 100);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(1, 2, 3, 50);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(1, 3, 2, 100);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(1, 4, 2, 50);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(2, 1, 3, 100);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(2, 2, 3, 50);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(2, 3, 2, 100);
INSERT INTO transfer (transfer_day_id, player_id, d11_team_id, fee)
VALUES(2, 4, 2, 50);

