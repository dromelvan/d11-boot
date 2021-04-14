CREATE TABLE IF NOT EXISTS match_week (
    id SERIAL PRIMARY KEY,
    premier_league_id INTEGER NOT NULL REFERENCES premier_league,
    league_leader_id INTEGER REFERENCES team,
    most_valuable_player_id INTEGER,
    match_week_number INTEGER NOT NULL,
    date DATE NOT NULL,
    elapsed INTEGER NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
