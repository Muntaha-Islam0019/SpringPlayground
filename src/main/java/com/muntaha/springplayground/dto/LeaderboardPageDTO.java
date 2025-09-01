package com.muntaha.springplayground.dto;

import java.util.List;

public record LeaderboardPageDTO(
        List<LeaderboardRowDTO> items,
        int page,
        int size,
        int total,
        String sortBy,
        String direction,
        Integer minRating,
        String title // normalized (uppercased) or null
) {
}
