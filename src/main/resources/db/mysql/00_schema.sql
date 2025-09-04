CREATE DATABASE IF NOT EXISTS chess_playground CHARACTER
SET
    utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE chess_playground;

CREATE TABLE
    players (
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
        username VARCHAR(32) NOT NULL,
        rating INT NOT NULL DEFAULT 1200,
        country_code CHAR(2) NOT NULL,
        total_games INT NOT NULL DEFAULT 0,
        wins INT NOT NULL DEFAULT 0,
        losses INT NOT NULL DEFAULT 0,
        draws INT NOT NULL DEFAULT 0,
        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        last_active_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (id),
        UNIQUE KEY uk_username (username)
    ) ENGINE = InnoDB;

CREATE TABLE
    games (
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
        white_player_id BIGINT UNSIGNED NOT NULL,
        black_player_id BIGINT UNSIGNED NOT NULL,
        result ENUM ('WHITE', 'BLACK', 'DRAW', 'ABORTED') NOT NULL,
        rated BOOLEAN NOT NULL DEFAULT TRUE,
        moves SMALLINT UNSIGNED NOT NULL,
        started_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        ended_at DATETIME NULL,
        termination VARCHAR(32) NULL,
        PRIMARY KEY (id),
        CONSTRAINT fk_games_white FOREIGN KEY (white_player_id) REFERENCES players (id),
        CONSTRAINT fk_games_black FOREIGN KEY (black_player_id) REFERENCES players (id)
    ) ENGINE = InnoDB;