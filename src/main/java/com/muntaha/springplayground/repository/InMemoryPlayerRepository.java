package com.muntaha.springplayground.repository;

import com.muntaha.springplayground.domain.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {

    private final List<Player> store = new ArrayList<>();

    public InMemoryPlayerRepository() {
        // Seed a few players for now
        store.addAll(List.of(
                new Player(1L, "AlphaCat", "USA", "GM", 2675),
                new Player(2L, "TacticalFox", "IND", "IM", 2540),
                new Player(3L, "EndgameNerd", "BAN", null, 2100),
                new Player(4L, "QuietMove", "FRA", "FM", 2380),
                new Player(5L, "SharpTactics", "DEU", "NM", 2330),
                new Player(6L, "BlitzWizard", "ESP", "CM", 2210),
                new Player(7L, "SolidCenter", "CAN", null, 2255)));
    }

    @Override
    public List<Player> findAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public void saveAll(List<Player> players) {
        store.clear();
        store.addAll(players);
    }
}
