package com.example.sessionservice.controller;

import com.example.sessionservice.model.Bookmark;
import com.example.sessionservice.repository.BookmarkRepository;
import com.example.sessionservice.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

/**
 * Controller for managing user bookmarks.
 */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    /**
     * Get all bookmarks for the authenticated user.
     */
    @Operation(summary = "Get all bookmarks for the authenticated user")
    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        String userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            throw new IllegalStateException("User ID not found in security context");
        }
        return bookmarkRepository.findByUserId(userId);
    }

    /**
     * Get all bookmarks for all users (admin only).
     */
    @Operation(summary = "Get all bookmarks for all users (admin only)", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public List<Bookmark> getAllBookmarksForAdmin() {
        return bookmarkRepository.findAll();
    }


    /**
     * Create a new bookmark for the authenticated user.
     */
    @Operation(summary = "Create a new bookmark for the authenticated user")
    @PostMapping
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        String userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            throw new IllegalStateException("User ID not found in security context");
        }
        bookmark.setUserId(userId);
        return bookmarkRepository.save(bookmark);
    }

    /**
     * Update an existing bookmark for the authenticated user.
     */
    @Operation(summary = "Update an existing bookmark for the authenticated user")
    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark) {
        String userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        return bookmarkRepository.findById(id)
            .filter(b -> b.getUserId().equals(userId))
            .map(existing -> {
                existing.setCode(bookmark.getCode());
                existing.setDescription(bookmark.getDescription());
                return ResponseEntity.ok(bookmarkRepository.save(existing));
            })
            .orElse(ResponseEntity.status(403).build());
    }

    /**
     * Delete a bookmark for the authenticated user.
     */
    @Operation(summary = "Delete a bookmark for the authenticated user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        String userId = SecurityUtil.getCurrentUserId();
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        return bookmarkRepository.findById(id)
            .filter(b -> b.getUserId().equals(userId))
            .map(b -> {
                bookmarkRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.status(403).build());
    }
}