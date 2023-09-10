CREATE TABLE IF NOT EXISTS player_match_stat(
    id SERIAL PRIMARY KEY,
    player_id INTEGER NOT NULL REFERENCES player,
    match_id INTEGER NOT NULL REFERENCES match,
    d11_match_id INTEGER REFERENCES d11_match,
    team_id INTEGER NOT NULL REFERENCES team,
    d11_team_id INTEGER NOT NULL REFERENCES d11_team,
    position_id INTEGER NOT NULL REFERENCES position,
    played_position VARCHAR(3) NOT NULL,
    lineup INTEGER NOT NULL DEFAULT 0,
    substitution_on_time INTEGER NOT NULL DEFAULT 0,
    substitution_off_time INTEGER NOT NULL DEFAULT 0,
    goals INTEGER NOT NULL DEFAULT 0,
    goal_assists INTEGER NOT NULL DEFAULT 0,
    own_goals INTEGER NOT NULL DEFAULT 0,
    goals_conceded INTEGER NOT NULL DEFAULT 0,
    yellow_card_time INTEGER NOT NULL DEFAULT 0,
    red_card_time INTEGER NOT NULL DEFAULT 0,
    man_of_the_match BOOLEAN NOT NULL DEFAULT false,
    shared_man_of_the_match BOOLEAN NOT NULL DEFAULT false,
    rating INTEGER NOT NULL DEFAULT 0,
    points INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()::timestamp
);

ALTER TABLE match_week
ADD CONSTRAINT match_week_most_valuable_player_id_fkey
FOREIGN KEY (most_valuable_player_id) REFERENCES player_match_stat(id);
