CREATE TABLE IF NOT EXISTS d11_match_week (
    id SERIAL PRIMARY KEY,
    d11_league_id INTEGER NOT NULL REFERENCES d11_league,
    match_week_id INTEGER NOT NULL REFERENCES match_week,
    match_week_number INTEGER NOT NULL,
    date DATE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
