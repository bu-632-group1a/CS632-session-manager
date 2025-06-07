package com.example.sessionservice.controller;

import com.example.sessionservice.model.Bookmark;
import com.example.sessionservice.repository.BookmarkRepository;
import com.example.sessionservice.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        String userId = SecurityUtil.getCurrentUserId();
        return bookmarkRepository.findByUserId(userId);
    }

    @PostMapping
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        bookmark.setUserId(SecurityUtil.getCurrentUserId());
        return bookmarkRepository.save(bookmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark) {
        String userId = SecurityUtil.getCurrentUserId();
        return bookmarkRepository.findById(id)
            .filter(b -> b.getUserId().equals(userId))
            .map(existing -> {
                existing.setCode(bookmark.getCode());
                existing.setDescription(bookmark.getDescription());
                return ResponseEntity.ok(bookmarkRepository.save(existing));
            })
            .orElse(ResponseEntity.status(403).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        String userId = SecurityUtil.getCurrentUserId();
        return bookmarkRepository.findById(id)
            .filter(b -> b.getUserId().equals(userId))
            .map(b -> {
                bookmarkRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.status(403).build());
    }
}