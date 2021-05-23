CREATE TABLE IF NOT EXISTS d11_match (
    id SERIAL PRIMARY KEY,
    home_d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    away_d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    match_week_id INTEGER NOT NULL REFERENCES match_week,
    date DATE NOT NULL,
    home_team_goals INTEGER NOT NULL,
    away_team_goals INTEGER NOT NULL,
    home_team_points INTEGER NOT NULL,
    away_team_points INTEGER NOT NULL,
    previous_home_team_goals INTEGER NOT NULL,
    previous_away_team_goals INTEGER NOT NULL,
    previous_home_team_points INTEGER NOT NULL,
    previous_away_team_points INTEGER NOT NULL,
    elapsed VARCHAR(10) NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
