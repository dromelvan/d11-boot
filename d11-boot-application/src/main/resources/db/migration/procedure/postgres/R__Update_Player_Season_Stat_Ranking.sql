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
