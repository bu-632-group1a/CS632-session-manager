package com.example.sessionservice.controller;

import com.example.sessionservice.model.SessionBookmark;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/bookmarks")
public class SessionBookmarkController {
    private final Map<String, SessionBookmark> bookmarks = new HashMap<>();

    @PostMapping
    public ResponseEntity<SessionBookmark> create(@RequestBody SessionBookmark bookmark) {
        String id = UUID.randomUUID().toString();
        bookmark.setId(id);
        bookmarks.put(id, bookmark);
        return ResponseEntity.ok(bookmark);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionBookmark> read(@PathVariable String id) {
        SessionBookmark bookmark = bookmarks.get(id);
        if (bookmark == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bookmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionBookmark> update(@PathVariable String id, @RequestBody SessionBookmark bookmark) {
        if (!bookmarks.containsKey(id)) return ResponseEntity.notFound().build();
        bookmark.setId(id);
        bookmarks.put(id, bookmark);
        return ResponseEntity.ok(bookmark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookmarks.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SessionBookmark>> list() {
        return ResponseEntity.ok(new ArrayList<>(bookmarks.values()));
    }
}
