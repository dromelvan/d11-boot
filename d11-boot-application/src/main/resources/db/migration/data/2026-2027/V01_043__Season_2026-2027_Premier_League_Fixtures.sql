insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795363, '2026-08-21 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795364, '2026-08-22 14:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795365, '2026-08-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795366, '2026-08-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795367, '2026-08-22 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795368, '2026-08-22 19:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795369, '2026-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795370, '2026-08-23 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795371, '2026-08-23 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 1 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795372, '2026-08-24 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795429, '2026-08-28 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795431, '2026-08-29 14:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795425, '2026-08-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795428, '2026-08-29 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795434, '2026-08-29 19:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795427, '2026-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795430, '2026-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795433, '2026-08-30 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795432, '2026-08-30 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 2 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795426, '2026-08-31 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795441, '2026-09-04 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795443, '2026-09-05 14:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795436, '2026-09-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795437, '2026-09-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795439, '2026-09-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795442, '2026-09-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795444, '2026-09-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795440, '2026-09-05 19:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795438, '2026-09-06 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 3 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795435, '2026-09-06 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795445, '2026-09-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795446, '2026-09-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795447, '2026-09-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795449, '2026-09-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795451, '2026-09-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795454, '2026-09-12 19:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795453, '2026-09-12 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795448, '2026-09-13 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795452, '2026-09-13 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 4 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795450, '2026-09-14 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795456, '2026-09-18 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795464, '2026-09-19 14:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795457, '2026-09-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795458, '2026-09-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795460, '2026-09-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795461, '2026-09-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795462, '2026-09-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795463, '2026-09-19 19:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795455, '2026-09-20 16:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 5 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795459, '2026-09-20 18:30:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795465, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795466, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795467, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795468, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795469, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795470, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795471, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795472, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795473, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 6 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795474, '2026-10-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795475, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795476, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795477, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795478, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795479, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795480, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795481, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795482, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795483, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 7 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795484, '2026-10-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795485, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795486, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795487, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795488, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795489, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795490, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795491, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795492, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795493, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 8 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795494, '2026-10-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795495, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795496, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795497, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795498, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795499, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795500, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795501, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795502, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795503, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 9 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795504, '2026-10-31 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795505, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795506, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795507, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795508, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795509, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795510, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795511, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795512, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795513, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 10 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795514, '2026-11-07 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795515, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795516, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795517, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795518, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795519, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795520, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795521, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795522, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795523, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 11 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795524, '2026-11-21 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795525, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795526, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795527, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795528, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795529, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795530, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795531, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795532, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795533, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 12 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795534, '2026-11-28 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795535, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795536, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795537, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795538, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795539, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795540, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795541, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795542, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795543, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 13 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795544, '2026-12-02 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795545, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795546, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795547, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795548, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795549, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795550, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795551, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795552, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795553, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 14 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795554, '2026-12-05 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795555, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795556, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795557, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795558, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795559, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795560, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795561, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795562, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795563, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 15 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795564, '2026-12-12 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795565, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795566, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795567, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795568, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795569, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795570, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795571, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795572, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795573, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 16 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795574, '2026-12-19 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795575, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795576, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795577, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795578, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795579, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795580, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795581, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795582, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795583, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 17 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795584, '2026-12-26 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795585, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795586, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795587, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795588, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795589, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795590, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795591, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795592, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795593, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 18 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795594, '2026-12-30 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795595, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795596, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795597, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795598, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795599, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795600, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795601, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795602, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795603, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 19 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795604, '2027-01-02 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795605, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795606, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795607, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795608, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795609, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795610, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795611, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795612, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795613, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 20 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795614, '2027-01-06 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795615, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795616, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795617, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795618, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795619, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795620, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795621, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795622, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795623, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 21 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795624, '2027-01-16 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795625, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795626, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795627, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795628, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795629, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795630, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795631, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795632, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795633, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 22 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795634, '2027-01-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795635, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795636, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795637, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795638, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795639, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795640, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795641, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795642, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795643, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 23 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795644, '2027-01-30 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795645, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795646, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795647, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795648, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795649, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795650, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795651, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795652, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795653, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 24 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795654, '2027-02-06 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795655, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795656, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795657, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795658, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795659, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795660, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795661, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795662, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795663, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 25 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795664, '2027-02-10 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795665, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795666, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795667, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795668, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795669, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795670, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795671, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795672, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795673, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 26 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795674, '2027-02-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795675, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795676, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795677, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795678, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795679, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795680, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795681, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795682, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795683, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 27 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795684, '2027-02-27 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795685, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795686, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795687, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795688, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795689, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795690, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795691, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795692, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795693, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 28 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795694, '2027-03-03 22:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795695, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795696, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795697, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795698, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795699, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795700, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795701, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795702, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795703, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 29 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795704, '2027-03-13 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795705, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795706, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795707, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795708, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795709, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795710, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795711, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795712, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795713, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 30 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795714, '2027-03-20 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795715, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795716, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795717, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795718, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795719, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795720, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795721, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795722, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795723, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 31 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795724, '2027-04-10 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795725, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795726, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795727, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795728, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795729, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795730, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795731, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795732, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795733, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 32 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795734, '2027-04-17 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795735, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795736, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795737, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795738, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795739, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795740, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795741, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795742, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795743, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 33 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795744, '2027-04-24 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795745, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795746, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795747, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795748, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795749, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795750, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795751, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795752, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795753, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 34 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795754, '2027-05-01 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795755, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795756, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795757, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795771, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795772, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795773, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795774, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795775, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795776, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 35 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795777, '2027-05-08 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795778, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795779, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795780, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795781, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795782, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795783, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795784, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795785, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795786, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 36 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795787, '2027-05-15 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678)),
                5795788, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937)),
                5795789, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204)),
                5795790, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668)),
                5795791, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879)),
                5795792, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463)),
                5795793, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456)),
                5795794, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261)),
                5795795, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203)),
                5795796, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 37 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586)),
                5795797, '2027-05-23 17:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10204),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9825)),
                5795798, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8586),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10252)),
                5795799, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9937),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8455)),
                5795800, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10203),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8669)),
                5795801, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8463),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9826)),
                5795802, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10261),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8667)),
                5795803, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8668),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9902)),
                5795804, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8678),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8650)),
                5795805, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 9879),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 10260)),
                5795806, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
insert into ${flyway:defaultSchema}.match (home_team_id, away_team_id, match_week_id, stadium_id, whoscored_id, datetime, home_team_goals, away_team_goals, previous_home_team_goals, previous_away_team_goals, elapsed, status, created_at, updated_at)
        values ((select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472), (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8456),
                (select (id) from ${flyway:defaultSchema}.match_week where match_week_number = 38 and season_id = (select max(id) from ${flyway:defaultSchema}.season)),
                (select (stadium_id) from ${flyway:defaultSchema}.team where id = (select (id) from ${flyway:defaultSchema}.team where whoscored_id = 8472)),
                5795807, '2027-05-30 18:00:00', 0, 0, 0, 0, 'N/A', 0, now()::timestamp, now()::timestamp);
