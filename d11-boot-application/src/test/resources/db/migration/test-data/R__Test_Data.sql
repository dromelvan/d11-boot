-- INSERT INTO application_user(
--     id, name, email, encrypted_password, administrator, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO application_user VALUES(1, 'User', 'user@email.com', 'password', false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO application_user VALUES(2, 'Administrator', 'administrator@email.com', 'password', true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO country(
--     id, name, iso, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?);
INSERT INTO country VALUES(1, 'CountryA', 'AA', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO country VALUES(2, 'CountryB', 'BB', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO stadium(
--     id, name, city, capacity, opened, photo_file_name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO stadium VALUES(1, 'StadiumA', 'CityA', 11111, 2020, '1.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO stadium VALUES(2, 'StadiumB', 'CityB', 22222, 2020, '2.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO team(
--     id, stadium_id, whoscored_id, name, short_name, code, nickname, established, motto, colour, dummy, photo_file_name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO team VALUES(1, 1, 11111, 'TeamA', 'ShortA', 'TMA', 'NicknameA', 2020, 'MottoA', 'ColourA', false, '1.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team VALUES(2, 2, 22222, 'TeamB', 'ShortB', 'TMB', 'NicknameB', 2020, 'MottoB', 'ColourB', false, '2.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO d11_team(
--     id, owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO d11_team VALUES(1, 1, null, 'D11TeamA', 'ShortA', 'DTA', false, '1.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team VALUES(2, 2, null, 'D11TeamB', 'ShortB', 'DTB', false, '2.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO "position"(
--     id, name, code, defender, sort_order, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO position VALUES(1, 'Defender', 'D', true, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO position VALUES(2, 'Non Defender', 'ND', false, 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO player(
--     id, country_id, whoscored_id, first_name, last_name, full_name, parameterized_name, date_of_birth, height, photo_file_name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO player VALUES(1, 1, 111111, 'FirstA', 'LastA', 'FullA', 'firsta-lasta', '2020-01-01', 111, '1.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player VALUES(2, 2, 222222, 'FirstB', 'LastB', 'FullB', 'firstb-lastb', '2020-01-01', 222, '2.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player VALUES(3, 1, 333333, 'FirstC', 'LastC', 'FullC', 'firstc-lastc', '2020-01-01', 333, '3.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
-- Players used for search tests.
INSERT INTO player VALUES(4, 1, 444444, 'Foo', 'Bar', null, 'foo-bar', '2020-01-01', 111, '4.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player VALUES(5, 1, 555555, 'Bar', 'Foo', null, 'bar-foo', '2020-01-01', 111, '5.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player VALUES(6, 1, 666666, 'Foo', '', null, 'foo', '2020-01-01', 111, '6.png', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO season(
--     id, name, status, date, legacy, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO season VALUES(1, '2020-2021', 2, '2020-01-01', false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO season VALUES(2, '2021-2022', 1, '2021-01-01', false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO premier_league(
--     id, season_id, name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?);
INSERT INTO premier_league VALUES(1, 1, 'PremierLeagueA', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO premier_league VALUES(2, 2, 'PremierLeagueB', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO match_week(
--     id, premier_league_id, match_week_number, date, status, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO match_week VALUES(1, 1, 1, CURRENT_DATE - 2, 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match_week VALUES(2, 1, 2, CURRENT_DATE - 1, 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match_week VALUES(3, 2, 1, CURRENT_DATE, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match_week VALUES(4, 2, 2, CURRENT_DATE + 1, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO match(
--     id, home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO match VALUES(1, 1, 2, 1, 1, 1, '2020-01-01', 0, 1, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(2, 2, 1, 1, 2, 2, '2020-01-02', 2, 1, 1, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(3, 1, 2, 2, 1, 3, '2020-02-01', 0, 1, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(4, 2, 1, 2, 2, 4, '2020-02-02', 2, 1, 1, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(5, 1, 2, 3, 1, 5, '2021-01-01', 0, 1, 0, 0, 'HT', 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(6, 2, 1, 3, 2, 6, '2021-01-02', 2, 1, 1, 0, '55', 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(7, 1, 2, 4, 1, 7, '2020-02-01', 0, 1, 0, 0, 'N/A', 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO match VALUES(8, 2, 1, 4, 2, 8, '2020-02-02', 2, 1, 1, 0, 'N/A', 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO goal(
--     id, match_id, team_id, player_id, "time", added_time, penalty, own_goal, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO goal VALUES(1, 1, 1, 1, 45, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(2, 1, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(3, 1, 2, 2, 60, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(4, 1, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(5, 2, 1, 2, 45, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(6, 2, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(7, 2, 2, 1, 60, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(8, 2, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(9, 3, 1, 1, 45, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(10, 3, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(11, 3, 2, 2, 60, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(12, 3, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(13, 4, 1, 2, 45, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(14, 4, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(15, 4, 2, 1, 60, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(16, 4, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(17, 5, 1, 1, 45, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(18, 5, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(19, 5, 2, 2, 60, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(20, 5, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(21, 6, 1, 2, 45, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(22, 6, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(23, 6, 2, 1, 60, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(24, 6, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(25, 7, 1, 1, 45, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(26, 7, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(27, 7, 2, 2, 60, 0, false, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(28, 7, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(29, 8, 1, 2, 45, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(30, 8, 1, 1, 90, 5, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(31, 8, 2, 1, 60, 0, false, true, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO goal VALUES(32, 8, 2, 2, 90, 0, true, false, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO card(
--     id, match_id, team_id, player_id, "time", added_time, card_type, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO card VALUES(1, 1, 1, 1, 45, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(2, 2, 2, 2, 60, 0, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(3, 3, 1, 1, 90, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(4, 4, 2, 2, 45, 0, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(5, 5, 1, 1, 60, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(6, 6, 2, 2, 90, 5, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(7, 7, 1, 1, 45, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO card VALUES(8, 8, 2, 2, 60, 0, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO substitution(
--     id, match_id, team_id, player_id, player_in_id, "time", added_time, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO substitution VALUES(1, 1, 1, 1, 2, 45, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(2, 2, 2, 2, 1, 60, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(3, 3, 1, 1, 2, 90, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(4, 4, 2, 2, 1, 45, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(5, 5, 1, 1, 2, 60, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(6, 6, 2, 2, 1, 90, 5, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(7, 7, 1, 1, 2, 45, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO substitution VALUES(8, 8, 2, 2, 1, 60, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO d11_league(
--     id, season_id, name, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?);
INSERT INTO d11_league VALUES(1, 1, 'D11LeagueA', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_league VALUES(2, 2, 'D11LeagueB', '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO d11_match_week(
--     id, d11_league_id, match_week_id, match_week_number, date, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO d11_match_week VALUES(1, 1, 1, 1, CURRENT_DATE - 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match_week VALUES(2, 1, 2, 2, CURRENT_DATE - 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match_week VALUES(3, 2, 3, 1, CURRENT_DATE, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match_week VALUES(4, 2, 4, 2, CURRENT_DATE + 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO d11_match(
--     id, home_d11_team_id, away_d11_team_id, d11_match_week_id, date, home_team_goals, away_team_goals, home_team_points, away_team_points, previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO d11_match VALUES(1, 1, 2, 1, '2020-01-01', 0, 1, 0, 1, 0, 0, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(2, 2, 1, 1, '2020-01-02', 2, 1, 5, 1, 0, 0, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(3, 1, 2, 2, '2020-02-01', 0, 1, 0, 1, 0, 0, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(4, 2, 1, 2, '2020-02-02', 2, 1, 5, 1, 0, 0, 0, 0, 'FT', 2, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(5, 1, 2, 3, '2021-01-01', 0, 1, 0, 1, 0, 0, 0, 0, 'HT', 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(6, 2, 1, 3, '2021-01-02', 2, 1, 5, 1, 0, 0, 0, 0, '55', 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(7, 1, 2, 4, '2020-02-01', 0, 1, 0, 1, 0, 0, 0, 0, 'N/A', 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_match VALUES(8, 2, 1, 4, '2020-02-02', 2, 1, 5, 1, 0, 0, 0, 0, 'N/A', 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO player_match_stat(
--     id, player_id, match_id, d11_match_id, team_id, d11_team_id, position_id, played_position, lineup, substitution_on_time, substitution_off_time, goals, goal_assists, own_goals, goals_conceded, yellow_card_time, red_card_time, man_of_the_match, shared_man_of_the_match, rating, points, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO player_match_stat VALUES (1, 1, 1, 1, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 700, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (2, 2, 1, 1, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 861, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (3, 1, 2, 2, 1, 1, 2, 'ND', 1, 23, 0, 0, 0, 0, 1, 10, 0, false, false, 701, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (4, 2, 2, 2, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 1, 0, 0, 0, true, false, 862, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (5, 1, 3, 3, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 702, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (6, 2, 3, 3, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 863, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (7, 1, 4, 4, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 703, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (8, 2, 4, 4, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 864, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (9, 1, 5, 5, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 704, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (10, 2, 5, 5, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 865, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (11, 1, 6, 6, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 705, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (12, 2, 6, 6, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 866, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (13, 1, 7, 7, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 706, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (14, 2, 7, 7, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 867, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (15, 1, 8, 8, 1, 1, 2, 'ND', 2, 0, 0, 0, 0, 0, 1, 10, 0, false, false, 707, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (16, 2, 8, 8, 2, 2, 1, 'D', 2, 0, 80, 1, 1, 0, 0, 0, 0, true, false, 868, 12, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (17, 3, 1, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (18, 3, 2, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (19, 3, 3, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (20, 3, 4, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (21, 3, 5, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (22, 3, 6, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (23, 3, 7, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_match_stat VALUES (24, 3, 8, 1, 1, 1, 2, 'ND', 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO player_season_stat(
--     id, player_id, season_id, team_id, d11_team_id, position_id, value, ranking, points, form_points, points_per_appearance, goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, substitutions_on, substitutions_off, man_of_the_match, shared_man_of_the_match, rating, games_started, games_substitute, games_did_not_participate, minutes_played, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO player_season_stat VALUES (1, 1, 1, 1, 1, 1, 50, 1, 100, 50, 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_season_stat VALUES (2, 2, 1, 2, 2, 2, 50, 2, 50, 25, 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 732, 1, 1, 0, 90, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_season_stat VALUES (3, 1, 2, 1, 1, 1, 50, 2, 50, 50, 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 632, 1, 1, 0, 90, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO player_season_stat VALUES (4, 2, 2, 2, 2, 2, 50, 1, 55, 25, 10, 2, 3, 1, 2, 1, 1, 2, 1, 2, 1, 2, 832, 1, 1, 0, 90, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO team_table_stat(
--     id, team_id, premier_league_id, match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking, home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking, away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO team_table_stat VALUES (1, 1, 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (2, 2, 1, 1, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (3, 1, 1, 2, 2, 1, 0, 1, 3, 3, 0, 3, 3, '3,0', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (4, 2, 1, 2, 1, 1, 0, 1, 3, 3, 0, 3, 3, '0,3', 2, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (5, 1, 2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (6, 2, 2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (7, 1, 2, 4, 2, 1, 1, 0, 2, 0, 2, 4, 4, '1,3', 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO team_table_stat VALUES (8, 2, 2, 4, 2, 0, 1, 1, 0, 2, -2, 1, 1, '1,0', 2, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');

-- INSERT INTO d11_team_table_stat(
--     id, d11_team_id, d11_league_id, d11_match_week_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against, goal_difference, points, form_points, form_match_points, ranking, previous_ranking, home_matches_played, home_matches_won, home_matches_drawn, home_matches_lost, home_goals_for, home_goals_against, home_goal_difference, home_points, home_ranking, away_matches_played, away_matches_won, away_matches_drawn, away_matches_lost, away_goals_for, away_goals_against, away_goal_difference, away_points, away_ranking, created_at, updated_at)
--     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO d11_team_table_stat VALUES (1, 1, 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 3, '3', 1, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (2, 2, 1, 1, 1, 0, 0, 1, 1, 2, -1, 0, 0, '0', 2, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (3, 1, 1, 2, 2, 1, 0, 1, 3, 3, 0, 3, 3, '3,0', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (4, 2, 1, 2, 1, 1, 0, 1, 3, 3, 0, 3, 3, '0,3', 2, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (5, 1, 2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 2, 2, 1, 1, 0, 0, 2, 1, 1, 3, 1,  0, 0, 0, 0, 0, 0, 0, 0, 0, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (6, 2, 2, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, '1', 1, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (7, 1, 2, 4, 2, 1, 1, 0, 2, 0, 2, 4, 4, '1,3', 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
INSERT INTO d11_team_table_stat VALUES (8, 2, 2, 4, 2, 0, 1, 1, 0, 2, -2, 1, 1, '1,0', 2, 1, 1, 1, 0, 0, 2, 1, 1, 3, 1,  1, 1, 0, 0, 2, 1, 1, 3, 1, '2020-01-01 00:00:00.000000', '2020-01-01 00:00:00.000000');
