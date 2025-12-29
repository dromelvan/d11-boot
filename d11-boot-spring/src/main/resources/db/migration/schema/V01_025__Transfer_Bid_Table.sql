CREATE TABLE IF NOT EXISTS transfer_bid(
    id SERIAL PRIMARY KEY,
    transfer_day_id INTEGER NOT NULL REFERENCES transfer_day,
    player_id INTEGER NOT NULL REFERENCES player,
    d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    player_ranking INTEGER NOT NULL,
    d11_team_ranking INTEGER NOT NULL,
    fee INTEGER NOT NULL,
    active_fee INTEGER NOT NULL,
    successful BOOLEAN NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);
