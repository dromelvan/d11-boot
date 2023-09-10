CREATE TABLE IF NOT EXISTS refresh_token (
    id SERIAL PRIMARY KEY,
    application_user_id INTEGER NOT NULL REFERENCES application_user,
    uuid UUID UNIQUE NOT NULL,
    expires_at TIMESTAMP WITHOUT TIME ZONE,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
