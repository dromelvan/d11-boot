CREATE TABLE IF NOT EXISTS team (
    id SERIAL PRIMARY KEY,
    stadium_id INTEGER NOT NULL,
    whoscored_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    code VARCHAR(3) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    established INTEGER NOT NULL,
    motto VARCHAR(255) NOT NULL,
    colour VARCHAR(255) NOT NULL,
    dummy BOOLEAN NOT NULL,
    photo_file_name VARCHAR(64),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);
