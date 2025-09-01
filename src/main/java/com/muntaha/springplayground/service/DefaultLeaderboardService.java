package com.muntaha.springplayground.service;

import com.muntaha.springplayground.domain.Player;
import com.muntaha.springplayground.dto.LeaderboardPageDTO;
import com.muntaha.springplayground.dto.LeaderboardRowDTO;
import com.muntaha.springplayground.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class DefaultLeaderboardService implements LeaderboardService {

    private final PlayerRepository repo;

    public DefaultLeaderboardService(PlayerRepository repo) {
        this.repo = repo;
    }

    @Override
    public LeaderboardPageDTO getLeaderboard(
            String sortBy,
            String direction,
            int page,
            int size,
            Integer minRating,
            String title) {
        String normalizedSort = normalizeSortBy(sortBy);
        String normalizedDir = normalizeDirection(direction);
        int safePage = Math.max(page, 0);
        int safeSize = clamp(size, 1, 100);
        Integer safeMin = (minRating != null && minRating > 0) ? minRating : null;
        String normalizedTitle = normalizeTitle(title);

        List<Player> all = repo.findAll();

        List<Player> filtered = all.stream()
                .filter(p -> safeMin == null || p.rating() >= safeMin)
                .filter(p -> normalizedTitle == null || normalizedTitle.equalsIgnoreCase(nullSafe(p.title())))
                .toList();

        Comparator<Player> cmp = switch (normalizedSort) {
            case "username" -> Comparator.comparing(p -> p.username().toLowerCase(Locale.ROOT));
            case "title" -> Comparator.comparing(p -> nullSafe(p.title()).toUpperCase(Locale.ROOT));
            case "country" -> Comparator.comparing(p -> nullSafe(p.country()).toUpperCase(Locale.ROOT));
            default -> Comparator.comparingInt(Player::rating); // "rating"
        };

        // tie-breaker: username asc for stability
        cmp = cmp.thenComparing(p -> p.username().toLowerCase(Locale.ROOT));

        if ("desc".equals(normalizedDir)) {
            cmp = cmp.reversed();
        }

        List<Player> ordered = filtered.stream()
                .sorted(cmp)
                .toList();

        int total = ordered.size();
        int from = Math.min(safePage * safeSize, total);
        int to = Math.min(from + safeSize, total);
        List<Player> slice = ordered.subList(from, to);

        // rank is 1-based across the full ordered list
        int startRank = from + 1;
        final int[] rank = { startRank };

        List<LeaderboardRowDTO> rows = slice.stream()
                .map(p -> new LeaderboardRowDTO(
                        rank[0]++,
                        p.username(),
                        p.title(),
                        p.country(),
                        p.rating()))
                .toList();

        return new LeaderboardPageDTO(
                rows, safePage, safeSize, total, normalizedSort, normalizedDir, safeMin, normalizedTitle);
    }

    private static String normalizeSortBy(String sortBy) {
        if (sortBy == null)
            return "rating";
        return switch (sortBy.toLowerCase(Locale.ROOT)) {
            case "username", "title", "country", "rating" -> sortBy.toLowerCase(Locale.ROOT);
            default -> "rating";
        };
    }

    private static String normalizeDirection(String dir) {
        if (dir == null)
            return "desc";
        String low = dir.toLowerCase(Locale.ROOT);
        return ("asc".equals(low) || "desc".equals(low)) ? low : "desc";
    }

    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    private static String normalizeTitle(String t) {
        if (t == null)
            return null;
        String up = t.trim().toUpperCase(Locale.ROOT);
        return switch (up) {
            case "GM", "IM", "FM", "NM", "CM" -> up;
            default -> null; // treat unknown as not provided
        };
    }

    private static String nullSafe(String s) {
        return s == null ? "" : s;
    }
}
