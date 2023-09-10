-- Create a view for player form match points --------------------------------------------------------------------------
CREATE OR REPLACE VIEW player_form_match_points AS
    SELECT player_id, season_id, SUM(points) form_points, ARRAY_TO_STRING(ARRAY_AGG(points),',') form_match_points FROM(
        SELECT season_id, player_id, points FROM(
            SELECT pms.player_id, mw.season_id, pms.points,
                   ROW_NUMBER() OVER (PARTITION BY player_id, season_id ORDER BY m.datetime DESC) AS index
            FROM player_match_stat pms
            JOIN match m ON pms.match_id = m.id
            JOIN match_week mw ON m.match_week_id = mw.id
        ) index_query
        WHERE index <= 5
        ORDER BY player_id, season_id DESC, index DESC
    ) sub_query
    GROUP BY player_id, season_id;

-- Create a view for team match stats ----------------------------------------------------------------------------------
CREATE OR REPLACE VIEW team_match_stats AS
SELECT *
FROM(
    SELECT id, team_id, match_week_id, season_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against,
           goals_for - goals_against goal_difference,
           CASE WHEN matches_played = 0 THEN 0 WHEN goals_for > goals_against THEN 3 WHEN goals_for = goals_against THEN 1 ELSE 0 END points
    FROM(
        SELECT m.id,
               m.home_team_id team_id,
               m.match_week_id,
               mw.season_id,
               CASE WHEN m.status IN (1,2,3) THEN 1 ELSE 0 END matches_played,
               CASE WHEN m.status IN (1,2,3) AND m.home_team_goals > m.away_team_goals THEN 1 ELSE 0 END matches_won,
               CASE WHEN m.status IN (1,2,3) AND m.home_team_goals = m.away_team_goals THEN 1 ELSE 0 END matches_drawn,
               CASE WHEN m.status IN (1,2,3) AND m.home_team_goals < m.away_team_goals THEN 1 ELSE 0 END matches_lost,
               m.home_team_goals goals_for,
               m.away_team_goals goals_against
        FROM match m
        JOIN match_week mw ON m.match_week_id = mw.id
    ) base_query
) sub_query
UNION
SELECT *
FROM(
    SELECT id, team_id, match_week_id, season_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against,
           goals_for - goals_against goal_difference,
           CASE WHEN matches_played = 0 THEN 0 WHEN goals_for > goals_against THEN 3 WHEN goals_for = goals_against THEN 1 ELSE 0 END points
    FROM(
        SELECT m.id,
               m.away_team_id team_id,
               m.match_week_id,
               mw.season_id,
               CASE WHEN m.status IN (1,2,3) THEN 1 ELSE 0 END matches_played,
               CASE WHEN m.status IN (1,2,3) AND m.away_team_goals > m.home_team_goals THEN 1 ELSE 0 END matches_won,
               CASE WHEN m.status IN (1,2,3) AND m.away_team_goals = m.home_team_goals THEN 1 ELSE 0 END matches_drawn,
               CASE WHEN m.status IN (1,2,3) AND m.away_team_goals < m.home_team_goals THEN 1 ELSE 0 END matches_lost,
               m.away_team_goals goals_for,
               m.home_team_goals goals_against
        FROM match m
        JOIN match_week mw ON m.match_week_id = mw.id
    ) base_query
) sub_query
ORDER BY match_week_id desc, id;

-- Create a view for team form match points ----------------------------------------------------------------------------
CREATE OR REPLACE VIEW team_form_match_points AS
    SELECT team_id, season_id, SUM(points) form_points, ARRAY_TO_STRING(ARRAY_AGG(points),',') form_match_points FROM(
        SELECT season_id, team_id, points FROM(
            SELECT tms.team_id, tms.season_id, tms.points,
                   ROW_NUMBER() OVER (PARTITION BY team_id, season_id ORDER BY m.datetime DESC) AS index
            FROM team_match_stats tms
            JOIN match m ON tms.id = m.id
            WHERE m.status in (1,2,3)
        ) index_query
        WHERE index <= 5
        ORDER BY team_id, season_id DESC, index DESC
    ) sub_query
    GROUP BY team_id, season_id;

