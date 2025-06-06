package com.example.sessionservice.controller;

import com.example.sessionservice.model.Bookmark;
import com.example.sessionservice.repository.BookmarkRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/bookmarks")
public class SessionBookmarkController {
    private final BookmarkRepository bookmarkRepository;

    public SessionBookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    @PostMapping
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @GetMapping("/{id}")
    public Bookmark getBookmark(@PathVariable Long id) {
        return bookmarkRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteBookmark(@PathVariable Long id) {
        bookmarkRepository.deleteById(id);
    }
}