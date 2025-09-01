package com.muntaha.springplayground.repository;

import com.muntaha.springplayground.domain.Player;
import java.util.List;

public interface PlayerRepository {
    List<Player> findAll();

    void saveAll(List<Player> players);
}
