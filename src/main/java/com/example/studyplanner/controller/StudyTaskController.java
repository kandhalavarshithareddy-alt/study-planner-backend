package com.example.studyplanner.controller;

import com.example.studyplanner.model.StudyTask;
import com.example.studyplanner.service.StudyTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = {
    "${app.cors.allowed-origin:https://kandhalavarshithareddy-alt.github.io}",
    "http://localhost:5500",
    "http://127.0.0.1:5500"
})
public class StudyTaskController {
    private final StudyTaskService service;

    public StudyTaskController(StudyTaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudyTask> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyTask> getTaskById(@PathVariable Long id) {
        StudyTask task = service.getTaskById(id);
        return task == null ? ResponseEntity.notFound().build()
                            : ResponseEntity.ok(task);
    }

    @PostMapping
    public StudyTask addTask(@RequestBody StudyTask task) {
        return service.addTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyTask> updateTask(
            @PathVariable Long id,
            @RequestBody StudyTask task) {
        StudyTask updated = service.updateTask(id, task);
        return updated == null ? ResponseEntity.notFound().build()
                               : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return service.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
