-- Manual script for migrating data from old production schema to new public schema
BEGIN;

DELETE FROM production.transfer_listing
WHERE id IN (
    SELECT MAX(id)
    FROM production.transfer_listing
    GROUP BY transfer_day_id, player_id
    HAVING COUNT(*) > 1
);

INSERT INTO public.application_user SELECT * FROM production.application_user;
UPDATE public.application_user set confirm_registration_token = null,reset_password_token = null;

INSERT INTO public.country SELECT * FROM production.country;
INSERT INTO public.stadium SELECT * FROM production.stadium;

INSERT INTO public.team (
    id,
    stadium_id,
    stat_source_id,
    premier_league_id,
    name,
    short_name,
    code,
    established,
    colour,
    dummy,
    photo_file_name,
    url,
    created_at,
    updated_at
)
SELECT
    id,
    stadium_id,
    whoscored_id,
    premier_league_id,
    name,
    short_name,
    code,
    established,
    LEFT(colour, 7),
    dummy,
    photo_file_name,
    url,
    created_at,
    updated_at
FROM production.team;

INSERT INTO public.d11_team SELECT * FROM production.d11_team;

INSERT INTO public.player (
    id,
    country_id,
    stat_source_id,
    premier_league_id,
    first_name,
    last_name,
    full_name,
    parameterized_name,
    date_of_birth,
    height,
    photo_file_name,
    verified,
    created_at,
    updated_at
)
SELECT
    id,
    country_id,
    whoscored_id,
    premier_league_id,
    first_name,
    last_name,
    full_name,
    parameterized_name,
    date_of_birth,
    height,
    photo_file_name,
    verified,
    created_at,
    updated_at
FROM production.player;

INSERT INTO public.position SELECT * FROM production.position;

INSERT INTO public.season (
    id,
    name,
    d11_team_budget,
    d11_team_max_transfers,
    status,
    date,
    legacy,
    created_at,
    updated_at
)
SELECT
    id,
    name,
    d11_team_budget,
    max_transfers,
    status,
    date,
    legacy,
    created_at,
    updated_at
FROM production.season;

INSERT INTO public.premier_league SELECT * FROM production.premier_league;
INSERT INTO public.d11_league SELECT * FROM production.d11_league;

INSERT INTO public.match_week (
    id,
    season_id,
    match_week_number,
    date,
    elapsed,
    status,
    created_at,
    updated_at
)
SELECT
    id,
    season_id,
    match_week_number,
    date,
    elapsed,
    status,
    created_at,
    updated_at
FROM production.match_week;

INSERT INTO public.match SELECT * FROM production.match;
INSERT INTO public.d11_match SELECT * FROM production.d11_match;
INSERT INTO public.goal SELECT * FROM production.goal;
INSERT INTO public.player_match_stat SELECT * FROM production.player_match_stat;
INSERT INTO public.player_season_stat SELECT * FROM production.player_season_stat;
INSERT INTO public.team_match_week_stat (
    id,
    team_id,
    match_week_id,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    ranking,
    previous_ranking,
    form_match_points,
    created_at,
    updated_at
)
SELECT
    id,
    team_id,
    match_week_id,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    ranking,
    previous_ranking,
    form_match_points,
    created_at,
    updated_at
FROM production.team_match_week_stat;

INSERT INTO public.team_season_stat (
    id,
    team_id,
    season_id,
    win_count,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    points_penalty,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
)
SELECT
    id,
    team_id,
    season_id,
    win_count,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    0 AS points_penalty,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
FROM production.team_season_stat;

INSERT INTO public.d11_team_match_week_stat (
    id,
    d11_team_id,
    match_week_id,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
)
SELECT
    id,
    d11_team_id,
    match_week_id,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
FROM production.d11_team_match_week_stat;

INSERT INTO public.d11_team_season_stat (
    id,
    d11_team_id,
    season_id,
    win_count,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
)
SELECT
    id,
    d11_team_id,
    season_id,
    win_count,
    matches_played,
    matches_won,
    matches_drawn,
    matches_lost,
    goals_for,
    goals_against,
    goal_difference,
    points,
    form_points,
    form_match_points,
    ranking,
    previous_ranking,
    created_at,
    updated_at
FROM production.d11_team_season_stat;

UPDATE public.match_week mw
SET
    premier_league_leader_id = old_mw.premier_league_leader_id,
    d11_league_leader_id = old_mw.d11_league_leader_id,
    most_valuable_player_id = old_mw.most_valuable_player_id
    FROM production.match_week old_mw
WHERE mw.id = old_mw.id;

INSERT INTO public.transfer_window SELECT * FROM production.transfer_window;
INSERT INTO public.transfer_day SELECT * FROM production.transfer_day;
INSERT INTO public.transfer_listing SELECT * FROM production.transfer_listing;
INSERT INTO public.transfer_bid SELECT * FROM production.transfer_bid;

INSERT INTO public.transfer(
    id,
    transfer_day_id,
    player_id,
    d11_team_id,
    transfer_listing_id,
    fee,
    created_at,
    updated_at
)
SELECT
    id,
    transfer_day_id,
    player_id,
    d11_team_id,
    1,
    fee,
    created_at,
    updated_at
FROM production.transfer;

UPDATE public.transfer t
SET transfer_listing_id = tl.id
FROM public.transfer_listing tl
WHERE tl.transfer_day_id = t.transfer_day_id
  AND tl.player_id = t.player_id;

DO $$
DECLARE r record;
BEGIN
FOR r IN
SELECT
    n.nspname AS schema_name,
    c.relname AS sequence_name,
    t.relname AS table_name
FROM pg_class c
         JOIN pg_namespace n ON n.oid = c.relnamespace
         LEFT JOIN pg_depend d ON d.objid = c.oid AND d.deptype = 'a'
         LEFT JOIN pg_class t ON d.refobjid = t.oid
WHERE c.relkind = 'S'
  AND n.nspname = 'public'
    LOOP
    IF r.table_name IS NOT NULL THEN
      EXECUTE format(
        'SELECT setval(%L, COALESCE((SELECT MAX(id) FROM public.%I), 1), true);',
        r.schema_name || '.' || r.sequence_name,
        r.table_name
      );
END IF;
END LOOP;
END$$;

COMMIT;
