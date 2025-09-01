package com.muntaha.springplayground.service;

import com.muntaha.springplayground.dto.LeaderboardPageDTO;

public interface LeaderboardService {
    LeaderboardPageDTO getLeaderboard(
            String sortBy,
            String direction,
            int page,
            int size,
            Integer minRating,
            String title // optional (GM/IM/FM/NM/CM), case-insensitive
    );
}
