package com.muntaha.springplayground.controller;

import com.muntaha.springplayground.error.BadRequestException;
import com.muntaha.springplayground.error.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    public record Msg(String message) {
    }

    @GetMapping
    public ResponseEntity<?> say(@RequestParam(required = false) String who) {
        // No param provided → default
        if (who == null) {
            who = "world";
        }
        // Param provided but blank → 400
        if (who.isBlank()) {
            throw new BadRequestException("Parameter 'who' must not be blank");
        }
        // Demo not-found
        if ("missing".equalsIgnoreCase(who)) {
            throw new ResourceNotFoundException("We couldn’t find that person");
        }
        return ResponseEntity.ok(new Msg("Hello " + who));
    }
}
