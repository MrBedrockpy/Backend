package ru.neocode.neocode.tasktracker.service;

import ru.neocode.neocode.tasktracker.dto.request.CreateBoardRequest;
import ru.neocode.neocode.tasktracker.dto.request.RenameBoardRequest;
import ru.neocode.neocode.tasktracker.dto.response.BoardResponse;
import ru.neocode.neocode.util.response.ApiResponse;

public interface BoardService {

    ApiResponse<BoardResponse> create(CreateBoardRequest request);

    ApiResponse<BoardResponse> findById(long id);

    ApiResponse<Void> rename(RenameBoardRequest request);

    ApiResponse<Void> deleteById(long id);

}
