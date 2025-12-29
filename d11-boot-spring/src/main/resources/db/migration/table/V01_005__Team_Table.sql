CREATE TABLE IF NOT EXISTS team (
    id SERIAL PRIMARY KEY,
    stadium_id INTEGER NOT NULL REFERENCES stadium,
    stat_source_id INTEGER NOT NULL,
    premier_league_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    code VARCHAR(3) NOT NULL,
    established INTEGER NOT NULL,
    colour VARCHAR(255) NOT NULL,
    dummy BOOLEAN NOT NULL,
    photo_file_name VARCHAR(64),
    url VARCHAR(64) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
