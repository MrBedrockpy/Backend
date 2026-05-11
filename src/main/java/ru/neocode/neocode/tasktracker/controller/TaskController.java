package ru.neocode.neocode.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.tasktracker.dto.request.CreateTaskRequest;
import ru.neocode.neocode.tasktracker.dto.response.TaskResponse;
import ru.neocode.neocode.tasktracker.service.TaskService;
import ru.neocode.neocode.util.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasktracker/task")
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> create(@RequestBody CreateTaskRequest request) {
        return this.service.create(request).toEntity();
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<TaskResponse>> findById(@RequestParam(name = "id") long id) {
        return this.service.findById(id).toEntity();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<ApiResponse<Void>> deleteById(@RequestParam(name = "id") long id) {
        return this.service.deleteById(id).toEntity();
    }
}
