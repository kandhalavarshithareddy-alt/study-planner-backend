package com.example.studyplanner.service;

import com.example.studyplanner.model.StudyTask;
import com.example.studyplanner.repository.StudyTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyTaskService {
    private final StudyTaskRepository repository;

    public StudyTaskService(StudyTaskRepository repository) {
        this.repository = repository;
    }

    public List<StudyTask> getAllTasks() {
        return repository.findAll();
    }

    public StudyTask getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public StudyTask addTask(StudyTask task) {
        if (task.getStatus() == null || task.getStatus().isBlank()) {
            task.setStatus("Pending");
        }
        return repository.save(task);
    }

    public StudyTask updateTask(Long id, StudyTask updatedTask) {
        StudyTask task = repository.findById(id).orElse(null);
        if (task == null) return null;

        task.setSubject(updatedTask.getSubject());
        task.setTopic(updatedTask.getTopic());
        task.setStudyDate(updatedTask.getStudyDate());
        task.setStudyTime(updatedTask.getStudyTime());
        task.setStatus(updatedTask.getStatus());

        return repository.save(task);
    }

    public boolean deleteTask(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
