CREATE TABLE IF NOT EXISTS premier_league (
    id SERIAL PRIMARY KEY,
    season_id INTEGER NOT NULL REFERENCES season,
    name VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);