-- Create a view for D11 team match stats ------------------------------------------------------------------------------
CREATE OR REPLACE VIEW d11_team_match_stats AS
SELECT *
FROM(
    SELECT id, d11_team_id, match_week_id, season_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against,
           goals_for - goals_against goal_difference,
           CASE WHEN matches_played = 0 THEN 0 WHEN goals_for > goals_against THEN 3 WHEN goals_for = goals_against THEN 1 ELSE 0 END points
    FROM(
        SELECT d11m.id,
               d11m.home_d11_team_id d11_team_id,
               d11m.match_week_id,
               mw.season_id,
               CASE WHEN d11m.status IN (1,2,3) THEN 1 ELSE 0 END matches_played,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.home_team_goals > d11m.away_team_goals THEN 1 ELSE 0 END matches_won,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.home_team_goals = d11m.away_team_goals THEN 1 ELSE 0 END matches_drawn,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.home_team_goals < d11m.away_team_goals THEN 1 ELSE 0 END matches_lost,
               d11m.home_team_goals goals_for,
               d11m.away_team_goals goals_against
        FROM d11_match d11m
        JOIN match_week mw ON d11m.match_week_id = mw.id
    ) base_query
) sub_query
UNION
SELECT *
FROM(
    SELECT id, d11_team_id, match_week_id, season_id, matches_played, matches_won, matches_drawn, matches_lost, goals_for, goals_against,
           goals_for - goals_against goal_difference,
           CASE WHEN matches_played = 0 THEN 0 WHEN goals_for > goals_against THEN 3 WHEN goals_for = goals_against THEN 1 ELSE 0 END points
    FROM(
        SELECT d11m.id,
               d11m.away_d11_team_id d11_team_id,
               d11m.match_week_id,
               mw.season_id,
               CASE WHEN d11m.status IN (1,2,3) THEN 1 ELSE 0 END matches_played,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.away_team_goals > d11m.home_team_goals THEN 1 ELSE 0 END matches_won,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.away_team_goals = d11m.home_team_goals THEN 1 ELSE 0 END matches_drawn,
               CASE WHEN d11m.status IN (1,2,3) AND d11m.away_team_goals < d11m.home_team_goals THEN 1 ELSE 0 END matches_lost,
               d11m.away_team_goals goals_for,
               d11m.home_team_goals goals_against
        FROM d11_match d11m
        JOIN match_week mw ON d11m.match_week_id = mw.id
    ) base_query
) sub_query
ORDER BY match_week_id desc, id;


-- Create a view for D11 team form match points ------------------------------------------------------------------------
CREATE OR REPLACE VIEW d11_team_form_match_points AS
    SELECT d11_team_id, season_id, SUM(points) form_points, ARRAY_TO_STRING(ARRAY_AGG(points),',') form_match_points FROM(
        SELECT d11_team_id, season_id, points FROM(
            SELECT d11tms.d11_team_id, d11tms.season_id, d11tms.points,
                   ROW_NUMBER() OVER (PARTITION BY d11_team_id, season_id ORDER BY d11m.datetime DESC) AS index
            FROM d11_team_match_stats d11tms
            JOIN d11_match d11m ON d11tms.id = d11m.id
            WHERE d11m.status in (1,2,3)
        ) index_query
        WHERE index <= 5
        ORDER BY d11_team_id, season_id DESC, index DESC
    ) sub_query
    GROUP BY d11_team_id, season_id;

