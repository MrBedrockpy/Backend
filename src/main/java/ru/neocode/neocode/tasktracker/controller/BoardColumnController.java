package ru.neocode.neocode.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.tasktracker.dto.request.CreateColumnRequest;
import ru.neocode.neocode.tasktracker.dto.request.MoveColumnRequest;
import ru.neocode.neocode.tasktracker.dto.request.RenameColumnRequest;
import ru.neocode.neocode.tasktracker.dto.response.BoardColumnResponse;
import ru.neocode.neocode.tasktracker.service.BoardColumnService;
import ru.neocode.neocode.util.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasktracker/column")
public class BoardColumnController {

    private final BoardColumnService service;

    @PostMapping
    public ResponseEntity<ApiResponse<BoardColumnResponse>> create(@RequestBody CreateColumnRequest request) {
        return this.service.create(request.getBoardId(), request.getTitle()).toEntity();
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<BoardColumnResponse>> findById(@RequestParam(name = "id") long id) {
        return this.service.getById(id).toEntity();
    }

    @PostMapping("/rename")
    public ResponseEntity<ApiResponse<Void>> rename(@RequestBody RenameColumnRequest request) {
        return this.service.rename(request.getColumnId(), request.getNewTitle()).toEntity();
    }

    @PostMapping("/move")
    public ResponseEntity<ApiResponse<Void>> move(@RequestBody MoveColumnRequest request) {
        return this.service.move(request.getColumnId(), request.getNewPosition()).toEntity();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<ApiResponse<Void>> deleteById(@RequestParam(name = "id") long id) {
        return this.service.deleteById(id).toEntity();
    }
}
