CREATE TABLE
    players (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(32) NOT NULL UNIQUE,
        rating INT NOT NULL DEFAULT 1200,
        country_code CHAR(2) NOT NULL,
        total_games INT NOT NULL DEFAULT 0,
        wins INT NOT NULL DEFAULT 0,
        losses INT NOT NULL DEFAULT 0,
        draws INT NOT NULL DEFAULT 0,
        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        last_active_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE
    games (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        white_player_id BIGINT NOT NULL,
        black_player_id BIGINT NOT NULL,
        result VARCHAR(8) NOT NULL CHECK (result IN ('WHITE', 'BLACK', 'DRAW', 'ABORTED')),
        rated BOOLEAN NOT NULL DEFAULT TRUE,
        moves SMALLINT NOT NULL,
        started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        ended_at TIMESTAMP NULL,
        termination VARCHAR(32),
        CONSTRAINT fk_games_white FOREIGN KEY (white_player_id) REFERENCES players (id),
        CONSTRAINT fk_games_black FOREIGN KEY (black_player_id) REFERENCES players (id)
    );

CREATE INDEX idx_players_rating_last_active ON players (rating DESC, last_active_at DESC);

CREATE INDEX idx_players_country_rating_last ON players (country_code, rating DESC, last_active_at DESC);

CREATE INDEX idx_players_last_active ON players (last_active_at DESC);

CREATE INDEX idx_games_white_started_at ON games (white_player_id, started_at);

CREATE INDEX idx_games_black_started_at ON games (black_player_id, started_at);

CREATE INDEX idx_games_result_ended ON games (result, ended_at);