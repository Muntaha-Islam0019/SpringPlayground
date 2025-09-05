package com.muntaha.springplayground.service;

import com.muntaha.springplayground.domain.Player;
import com.muntaha.springplayground.repository.PlayerRepository;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LeaderboardRecomputeService {

    private static final Logger log = LoggerFactory.getLogger(LeaderboardRecomputeService.class);

    private final PlayerRepository repo;

    public LeaderboardRecomputeService(PlayerRepository repo) {
        this.repo = repo;
    }

    // single-lane worker for serialized recomputes
    private final ExecutorService single = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "leaderboard-recompute");
        t.setDaemon(true);
        return t;
    });

    // coalesce bursts so we don’t queue duplicates
    private final AtomicBoolean queued = new AtomicBoolean(false);

    /** Fire-and-forget: queues one recompute if not already queued. */
    public void triggerRecompute() {
        if (!queued.compareAndSet(false, true)) {
            log.debug("Recompute already queued; skipping");
            return;
        }
        single.submit(() -> {
            long start = System.currentTimeMillis();
            try {
                recompute();
                log.info("Recomputed leaderboard in {} ms", System.currentTimeMillis() - start);
            } catch (Exception e) {
                log.error("Leaderboard recompute failed", e);
            } finally {
                queued.set(false);
            }
        });
    }

    /** Demo implementation: read players, sort them like your leaderboard would. */
    private void recompute() {
        List<Player> players = repo.findAll();

        Comparator<Player> cmp = Comparator
                .comparingInt(Player::rating) // rating
                .thenComparing(p -> p.username().toLowerCase(Locale.ROOT)); // stable tie-break

        // simulate the “work” (e.g., caching ranks, writing to DB later)
        List<Player> ordered = players.stream().sorted(cmp.reversed()).toList();
        log.debug("Top after recompute: {}", ordered.stream().limit(3).map(Player::username).toList());
        // TODO (when you add DB/cache): persist ranks or warm a cache here
    }

    @PreDestroy
    public void shutdown() {
        single.shutdown();
    }
}
