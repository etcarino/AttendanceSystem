package com.icpep.AttendanceSystem.repository;

import com.icpep.AttendanceSystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByEventSelectedTrue();
}
