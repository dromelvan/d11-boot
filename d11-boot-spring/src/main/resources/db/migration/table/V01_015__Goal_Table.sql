CREATE TABLE IF NOT EXISTS goal (
    id SERIAL PRIMARY KEY,
    match_id INTEGER NOT NULL REFERENCES match,
    team_id INTEGER NOT NULL REFERENCES team,
    player_id INTEGER NOT NULL REFERENCES player,
    time INTEGER NOT NULL,
    added_time INTEGER NOT NULL,
    penalty BOOLEAN NOT NULL,
    own_goal BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
