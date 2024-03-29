CREATE TABLE IF NOT EXISTS d11_team (
    id SERIAL PRIMARY KEY,
    owner_id INTEGER REFERENCES application_user,
    co_owner_id INTEGER REFERENCES application_user,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(255) NOT NULL,
    code VARCHAR(3) NOT NULL,
    dummy BOOLEAN NOT NULL,
    photo_file_name VARCHAR(64),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
