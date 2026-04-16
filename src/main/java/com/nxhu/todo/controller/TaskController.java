package com.nxhu.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nxhu.todo.dto.request.TaskDtoReq;
import com.nxhu.todo.dto.response.TaskDtoRes;
import com.nxhu.todo.exception.TaskNotFoundEx;
import com.nxhu.todo.service.impl.TaskServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping("")
    public ResponseEntity<TaskDtoRes> createTask(@Valid @RequestBody TaskDtoReq taskDtoReq) {

        TaskDtoRes response = taskService.createTask(taskDtoReq);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDtoRes>> findAllTask() {
        List<TaskDtoRes> response = taskService.findAllTasks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDtoRes> findTaskById(@PathVariable Long id) throws TaskNotFoundEx {
        TaskDtoRes response = taskService.findTaskById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDtoRes> patchTask(@PathVariable Long id, @Valid @RequestBody Map<String, Object> updates) throws TaskNotFoundEx {
        TaskDtoRes response = taskService.patchTask(id, updates);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) throws TaskNotFoundEx {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.NO_CONTENT);
    }

    // ENDPOINTS FILTER

    @GetMapping("/filter/title")
    public ResponseEntity<List<TaskDtoRes>> filterByTitle(@RequestParam String title) {
        List<TaskDtoRes> response = taskService.findByTitle(title);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/filter/completed")
    public ResponseEntity<List<TaskDtoRes>> filterByCompleted(@RequestParam Boolean completed) {
        List<TaskDtoRes> response = taskService.findByCompleted(completed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
