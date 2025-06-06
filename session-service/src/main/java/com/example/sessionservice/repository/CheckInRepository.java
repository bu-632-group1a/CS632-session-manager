package com.example.sessionservice.repository;

import com.example.sessionservice.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
}