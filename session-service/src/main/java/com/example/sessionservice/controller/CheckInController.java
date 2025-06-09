package com.example.sessionservice.controller;

import com.example.sessionservice.model.CheckIn;
import com.example.sessionservice.repository.CheckInRepository;
import com.example.sessionservice.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    private final CheckInRepository checkInRepository;

    public CheckInController(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    @Operation(summary = "Get a bookmark", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public List<CheckIn> getAllCheckIns() {
        String userId = SecurityUtil.getCurrentUserId();
        return checkInRepository.findByUserId(userId);
    }

    @Operation(summary = "Get all check-ins (admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<CheckIn> getAllCheckInsForAdmin() {
        return checkInRepository.findAll();
    }

    @Operation(summary = "Create a bookmark", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
        checkIn.setUserId(SecurityUtil.getCurrentUserId());
        return checkInRepository.save(checkIn);
    }

    @Operation(summary = "Update a bookmark", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn checkIn) {
        String userId = SecurityUtil.getCurrentUserId();
        return checkInRepository.findById(id)
            .filter(c -> c.getUserId().equals(userId))
            .map(existing -> {
                existing.setCode(checkIn.getCode());
                existing.setDescription(checkIn.getDescription());
                return ResponseEntity.ok(checkInRepository.save(existing));
            })
            .orElse(ResponseEntity.status(403).build());
    }

    @Operation(summary = "Delete a bookmark", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        String userId = SecurityUtil.getCurrentUserId();
        return checkInRepository.findById(id)
            .filter(c -> c.getUserId().equals(userId))
            .map(c -> {
                checkInRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.status(403).build());
    }
}