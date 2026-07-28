-- Season --------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.season (name, d11_team_budget, max_transfers, status, date, legacy, created_at, updated_at)
values ('2026-2027', 600, 13, 0, '2026-08-21', false, now()::timestamp, now()::timestamp);

-- Leagues -------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.premier_league (season_id, name, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), 'Premier League', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.d11_league (season_id, name, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), 'Drömelvan', now()::timestamp, now()::timestamp);

-- Teams, D11 teams, stadia --------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.stadium (name, city, capacity, opened, photo_file_name, created_at, updated_at)
values ('Coventry Building Society Arena', 'Coventry', 32609, 2005, '49.png', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.team (stadium_id, whoscored_id, premier_league_id, name, short_name, code, nickname, established, motto, colour, dummy, photo_file_name, url, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.stadium), 8669, 9, 'Coventry City', 'Coventry', 'COV', 'The Sky Blues', 1883, 'Camera Principis', '#009edb', false, '46.png', '', now()::timestamp, now()::timestamp);

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
values (19, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
values (20, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
values (11, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
values (34, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.team), (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);

-- D11 team season stats ------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
values (2, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_team_season_stat (d11_team_id, season_id, form_match_points, created_at, updated_at)
values (37, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
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
values (38, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
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
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 1, '2026-08-21', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 2, '2026-08-28', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 3, '2026-09-04', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 4, '2026-09-12', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 5, '2026-09-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 6, '2026-10-10', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 7, '2026-10-17', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 8, '2026-10-24', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 9, '2026-10-31', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 10, '2026-11-07', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 11, '2026-11-21', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 12, '2026-11-28', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 13, '2026-12-02', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 14, '2026-12-05', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 15, '2026-12-12', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 16, '2026-12-19', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 17, '2026-12-26', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 18, '2026-12-30', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 19, '2027-01-02', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 20, '2027-01-06', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 21, '2027-01-16', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 22, '2027-01-23', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 23, '2027-01-30', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 24, '2027-02-06', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 25, '2027-02-10', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 26, '2027-02-20', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 27, '2027-02-27', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 28, '2027-03-03', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 29, '2027-03-13', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 30, '2027-03-20', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 31, '2027-04-10', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 32, '2027-04-17', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 33, '2027-04-24', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 34, '2027-05-01', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 35, '2027-05-08', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 36, '2027-05-15', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 37, '2027-05-23', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 38, '2027-05-30', 0, 0, now()::timestamp, now()::timestamp);

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
        1, true, 0, '2026-08-20 18:00:00', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.transfer_day (transfer_window_id, transfer_day_number, status, datetime, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.transfer_window), 1, 0, '2026-08-20 18:00:00', now()::timestamp, now()::timestamp);
