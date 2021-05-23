CREATE TABLE IF NOT EXISTS match_week (
    id SERIAL PRIMARY KEY,
    season_id INTEGER NOT NULL REFERENCES season,
    premier_league_leader_id INTEGER,
    d11_league_leader_id INTEGER,
    most_valuable_player_id INTEGER,
    match_week_number INTEGER NOT NULL,
    date DATE NOT NULL,
    elapsed INTEGER NOT NULL,
    status INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
