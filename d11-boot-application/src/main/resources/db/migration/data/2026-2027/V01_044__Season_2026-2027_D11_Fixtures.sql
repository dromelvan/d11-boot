insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(41, 50,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(4, 24,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(29, 38,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(7, 16,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(37, 48,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(34, 33,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(31, 43,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(35, 52,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(32, 13,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(12, 2,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(16, 29,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(43, 12,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(50, 4,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(48, 32,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(52, 34,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(24, 35,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(13, 7,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(38, 41,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(2, 37,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values(33, 31,
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);