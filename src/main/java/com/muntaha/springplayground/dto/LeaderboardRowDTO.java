package com.muntaha.springplayground.dto;

public record LeaderboardRowDTO(
        int rank,
        String username,
        String title,
        String country,
        int rating) {
}
