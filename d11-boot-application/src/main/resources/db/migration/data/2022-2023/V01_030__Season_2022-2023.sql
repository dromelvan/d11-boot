-- Season --------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.season (name, d11_team_budget, max_transfers, status, date, legacy, created_at, updated_at)
    values ('2022-2023', 600, 8, 0, '2022-08-05', false, now()::timestamp, now()::timestamp);

-- Leagues -------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.premier_league (season_id, name, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), 'Barclays Premier League', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.d11_league (season_id, name, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), 'Drömelvan', now()::timestamp, now()::timestamp);

-- Teams, D11 teams, stadia --------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.stadium (name, city, capacity, opened, photo_file_name, created_at, updated_at)
    values('City Ground', 'Nottingham', 30445, 1898, (select concat((select (select max(id) from ${flyway:defaultSchema}.stadium) + 1), '.png')), now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, nickname, established, motto, colour, dummy, photo_file_name, url, created_at, updated_at)
    values((select max(id) from ${flyway:defaultSchema}.stadium), 174, 15, 'Nottingham Forest FC', 'Forest', 'NOT', 'The Tricky Trees', 1865, 'Vivit Post Funera Virtus', '#000000', false, (select concat((select (select max(id) from ${flyway:defaultSchema}.team) + 1), '.png')),'https://www.nottinghamforest.co.uk/', now()::timestamp, now()::timestamp );

insert into ${flyway:defaultSchema}.d11_team (owner_id, co_owner_id, name, short_name, code, dummy, photo_file_name, created_at, updated_at)
    values (null, null, 'Berndtsson/Bolin', 'Berndt/Bolin', 'B/B', false, null, now()::timestamp, now()::timestamp);

-- Team season stats ---------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (41, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (2, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (3, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (29, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (43, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (7, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (28, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (9, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (10, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (12, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (13, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (14, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (24, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (15, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (17, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (44, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (18, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (20, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (21, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
    values (26, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);

-- D1 team season stats ------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (2, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (53, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (4, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (35, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (33, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (31, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (48, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (29, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (43, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (12, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (52, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (13, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (41, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (30, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (34, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (24, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (32, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (16, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (7, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
    values (50, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);

-- Match weeks ---------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 1, '2022-08-05', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 2, '2022-08-13', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 3, '2022-08-20', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 4, '2022-08-27', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 5, '2022-08-30', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 6, '2022-09-03', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 7, '2022-09-10', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 8, '2022-09-16', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 9, '2022-10-01', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 10, '2022-10-08', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 11, '2022-10-15', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 12, '2022-10-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 13, '2022-10-22', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 14, '2022-10-29', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 15, '2022-11-05', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 16, '2022-11-12', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 17, '2022-12-26', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 18, '2022-12-31', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 19, '2023-01-02', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 20, '2023-01-14', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 21, '2023-01-21', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 22, '2023-02-04', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 23, '2023-02-11', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 24, '2023-02-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 25, '2023-02-25', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 26, '2023-03-04', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 27, '2023-03-11', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 28, '2023-03-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 29, '2023-04-01', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 30, '2023-04-08', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 31, '2023-04-15', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 32, '2023-04-22', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 33, '2023-04-25', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 34, '2023-04-29', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 35, '2023-05-06', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 36, '2023-05-13', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 37, '2023-05-20', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 38, '2023-05-28', 0, 0, now()::timestamp, now()::timestamp);

-- Team match week stats -----------------------------------------------------------------------------------------------

do $$
    declare
        match_week record;
        team_season_stat record;
    begin
        for match_week in
            select * from ${flyway:defaultSchema}.match_week
            where season_id = (select max(id) from ${flyway:defaultSchema}.season)
            order by match_week_number
            loop
                for team_season_stat in
                    select * from ${flyway:defaultSchema}.team_season_stat
                    where season_id = (select max(id) from ${flyway:defaultSchema}.season)
                    loop
                        insert into ${flyway:defaultSchema}.team_match_week_stat (team_id, match_week_id, form_match_points, created_at, updated_at)
                            values (team_season_stat.team_id, match_week.id, '', now()::timestamp, now()::timestamp);
                    end loop;
            end loop;
    end;
$$;

-- D11 team match week stats -------------------------------------------------------------------------------------------

do $$
    declare
        match_week record;
        d11_team_season_stat record;
    begin
        for match_week in
            select * from ${flyway:defaultSchema}.match_week
            where season_id = (select max(id) from ${flyway:defaultSchema}.season)
            order by match_week_number
            loop
                for d11_team_season_stat in
                    select * from ${flyway:defaultSchema}.d11_team_season_stat
                    where season_id = (select max(id) from ${flyway:defaultSchema}.season)
                    loop
                        insert into ${flyway:defaultSchema}.d11_team_match_week_stat (d11_team_id, match_week_id, form_match_points, created_at, updated_at)
                        values (d11_team_season_stat.d11_team_id, match_week.id, '', now()::timestamp, now()::timestamp);
                    end loop;
            end loop;
    end;
$$;

-- Update stats/rankings -----------------------------------------------------------------------------------------------

do $$
    declare
        current_season_id integer := (select max(id) from ${flyway:defaultSchema}.season);
        first_match_week_id integer := (select max(id) from ${flyway:defaultSchema}.match_week) - 37;
    begin
        call ${flyway:defaultSchema}.update_stats_by_season_id(current_season_id);
        call ${flyway:defaultSchema}.update_previous_rankings_by_match_week_id(first_match_week_id);
    end;
$$;

-- Transfer window -----------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.transfer_window (match_week_id, transfer_window_number, draft, status, datetime, created_at, updated_at)
    values ((select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
            1, true, 0, '2022-08-04 18:00:00', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.transfer_day (transfer_window_id, transfer_day_number, status, datetime, created_at, updated_at)
    values ((select max(id) from ${flyway:defaultSchema}.transfer_window), 1, 0, '2022-08-04 18:00:00', now()::timestamp, now()::timestamp);
