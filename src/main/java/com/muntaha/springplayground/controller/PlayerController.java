package com.muntaha.springplayground.controller;

import com.muntaha.springplayground.dto.PlayerDTO;
import com.muntaha.springplayground.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository repo;

    public PlayerController(PlayerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> all() {
        List<PlayerDTO> items = repo.findAll().stream()
                .map(p -> new PlayerDTO(p.id(), p.username(), p.country(), p.title(), p.rating()))
                .toList();
        return ResponseEntity.ok(items);
    }
}
