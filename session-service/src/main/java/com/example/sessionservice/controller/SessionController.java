package com.example.sessionservice.controller;

import com.example.sessionservice.model.Session;
import com.example.sessionservice.repository.SessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // Unauthenticated GET
    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    // Only ADMIN can POST
    @Operation(summary = "Create a session", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionRepository.save(session);
    }

    // Only ADMIN can DELETE
    @Operation(summary = "Delete a session", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        if (sessionRepository.existsById(id)) {
            sessionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}