package com.muntaha.springplayground.domain;

public record Player(
        Long id,
        String username,
        String country, // ISO-3 or ISO-2; free-form for now
        String title, // GM/IM/FM/NM/CM or null
        int rating // current rating for the selected mode (simplified)
) {
}
