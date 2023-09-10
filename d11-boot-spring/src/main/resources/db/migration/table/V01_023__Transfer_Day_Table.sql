CREATE TABLE IF NOT EXISTS transfer_day(
    id SERIAL PRIMARY KEY,
    transfer_window_id INTEGER NOT NULL REFERENCES transfer_window,
    transfer_day_number INTEGER NOT NULL DEFAULT 1,
    status INTEGER NOT NULL,
    datetime TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
