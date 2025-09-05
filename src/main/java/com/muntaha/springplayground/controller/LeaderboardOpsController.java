package com.muntaha.springplayground.controller;

import com.muntaha.springplayground.service.LeaderboardRecomputeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardOpsController {

    private final LeaderboardRecomputeService jobs;

    public LeaderboardOpsController(LeaderboardRecomputeService jobs) {
        this.jobs = jobs;
    }

    /**
     * Manually queue a background recompute (temporary until you have game saves).
     */
    @PostMapping("/recompute")
    public ResponseEntity<?> recompute() {
        jobs.triggerRecompute();
        record Msg(String message) {
        }
        return ResponseEntity.accepted().body(new Msg("recompute queued"));
    }
}
