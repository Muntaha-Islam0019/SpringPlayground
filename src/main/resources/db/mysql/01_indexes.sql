USE chess_playground;

CREATE INDEX idx_players_rating_last_active ON players (rating DESC, last_active_at DESC);

CREATE INDEX idx_players_country_rating_last ON players (country_code, rating DESC, last_active_at DESC);

CREATE INDEX idx_players_last_active ON players (last_active_at DESC);

CREATE INDEX idx_games_white_started_at ON games (white_player_id, started_at);

CREATE INDEX idx_games_black_started_at ON games (black_player_id, started_at);

CREATE INDEX idx_games_result_ended ON games (result, ended_at);