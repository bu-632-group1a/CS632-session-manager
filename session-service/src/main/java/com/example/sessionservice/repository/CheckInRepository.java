package com.example.sessionservice.repository;

import com.example.sessionservice.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByUserId(String userId);
}