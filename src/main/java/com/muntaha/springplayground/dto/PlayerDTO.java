package com.muntaha.springplayground.dto;

public record PlayerDTO(
        Long id,
        String username,
        String country,
        String title,
        Integer rating) {
}
