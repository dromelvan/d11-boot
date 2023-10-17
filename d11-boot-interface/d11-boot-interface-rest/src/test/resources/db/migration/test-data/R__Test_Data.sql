-- User
INSERT INTO application_user (name, email, encrypted_password, administrator)
    VALUES('User', 'user@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', false);
INSERT INTO application_user (name, email, encrypted_password, administrator)
    VALUES('Administrator', 'administrator@email.com', '$2a$12$MgLkW.T6F0iqYRbdwHL9LOGxmt2UtI5hgVr.DA7qho7iuMVFdtQha', true);

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

-- Match day
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(1, null, null, null, 1, CURRENT_DATE - 2, 10, 3);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 1, CURRENT_DATE - 1, 0, 0);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 2, CURRENT_DATE, 7, 1);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 3, CURRENT_DATE + 1, 0, 0);
INSERT INTO match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status)
VALUES(2, null, null, null, 4, CURRENT_DATE + 2, 0, 0);

-- Transfer Window
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(1, 0, true, 3, CURRENT_DATE - 2);
INSERT INTO transfer_window (match_week_id, transfer_window_number, draft, status, datetime)
VALUES(2, 0, true, 0, CURRENT_DATE - 1);
