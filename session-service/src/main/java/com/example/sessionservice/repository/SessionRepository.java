package com.example.sessionservice.repository;

import com.example.sessionservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}