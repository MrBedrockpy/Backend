package ru.neocode.neocode.tasktracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.tasktracker.dto.request.CreateBoardRequest;
import ru.neocode.neocode.tasktracker.dto.request.RenameBoardRequest;
import ru.neocode.neocode.tasktracker.dto.response.BoardResponse;
import ru.neocode.neocode.tasktracker.entity.Board;
import ru.neocode.neocode.tasktracker.repository.BoardRepo;
import ru.neocode.neocode.tasktracker.service.BoardService;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepo boardRepo;

    @Override
    public ApiResponse<BoardResponse> create(CreateBoardRequest request) {
        Board board = Board.builder()
                .title(request.getTitle())
                .columns(new ArrayList<>())
                .build();
        boardRepo.save(board);
        return ApiResponse.success(BoardResponse.from(board));
    }

    @Override
    public ApiResponse<BoardResponse> findById(long id) {
        return boardRepo.findById(id)
                .map(BoardResponse::from)
                .map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("Board not found")));
    }

    @Override
    public ApiResponse<Void> rename(RenameBoardRequest request) {
        Board board = boardRepo.findById(request.getBoardId()).orElse(null);
        if (board == null) return ApiResponse.error(ApiError.notFound("Board not found"));
        board.setTitle(request.getNewTitle());
        boardRepo.save(board);
        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse<Void> deleteById(long id) {
        Board board = boardRepo.findById(id).orElse(null);
        if (board == null) return ApiResponse.error(ApiError.notFound("Board not found"));
        boardRepo.delete(board);
        return ApiResponse.success(null);
    }
}