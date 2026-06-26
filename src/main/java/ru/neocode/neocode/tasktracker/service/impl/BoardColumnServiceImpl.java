package ru.neocode.neocode.tasktracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.tasktracker.dto.response.BoardColumnResponse;
import ru.neocode.neocode.tasktracker.dto.response.BoardResponse;
import ru.neocode.neocode.tasktracker.entity.Board;
import ru.neocode.neocode.tasktracker.entity.BoardColumn;
import ru.neocode.neocode.tasktracker.repository.BoardColumnRepo;
import ru.neocode.neocode.tasktracker.repository.BoardRepo;
import ru.neocode.neocode.tasktracker.service.BoardColumnService;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardColumnServiceImpl implements BoardColumnService {

    private final BoardColumnRepo columnRepo;
    private final BoardRepo boardRepo;

    @Override
    public ApiResponse<BoardColumnResponse> create(long boardId, String title) {
        Board board = boardRepo.findById(boardId).orElse(null);
        if (board == null) return ApiResponse.error(ApiError.notFound("Board not found"));
        List<BoardColumn> columns = columnRepo.findAllByBoardOrderByPosition(board);
        BoardColumn column = BoardColumn.builder().board(board).name(title)
                .position(columns.size()).tasks(new ArrayList<>()).build();
        columnRepo.save(column);
        return ApiResponse.success(BoardColumnResponse.from(column));
    }

    @Override
    public ApiResponse<BoardColumnResponse> findById(long id) {
        return columnRepo.findById(id)
                .map(BoardColumnResponse::from)
                .map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("Column not found")));
    }

    @Override
    public ApiResponse<Void> rename(long id, String newTitle) {
        BoardColumn column = columnRepo.findById(id).orElse(null);
        if (column == null) return ApiResponse.error(ApiError.notFound("Column not found"));
        column.setName(newTitle);
        columnRepo.save(column);
        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse<Void> move(long id, int newPosition) {
        BoardColumn column = columnRepo.findById(id).orElse(null);
        if (column == null) return ApiResponse.error(ApiError.notFound("Column not found"));
        List<BoardColumn> columns = columnRepo.findAllByBoardOrderByPosition(column.getBoard());
        if (newPosition < 0 || newPosition >= columns.size())
            return ApiResponse.error(ApiError.badRequest("Invalid position"));
        columns.remove(column);
        columns.add(newPosition, column);
        for (int i = 0; i < columns.size(); i++) columns.get(i).setPosition(i);
        columnRepo.saveAll(columns);
        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse<Void> deleteById(long id) {
        BoardColumn column = columnRepo.findById(id).orElse(null);
        if (column == null) return ApiResponse.error(ApiError.notFound("Column not found"));
        Board board = column.getBoard();
        columnRepo.delete(column);
        List<BoardColumn> columns = columnRepo.findAllByBoardOrderByPosition(board);
        for (int i = 0; i < columns.size(); i++) columns.get(i).setPosition(i);
        columnRepo.saveAll(columns);
        return ApiResponse.success(null);
    }
}