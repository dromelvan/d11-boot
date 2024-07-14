-- Season --------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.season (name, d11_team_budget, max_transfers, status, date, legacy, created_at, updated_at)
values ('2024-2025', 600, 13, 0, '2024-08-15', false, now()::timestamp, now()::timestamp);

-- Leagues -------------------------------------------------------------------------------------------------------------

insert into ${flyway:defaultSchema}.premier_league (season_id, name, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), 'Barclays Premier League', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.d11_league (season_id, name, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), 'Drömelvan', now()::timestamp, now()::timestamp);

-- Teams, D11 teams, stadia --------------------------------------------------------------------------------------------

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
values (13, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.team_season_stat (team_id, season_id, form_match_points, created_at, updated_at)
values (11, (select max(id) from ${flyway:defaultSchema}.season), '', now()::timestamp, now()::timestamp);
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

-- D11 team season stats ------------------------------------------------------------------------------------------------

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
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 1, '2024-08-16', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 2, '2024-08-24', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 3, '2024-08-31', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 4, '2024-09-14', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 5, '2024-09-21', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 6, '2024-09-28', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 7, '2024-10-05', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 8, '2024-10-19', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 9, '2024-10-26', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 10, '2024-11-02', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 11, '2024-11-09', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 12, '2024-11-23', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 13, '2024-11-30', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 14, '2024-12-03', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 15, '2024-12-07', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 16, '2024-12-14', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 17, '2024-12-21', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 18, '2024-12-26', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 19, '2024-12-29', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 20, '2025-01-04', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 21, '2025-01-14', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 22, '2025-01-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 23, '2025-01-25', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 24, '2025-02-01', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 25, '2025-02-15', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 26, '2025-02-22', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 27, '2025-02-25', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 28, '2025-03-08', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 29, '2025-03-15', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 30, '2025-04-01', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 31, '2025-04-05', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 32, '2025-04-12', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 33, '2025-04-19', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 34, '2025-04-26', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 35, '2025-05-03', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 36, '2025-05-10', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 37, '2025-05-18', 0, 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match_week (season_id, premier_league_leader_id, d11_league_leader_id, most_valuable_player_id, match_week_number, date, elapsed, status, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.season), null, null, null, 38, '2025-05-25', 0, 0, now()::timestamp, now()::timestamp);

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
        1, true, 0, '2024-08-15 18:00:00', now()::timestamp, now()::timestamp);

insert into ${flyway:defaultSchema}.transfer_day (transfer_window_id, transfer_day_number, status, datetime, created_at, updated_at)
values ((select max(id) from ${flyway:defaultSchema}.transfer_window), 1, 0, '2024-08-15 18:00:00', now()::timestamp, now()::timestamp);
