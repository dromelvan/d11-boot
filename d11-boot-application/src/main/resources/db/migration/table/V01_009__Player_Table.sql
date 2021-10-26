CREATE TABLE IF NOT EXISTS player (
    id SERIAL PRIMARY KEY,
    country_id INTEGER NOT NULL REFERENCES country,
    whoscored_id INTEGER NOT NULL,
    premier_league_id INTEGER NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    parameterized_name VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    height INTEGER,
    photo_file_name VARCHAR(64),
    verified BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
