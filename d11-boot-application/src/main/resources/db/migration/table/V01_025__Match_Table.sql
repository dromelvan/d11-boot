CREATE TABLE IF NOT EXISTS match (
    id SERIAL PRIMARY KEY,
    home_team_id INTEGER NOT NULL REFERENCES team,
    away_team_id INTEGER NOT NULL REFERENCES team,
    match_week_id INTEGER NOT NULL REFERENCES match_week,
    stadium_id INTEGER NOT NULL REFERENCES stadium,
    whoscored_id INTEGER NOT NULL,
    datetime TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    home_team_goals INTEGER NOT NULL,
    away_team_goals INTEGER NOT NULL,
    previous_home_team_goals INTEGER NOT NULL,
    previous_away_team_goals INTEGER NOT NULL,
    elapsed VARCHAR(10) NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

