CREATE TABLE IF NOT EXISTS d11_team_match_week_stat(
    id SERIAL PRIMARY KEY,
    d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    match_week_id INTEGER NOT NULL REFERENCES match_week,
    matches_played INTEGER NOT NULL DEFAULT 0,
    matches_won INTEGER NOT NULL DEFAULT 0,
    matches_drawn INTEGER NOT NULL DEFAULT 0,
    matches_lost INTEGER NOT NULL DEFAULT 0,
    goals_for INTEGER NOT NULL DEFAULT 0,
    goals_against INTEGER NOT NULL DEFAULT 0,
    goal_difference INTEGER NOT NULL DEFAULT 0,
    points INTEGER NOT NULL DEFAULT 0,
    form_points INTEGER NOT NULL DEFAULT 0,
    form_match_points VARCHAR(255) NOT NULL,
    ranking INTEGER NOT NULL DEFAULT 0,
    previous_ranking INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

ALTER TABLE match_week
ADD CONSTRAINT match_week_d11_league_leader_id_fkey
FOREIGN KEY (d11_league_leader_id) REFERENCES d11_team_match_week_stat(id);
