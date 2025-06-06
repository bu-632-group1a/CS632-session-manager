package com.example.sessionservice.controller;

import com.example.sessionservice.model.CheckIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sessionservice.repository.CheckInRepository;

import java.util.*;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    private final CheckInRepository checkInRepository;

    public CheckInController(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @PostMapping
    public ResponseEntity<CheckIn> create(@RequestBody CheckIn checkIn) {
        return ResponseEntity.ok(checkInRepository.save(checkIn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> read(@PathVariable Long id) {
        return checkInRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> update(@PathVariable Long id, @RequestBody CheckIn checkIn) {
        if (!checkInRepository.existsById(id)) return ResponseEntity.notFound().build();
        checkIn.setId(id);
        return ResponseEntity.ok(checkInRepository.save(checkIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        checkInRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CheckIn>> list() {
        return ResponseEntity.ok(checkInRepository.findAll());
    }
}