-- Update player season stat rankings for a season.
CREATE OR REPLACE PROCEDURE update_player_season_stat_ranking(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.player_season_stat pss
    SET ranking = sub_query.new_ranking
    FROM(
            SELECT pss.id,
                   ROW_NUMBER() OVER (ORDER BY points DESC, rating DESC, last_name, first_name) AS new_ranking
            FROM ${flyway:defaultSchema}.player_season_stat pss
                     JOIN ${flyway:defaultSchema}.player pl ON pss.player_id = pl.id
            WHERE pss.season_id = update_season_id
        ) sub_query WHERE sub_query.id = pss.id;
END;$$;

-- Update team match week stat rankings for a season.
CREATE OR REPLACE PROCEDURE update_team_match_week_stat_ranking(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.team_match_week_stat tmws
    SET ranking = sub_query.new_ranking
    FROM(
            SELECT tmws.id,
                   ROW_NUMBER() OVER (ORDER BY points DESC, goal_difference DESC, goals_for DESC) AS new_ranking
            FROM ${flyway:defaultSchema}.team_match_week_stat tmws
            WHERE tmws.match_week_id = update_match_week_id
        ) sub_query WHERE sub_query.id = tmws.id;
END;$$;

-- Update team match week stat previous rankings for a season.
CREATE OR REPLACE PROCEDURE ${flyway:defaultSchema}.update_team_match_week_stat_previous_ranking(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.team_match_week_stat tmws
    SET previous_ranking = sub_query.previous_ranking
    FROM(
            SELECT team_id, match_week_id, ranking AS previous_ranking, match_week_number
            FROM ${flyway:defaultSchema}.team_match_week_stat tmws
                     JOIN ${flyway:defaultSchema}.match_week mw
                          ON tmws.match_week_id = mw.id
            WHERE mw.season_id = update_season_id
        ) sub_query WHERE sub_query.match_week_number > 1
                      AND sub_query.team_id = tmws.team_id
                      AND sub_query.match_week_id = tmws.match_week_id;
END;$$;

-- Update team season stat rankings for a season.
CREATE OR REPLACE PROCEDURE update_team_season_stat_ranking(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.team_season_stat tss
    SET ranking = sub_query.new_ranking
    FROM(
            SELECT tss.id,
                   ROW_NUMBER() OVER (ORDER BY points DESC, goal_difference DESC, goals_for DESC, name) AS new_ranking
            FROM ${flyway:defaultSchema}.team_season_stat tss
                     JOIN ${flyway:defaultSchema}.team t ON tss.team_id = t.id
            WHERE tss.season_id = update_season_id
        ) sub_query WHERE sub_query.id = tss.id;
END;$$;

-- Update D11 team match week stat rankings for a season.
CREATE OR REPLACE PROCEDURE update_d11_team_match_week_stat_ranking(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.d11_team_match_week_stat d11tmws
    SET ranking = sub_query.new_ranking
    FROM(
            SELECT d11tmws.id,
                   ROW_NUMBER() OVER (ORDER BY points DESC, goals_for DESC, goals_against DESC) AS new_ranking
            FROM ${flyway:defaultSchema}.d11_team_match_week_stat d11tmws
            WHERE d11tmws.match_week_id = update_match_week_id
        ) sub_query WHERE sub_query.id = d11tmws.id;
END;$$;

-- Update D11 team match week stat previous rankings for a season.
CREATE OR REPLACE PROCEDURE ${flyway:defaultSchema}.update_d11_team_match_week_stat_previous_ranking(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.d11_team_match_week_stat d11tmws
    SET previous_ranking = sub_query.previous_ranking
    FROM(
            SELECT d11_team_id, match_week_id, ranking AS previous_ranking, match_week_number
            FROM ${flyway:defaultSchema}.d11_team_match_week_stat d11tmws
                     JOIN ${flyway:defaultSchema}.match_week mw
                          ON d11tmws.match_week_id = mw.id
            WHERE mw.season_id = update_season_id
        ) sub_query WHERE sub_query.match_week_number > 1
                      AND sub_query.d11_team_id = d11tmws.d11_team_id
                      AND sub_query.match_week_id = d11tmws.match_week_id;
END;$$;

-- Update team season stat rankings for a season.
CREATE OR REPLACE PROCEDURE update_d11_team_season_stat_ranking(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE ${flyway:defaultSchema}.d11_team_season_stat d11tss
    SET ranking = sub_query.new_ranking
    FROM(
            SELECT d11tss.id,
                   ROW_NUMBER() OVER (ORDER BY points DESC, goals_for DESC, goals_against DESC, name) AS new_ranking
            FROM ${flyway:defaultSchema}.d11_team_season_stat d11tss
                     JOIN ${flyway:defaultSchema}.d11_team d11t ON d11tss.d11_team_id = d11t.id
            WHERE d11tss.season_id = update_season_id
        ) sub_query WHERE sub_query.id = d11tss.id;
END;$$;
