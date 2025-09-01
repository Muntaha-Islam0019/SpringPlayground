package com.muntaha.springplayground.controller;

import com.muntaha.springplayground.dto.LeaderboardPageDTO;
import com.muntaha.springplayground.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService service;

    public LeaderboardController(LeaderboardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<LeaderboardPageDTO> getLeaderboard(
            @RequestParam(defaultValue = "rating") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer minRating,
            @RequestParam(required = false) String title) {
        return ResponseEntity.ok(
                service.getLeaderboard(sortBy, direction, page, size, minRating, title));
    }
}
