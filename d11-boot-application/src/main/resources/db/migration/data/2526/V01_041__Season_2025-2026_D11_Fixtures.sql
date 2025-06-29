insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.d11_match (home_d11_team_id, away_d11_team_id, match_week_id, datetime, home_team_goals, away_team_goals, home_team_points, away_team_points,
                                               previous_home_team_goals, previous_away_team_goals, previous_home_team_points, previous_away_team_points, elapsed, status, created_at, updated_at)
values((select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select d11_team_id from ${flyway:defaultSchema}.d11_team_season_stat where ranking = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select id from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
       (select((select date from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season))::timestamp)),
       0, 0, 0, 0, 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
