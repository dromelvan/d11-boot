insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813374, '2025-08-15 21:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813375, '2025-08-16 13:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813376, '2025-08-16 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813378, '2025-08-16 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813379, '2025-08-16 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813380, '2025-08-16 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813381, '2025-08-17 15:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813377, '2025-08-17 15:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813382, '2025-08-17 17:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813383, '2025-08-18 21:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813385, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813386, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813387, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813388, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813389, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813390, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813391, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813392, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813393, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813394, '2025-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813395, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813396, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813397, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813398, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813399, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813400, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813401, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813402, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813403, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813404, '2025-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813405, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813406, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813407, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813408, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813409, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813410, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813411, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813412, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813413, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813414, '2025-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813415, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813416, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813417, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813418, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813419, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813420, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813421, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813422, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813423, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813424, '2025-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813425, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813426, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813427, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813428, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813429, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813430, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813431, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813432, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813433, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813434, '2025-09-27 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813435, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813436, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813437, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813438, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813439, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813440, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813441, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813442, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813443, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813444, '2025-10-04 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813445, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813446, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813447, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813448, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813449, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813450, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813451, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813452, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813453, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813454, '2025-10-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813455, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813456, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813457, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813458, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813459, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813460, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813461, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813462, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813463, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813464, '2025-10-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813465, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813466, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813467, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813468, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813469, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813470, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813471, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813472, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813473, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813474, '2025-11-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813475, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813476, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813477, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813478, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813479, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813480, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813481, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813482, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813483, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813484, '2025-11-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813485, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813486, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813487, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813488, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813489, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813490, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813491, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813492, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813493, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813494, '2025-11-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813495, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813496, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813497, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813498, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813499, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813500, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813501, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813502, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813503, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813504, '2025-11-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813505, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813506, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813507, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813508, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813509, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813510, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813511, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813512, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813513, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813514, '2025-12-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813515, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813516, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813517, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813518, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813519, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813520, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813521, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813522, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813523, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813524, '2025-12-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813525, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813526, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813527, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813528, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813529, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813530, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813531, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813532, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813533, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813534, '2025-12-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813535, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813536, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813537, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813538, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813539, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813540, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813541, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813542, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813543, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813544, '2025-12-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813545, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813546, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813547, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813548, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813549, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813550, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813551, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813552, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813553, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813554, '2025-12-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813555, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813556, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813557, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813558, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813559, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813560, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813561, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813562, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813563, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813564, '2025-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813565, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813566, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813567, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813568, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813569, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813570, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813571, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813572, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813573, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813574, '2026-01-03 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813575, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813576, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813577, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813578, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813579, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813580, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813581, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813582, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813583, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813584, '2026-01-07 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813585, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813586, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813587, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813588, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813589, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813590, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813591, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813592, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813593, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813594, '2026-01-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813595, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813596, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813597, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813598, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813599, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813600, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813601, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813602, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813603, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813604, '2026-01-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813605, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813606, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813607, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813608, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813609, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813610, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813611, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813612, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813613, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813614, '2026-01-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813615, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813616, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813617, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813618, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813619, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813620, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813621, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813622, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813623, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813624, '2026-02-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813625, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813626, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813627, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813628, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813629, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813630, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813631, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813632, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813633, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813634, '2026-02-11 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813635, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813636, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813637, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813638, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813639, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813640, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813641, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813642, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813643, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813644, '2026-02-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813645, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813646, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813647, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813648, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813649, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813650, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813651, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813652, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813653, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813654, '2026-02-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813655, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813656, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813657, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813658, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813659, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813660, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813661, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813662, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813663, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813664, '2026-03-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813665, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813666, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813667, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813668, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813669, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813670, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813671, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813672, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813673, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813674, '2026-03-14 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813675, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813676, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813677, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813678, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813679, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813680, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813681, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813682, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813683, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813684, '2026-03-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813685, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813686, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813687, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813688, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813689, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813690, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813691, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813692, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813693, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813694, '2026-04-11 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813695, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813696, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813697, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813698, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813699, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813700, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813701, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813702, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813703, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813704, '2026-04-18 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813705, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813706, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813707, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813708, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813709, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813710, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813711, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813712, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813713, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813714, '2026-04-25 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813715, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813716, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813717, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813718, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813719, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813720, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813721, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813722, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813723, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813724, '2026-05-02 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813725, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813726, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813727, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813728, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813729, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813730, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813731, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813732, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813733, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813734, '2026-05-09 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (41, 24,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 41),
        4813735, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (2, 36,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 2),
        4813736, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (3, 14,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 3),
        4813737, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (29, 28,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 29),
        4813738, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (7, 20,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 7),
        4813739, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (9, 19,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 9),
        4813740, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (12, 43,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 12),
        4813741, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (15, 44,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 15),
        4813742, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (17, 21,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 17),
        4813743, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (26, 10,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 26),
        4813744, '2026-05-17 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (43, 15,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 43),
        4813745, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (36, 26,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 36),
        4813746, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (28, 2,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 28),
        4813747, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (10, 17,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 10),
        4813748, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (14, 29,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 14),
        4813749, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (24, 3,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 24),
        4813750, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (44, 41,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 44),
        4813751, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (19, 7,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 19),
        4813752, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (20, 9,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 20),
        4813753, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
values (21, 12,
        (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
        (select (stadium_id) from ${flyway:defaultSchema}.team where id = 21),
        4813754, '2026-05-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
