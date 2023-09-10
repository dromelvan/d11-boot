CREATE TABLE IF NOT EXISTS transfer_window(
    id SERIAL PRIMARY KEY,
    match_week_id INTEGER NOT NULL REFERENCES match_week,
    transfer_window_number INTEGER NOT NULL DEFAULT 0,
    draft BOOLEAN NOT NULL DEFAULT false,
    status INTEGER NOT NULL,
    datetime TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
