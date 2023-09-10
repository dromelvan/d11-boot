CREATE OR REPLACE PROCEDURE update_previous_rankings_by_match_week_id(
    update_match_week_id BIGINT
)
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE team_match_week_stat tmws
    SET previous_ranking = ranking
    WHERE tmws.match_week_id = update_match_week_id;

    UPDATE team_season_stat tss
    SET previous_ranking = ranking
    FROM(
        SELECT tss.id, mw.id match_week_id
        FROM team_season_stat tss
        JOIN match_week mw ON tss.season_id = mw.season_id
    ) sub_query
    WHERE tss.id = sub_query.id
      AND sub_query.match_week_id = update_match_week_id;

    UPDATE d11_team_match_week_stat d11tmws
    SET previous_ranking = ranking
    WHERE d11tmws.match_week_id = update_match_week_id;

    UPDATE d11_team_season_stat d11tss
    SET previous_ranking = ranking
    FROM(
        SELECT d11tss.id, mw.id match_week_id
        FROM d11_team_season_stat d11tss
        JOIN match_week mw ON d11tss.season_id = mw.season_id
    ) sub_query
    WHERE d11tss.id = sub_query.id
      AND sub_query.match_week_id = update_match_week_id;

END;$$;
