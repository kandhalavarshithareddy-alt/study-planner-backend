package com.example.studyplanner.repository;

import com.example.studyplanner.model.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {
}
