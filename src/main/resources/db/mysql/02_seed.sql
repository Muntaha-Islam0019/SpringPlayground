USE chess_playground;

INSERT INTO
    players (
        username,
        rating,
        country_code,
        total_games,
        wins,
        losses,
        draws,
        last_active_at
    )
VALUES
    ('magnus', 2880, 'NO', 1200, 900, 200, 100, NOW ()),
    (
        'hikaru',
        2850,
        'US',
        1500,
        1000,
        350,
        150,
        NOW ()
    ),
    ('alireza', 2810, 'FR', 900, 600, 200, 100, NOW ()),
    ('pragg', 2785, 'IN', 800, 520, 190, 90, NOW ()),
    ('muntaha', 1500, 'BD', 200, 90, 90, 20, NOW ()),
    ('tania', 1750, 'BD', 350, 180, 130, 40, NOW ()),
    (
        'nepomniachtchi',
        2790,
        'RU',
        1100,
        750,
        250,
        100,
        NOW ()
    ),
    (
        'fabiano',
        2820,
        'US',
        1300,
        880,
        300,
        120,
        NOW ()
    ),
    ('wesley', 2760, 'US', 1000, 650, 250, 100, NOW ()),
    ('ding', 2805, 'CN', 1150, 780, 250, 120, NOW ());

INSERT INTO
    games (
        white_player_id,
        black_player_id,
        result,
        rated,
        moves,
        started_at,
        ended_at,
        termination
    )
VALUES
    (
        1,
        2,
        'WHITE',
        TRUE,
        42,
        NOW () - INTERVAL 10 DAY,
        NOW () - INTERVAL 10 DAY,
        'CHECKMATE'
    ),
    (
        2,
        1,
        'DRAW',
        TRUE,
        85,
        NOW () - INTERVAL 9 DAY,
        NOW () - INTERVAL 9 DAY,
        'STALEMATE'
    ),
    (
        5,
        6,
        'BLACK',
        TRUE,
        60,
        NOW () - INTERVAL 3 DAY,
        NOW () - INTERVAL 3 DAY,
        'TIME'
    ),
    (
        6,
        5,
        'WHITE',
        TRUE,
        34,
        NOW () - INTERVAL 2 DAY,
        NOW () - INTERVAL 2 DAY,
        'RESIGN'
    ),
    (
        3,
        4,
        'WHITE',
        TRUE,
        51,
        NOW () - INTERVAL 7 DAY,
        NOW () - INTERVAL 7 DAY,
        'CHECKMATE'
    ),
    (
        4,
        3,
        'BLACK',
        TRUE,
        44,
        NOW () - INTERVAL 5 DAY,
        NOW () - INTERVAL 5 DAY,
        'RESIGN'
    ),
    (
        8,
        9,
        'DRAW',
        TRUE,
        72,
        NOW () - INTERVAL 1 DAY,
        NOW () - INTERVAL 1 DAY,
        'THREEFOLD'
    ),
    (
        9,
        8,
        'BLACK',
        TRUE,
        38,
        NOW () - INTERVAL 12 DAY,
        NOW () - INTERVAL 12 DAY,
        'TIME'
    ),
    (
        10,
        1,
        'WHITE',
        TRUE,
        57,
        NOW () - INTERVAL 4 DAY,
        NOW () - INTERVAL 4 DAY,
        'CHECKMATE'
    ),
    (
        7,
        10,
        'BLACK',
        TRUE,
        49,
        NOW () - INTERVAL 6 DAY,
        NOW () - INTERVAL 6 DAY,
        'RESIGN'
    );