-- Update stats for a season -------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE update_stats_by_season_id(
    update_season_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    -- Update player season stats
    UPDATE player_season_stat pss
    SET points = sub_query.points,
        goals = sub_query.goals,
        goal_assists = sub_query.goal_assists,
        own_goals = sub_query.own_goals,
        goals_conceded = sub_query.goals_conceded,
        clean_sheets = sub_query.clean_sheets,
        yellow_cards = sub_query.yellow_cards,
        red_cards = sub_query.red_cards,
        substitutions_on = sub_query.substitutions_on,
        substitutions_off = sub_query.substitutions_off,
        man_of_the_match = sub_query.man_of_the_match,
        shared_man_of_the_match = sub_query.shared_man_of_the_match,
        rating = sub_query.rating,
        games_started = sub_query.games_started,
        games_substitute = sub_query.games_substitute,
        games_did_not_participate = sub_query.games_did_not_participate,
        minutes_played = sub_query.minutes_played
    FROM(SELECT pms.player_id,
                mw.season_id,
                SUM(points) points,
                SUM(goals) AS goals,
                SUM(goal_assists) AS goal_assists,
                SUM(own_goals) AS own_goals,
                SUM(goals_conceded) AS goals_conceded,
                COUNT(goals_conceded) FILTER (WHERE goals_conceded = 0) AS clean_sheets,
                COUNT(yellow_card_time) FILTER (WHERE yellow_card_time > 0) AS yellow_cards,
                COUNT(red_card_time) FILTER (WHERE red_card_time > 0) AS red_cards,
                COUNT(substitution_on_time) FILTER (WHERE substitution_on_time > 0) AS substitutions_on,
                COUNT(substitution_off_time) FILTER (WHERE substitution_off_time > 0) AS substitutions_off,
                COUNT(man_of_the_match) FILTER (WHERE man_of_the_match = true) AS man_of_the_match,
                COUNT(shared_man_of_the_match) FILTER (WHERE shared_man_of_the_match = true) AS shared_man_of_the_match,
                COALESCE(ROUND(AVG(rating) FILTER (WHERE rating > 0)), 0) AS rating,
                COUNT(lineup) FILTER (WHERE lineup = 2) AS games_started,
                COUNT(lineup) FILTER (WHERE lineup = 1) AS games_substitute,
                COUNT(lineup) FILTER (WHERE lineup = 0) AS games_did_not_participate,
                SUM(CASE
                        WHEN lineup = 2 THEN
                            CASE
                                WHEN substitution_off_time > 0 THEN substitution_off_time
                                ELSE 90
                            END
                        WHEN lineup = 1 THEN
                            CASE
                                WHEN substitution_on_time > 0 THEN
                                    CASE
                                        WHEN substitution_off_time > 0 THEN substitution_off_time - substitution_on_time
                                        ELSE 90 - substitution_on_time
                                    END
                                ELSE 0
                            END
                        ELSE 0
                     END
                ) AS minutes_played
            FROM player_match_stat pms
            JOIN match m ON pms.match_id = m.id
            JOIN match_week mw ON m.match_week_id = mw.id
            WHERE mw.season_id = update_season_id
            GROUP BY pms.player_id, mw.season_id
        ) sub_query WHERE sub_query.player_id = pss.player_id
                      AND sub_query.season_id = pss.season_id;

    -- Update player form stats
    UPDATE player_season_stat pss
    SET form_points = pfmp.form_points,
        form_match_points = pfmp.form_match_points
    FROM (SELECT * FROM player_form_match_points WHERE season_id = update_season_id) pfmp
    WHERE pfmp.player_id = pss.player_id
      AND pfmp.season_id = pss.season_id;

    -- Update player rankings
    UPDATE player_season_stat pss
    SET ranking = sub_query.new_ranking
    FROM(
        SELECT pss.id,
               ROW_NUMBER() OVER (ORDER BY points DESC, rating DESC, last_name, first_name) AS new_ranking
        FROM player_season_stat pss
        JOIN player pl ON pss.player_id = pl.id
        WHERE pss.season_id = update_season_id
    ) sub_query WHERE sub_query.id = pss.id;

    -- Update team season stats
    UPDATE team_season_stat tss
    SET matches_played = sub_query.matches_played,
        matches_won = sub_query.matches_won,
        matches_drawn = sub_query.matches_drawn,
        matches_lost = sub_query.matches_lost,
        goals_for = sub_query.goals_for,
        goals_against = sub_query.goals_against,
        goal_difference = sub_query.goal_difference,
        points = sub_query.points
    FROM(
        SELECT team_id,
               season_id,
               COALESCE(SUM(matches_played),0) AS matches_played,
               COALESCE(SUM(matches_won),0) AS matches_won,
               COALESCE(SUM(matches_drawn),0) AS matches_drawn,
               COALESCE(SUM(matches_lost),0) AS matches_lost,
               COALESCE(SUM(goals_for),0) AS goals_for,
               COALESCE(SUM(goals_against),0) AS goals_against,
               COALESCE(SUM(goal_difference),0) AS goal_difference,
               COALESCE(SUM(points),0) AS points
        FROM team_match_stats
        WHERE season_id = update_season_id
        GROUP BY team_id, season_id
    ) sub_query WHERE sub_query.team_id = tss.team_id
            AND sub_query.season_id = tss.season_id;

    -- Update team form stats
    UPDATE team_season_stat tss
    SET form_points = tfmp.form_points,
        form_match_points = tfmp.form_match_points
    FROM (SELECT * FROM team_form_match_points WHERE season_id = update_season_id) tfmp
    WHERE tfmp.team_id = tss.team_id
      AND tfmp.season_id = tss.season_id;

    -- Update team rankings
    UPDATE team_season_stat tss
    SET ranking = sub_query.new_ranking
    FROM(
        SELECT tss.id,
               ROW_NUMBER() OVER (ORDER BY points DESC, goal_difference DESC, goals_for DESC, name) AS new_ranking
        FROM team_season_stat tss
        JOIN team t ON tss.team_id = t.id
        WHERE tss.season_id = update_season_id
    ) sub_query WHERE sub_query.id = tss.id;

    -- Update team match week stats
    UPDATE team_match_week_stat tmws
    SET matches_played = sub_query.matches_played,
        matches_won = sub_query.matches_won,
        matches_drawn = sub_query.matches_drawn,
        matches_lost = sub_query.matches_lost,
        goals_for = sub_query.goals_for,
        goals_against = sub_query.goals_against,
        goal_difference = sub_query.goal_difference,
        points = sub_query.points
    FROM(
        SELECT tmws.id team_match_week_stat_id,
               tmws.team_id,
               tmws.match_week_id,
               COALESCE(SUM(tms.matches_played),0) AS matches_played,
               COALESCE(SUM(tms.matches_won),0) AS matches_won,
               COALESCE(SUM(tms.matches_drawn),0) AS matches_drawn,
               COALESCE(SUM(tms.matches_lost),0) AS matches_lost,
               COALESCE(SUM(tms.goals_for),0) AS goals_for,
               COALESCE(SUM(tms.goals_against),0) AS goals_against,
               COALESCE(SUM(tms.goal_difference),0) AS goal_difference,
               COALESCE(SUM(tms.points),0) AS points
        FROM team_match_week_stat tmws
        JOIN team_match_stats tms ON tmws.team_id = tms.team_id AND tmws.match_week_id >= tms.match_week_id
        JOIN match_week mw ON tms.match_week_id = mw.id AND mw.season_id = update_season_id
        GROUP BY tmws.id
    ) sub_query WHERE sub_query.team_match_week_stat_id = tmws.id;

    -- Update team match week stat rankings
    UPDATE team_match_week_stat tmws
    SET ranking = sub_query.new_ranking
    FROM(
        SELECT tmws.id,
               ROW_NUMBER() OVER (PARTITION BY match_week_id ORDER BY points DESC, goal_difference DESC, goals_for DESC, name) AS new_ranking
        FROM team_match_week_stat tmws
        JOIN team t ON tmws.team_id = t.id
        JOIN match_week mw ON tmws.match_week_id = mw.id
        WHERE mw.season_id = update_season_id
    ) sub_query WHERE sub_query.id = tmws.id;

    -- Update D11 team season stats
    UPDATE d11_team_season_stat d11tss
    SET matches_played = sub_query.matches_played,
        matches_won = sub_query.matches_won,
        matches_drawn = sub_query.matches_drawn,
        matches_lost = sub_query.matches_lost,
        goals_for = sub_query.goals_for,
        goals_against = sub_query.goals_against,
        goal_difference = sub_query.goal_difference,
        points = sub_query.points
    FROM(
        SELECT d11_team_id,
               season_id,
               COALESCE(SUM(matches_played),0) AS matches_played,
               COALESCE(SUM(matches_won),0) AS matches_won,
               COALESCE(SUM(matches_drawn),0) AS matches_drawn,
               COALESCE(SUM(matches_lost),0) AS matches_lost,
               COALESCE(SUM(goals_for),0) AS goals_for,
               COALESCE(SUM(goals_against),0) AS goals_against,
               COALESCE(SUM(goal_difference),0) AS goal_difference,
               COALESCE(SUM(points),0) AS points
        FROM d11_team_match_stats
        WHERE season_id = update_season_id
        GROUP BY d11_team_id, season_id
    ) sub_query WHERE sub_query.d11_team_id = d11tss.d11_team_id
                  AND sub_query.season_id = d11tss.season_id;

    -- Update D11 team form stats
    UPDATE d11_team_season_stat d11tss
    SET form_points = d11tfmp.form_points,
        form_match_points = d11tfmp.form_match_points
    FROM (SELECT * FROM d11_team_form_match_points WHERE season_id = update_season_id) d11tfmp
    WHERE d11tfmp.d11_team_id = d11tss.d11_team_id
      AND d11tfmp.season_id = d11tss.season_id;

    -- Update D11 team rankings
    UPDATE d11_team_season_stat d11tss
    SET ranking = sub_query.new_ranking
    FROM(
        SELECT d11tss.id,
               ROW_NUMBER() OVER (ORDER BY points DESC, goals_for DESC, goals_against DESC, name) AS new_ranking
        FROM d11_team_season_stat d11tss
        JOIN d11_team d11t ON d11tss.d11_team_id = d11t.id
        WHERE d11tss.season_id = update_season_id
    ) sub_query WHERE sub_query.id = d11tss.id;

    -- Update D11 team match week stats
    UPDATE d11_team_match_week_stat d11tmws
    SET matches_played = sub_query.matches_played,
        matches_won = sub_query.matches_won,
        matches_drawn = sub_query.matches_drawn,
        matches_lost = sub_query.matches_lost,
        goals_for = sub_query.goals_for,
        goals_against = sub_query.goals_against,
        goal_difference = sub_query.goal_difference,
        points = sub_query.points
    FROM(
        SELECT d11tmws.id d11_team_match_week_stat_id,
               d11tmws.d11_team_id,
               d11tmws.match_week_id,
               COALESCE(SUM(d11tms.matches_played),0) AS matches_played,
               COALESCE(SUM(d11tms.matches_won),0) AS matches_won,
               COALESCE(SUM(d11tms.matches_drawn),0) AS matches_drawn,
               COALESCE(SUM(d11tms.matches_lost),0) AS matches_lost,
               COALESCE(SUM(d11tms.goals_for),0) AS goals_for,
               COALESCE(SUM(d11tms.goals_against),0) AS goals_against,
               COALESCE(SUM(d11tms.goal_difference),0) AS goal_difference,
               COALESCE(SUM(d11tms.points),0) AS points
        FROM d11_team_match_week_stat d11tmws
        JOIN d11_team_match_stats d11tms ON d11tmws.d11_team_id = d11tms.d11_team_id AND d11tmws.match_week_id >= d11tms.match_week_id
        JOIN match_week mw ON d11tms.match_week_id = mw.id AND mw.season_id = update_season_id
        GROUP BY d11tmws.id
    ) sub_query WHERE sub_query.d11_team_match_week_stat_id = d11tmws.id;

    -- Update D11 team match week stat rankings
    UPDATE d11_team_match_week_stat d11tmws
    SET ranking = sub_query.new_ranking
    FROM(
        SELECT d11tmws.id,
               ROW_NUMBER() OVER (PARTITION BY match_week_id ORDER BY points DESC, goals_for DESC, goals_against DESC, name) AS new_ranking
        FROM d11_team_match_week_stat d11tmws
        JOIN d11_team d11t ON d11tmws.d11_team_id = d11t.id
        JOIN match_week mw ON d11tmws.match_week_id = mw.id
        WHERE mw.season_id = update_season_id
    ) sub_query WHERE sub_query.id = d11tmws.id;

    -- Update match week premier league leader
    UPDATE match_week
    SET premier_league_leader_id = (
        SELECT team_match_week_stat_id FROM (
            SELECT match_week.id AS match_week_id, team_match_week_stat.id AS team_match_week_stat_id
            FROM match_week
            JOIN team_match_week_stat ON match_week.id = team_match_week_stat.match_week_id
            WHERE team_match_week_stat.ranking = 1
        ) premier_league_leader_query
        WHERE premier_league_leader_query.match_week_id = match_week.id
    ) WHERE match_week.season_id = update_season_id;

    UPDATE match_week
    SET d11_league_leader_id = (
        SELECT d11_team_match_week_stat_id FROM (
            SELECT match_week.id AS match_week_id, d11_team_match_week_stat.id AS d11_team_match_week_stat_id
            FROM match_week
            JOIN d11_team_match_week_stat ON match_week.id = d11_team_match_week_stat.match_week_id
            WHERE d11_team_match_week_stat.ranking = 1
        ) d11_league_leader_query
        WHERE d11_league_leader_query.match_week_id = match_week.id
    ) WHERE match_week.season_id = update_season_id;

    UPDATE match_week
    SET most_valuable_player_id = (
        SELECT player_match_stat.id
        FROM player_match_stat
        JOIN match ON player_match_stat.match_id = match.id
        WHERE match.match_week_id = match_week.id
        ORDER BY points DESC, rating DESC
        LIMIT 1
    ) WHERE match_week.season_id = update_season_id
        AND match_week.status IN (1,2,3);

END;$$;
