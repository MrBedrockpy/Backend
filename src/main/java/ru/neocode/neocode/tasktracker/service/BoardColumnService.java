package ru.neocode.neocode.tasktracker.service;

import ru.neocode.neocode.tasktracker.dto.response.BoardColumnResponse;
import ru.neocode.neocode.util.response.ApiResponse;

public interface BoardColumnService {

    ApiResponse<BoardColumnResponse> create(long boardId, String title);

    ApiResponse<BoardColumnResponse> getById(long id);

    ApiResponse<Void> rename(long id, String newTitle);

    ApiResponse<Void> move(long id, int newPosition);

    ApiResponse<Void> deleteById(long id);

}
