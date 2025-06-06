package com.example.sessionservice.controller;

import com.example.sessionservice.model.CheckIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    private final Map<String, CheckIn> checkIns = new HashMap<>();

    @PostMapping
    public ResponseEntity<CheckIn> create(@RequestBody CheckIn checkIn) {
        String id = UUID.randomUUID().toString();
        checkIn.setId(id);
        checkIns.put(id, checkIn);
        return ResponseEntity.ok(checkIn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> read(@PathVariable String id) {
        CheckIn checkIn = checkIns.get(id);
        if (checkIn == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(checkIn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> update(@PathVariable String id, @RequestBody CheckIn checkIn) {
        if (!checkIns.containsKey(id)) return ResponseEntity.notFound().build();
        checkIn.setId(id);
        checkIns.put(id, checkIn);
        return ResponseEntity.ok(checkIn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        checkIns.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CheckIn>> list() {
        return ResponseEntity.ok(new ArrayList<>(checkIns.values()));
    }
}
