package ru.neocode.neocode.tasktracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.tasktracker.dto.request.CreateBoardRequest;
import ru.neocode.neocode.tasktracker.dto.request.RenameBoardRequest;
import ru.neocode.neocode.tasktracker.dto.response.BoardResponse;
import ru.neocode.neocode.tasktracker.service.BoardService;
import ru.neocode.neocode.util.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasktracker/board")
public class BoardController {

    private final BoardService service;

    @PostMapping
    public ResponseEntity<ApiResponse<BoardResponse>> create(@RequestBody CreateBoardRequest request) {
        return this.service.create(request).toEntity();
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<BoardResponse>> findById(@RequestParam(name = "id") long id) {
        return this.service.findById(id).toEntity();
    }

    @PostMapping("/rename")
    public ResponseEntity<ApiResponse<Void>> rename(@RequestBody RenameBoardRequest request) {
        return this.service.rename(request).toEntity();
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<ApiResponse<Void>> deleteById(@RequestParam(name = "id") long id) {
        return this.service.deleteById(id).toEntity();
    }
}
