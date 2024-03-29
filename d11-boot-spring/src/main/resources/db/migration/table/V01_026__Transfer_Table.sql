CREATE TABLE IF NOT EXISTS transfer(
    id SERIAL PRIMARY KEY,
    transfer_day_id INTEGER NOT NULL REFERENCES transfer_day,
    player_id INTEGER NOT NULL REFERENCES player,
    d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    fee INTEGER NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
