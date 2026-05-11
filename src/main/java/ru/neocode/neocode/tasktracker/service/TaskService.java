package ru.neocode.neocode.tasktracker.service;

import ru.neocode.neocode.tasktracker.dto.request.CreateTaskRequest;
import ru.neocode.neocode.tasktracker.dto.request.MoveTaskRequest;
import ru.neocode.neocode.tasktracker.dto.response.TaskResponse;
import ru.neocode.neocode.util.response.ApiResponse;

public interface TaskService {

    ApiResponse<TaskResponse> create(CreateTaskRequest createTaskRequest);

    ApiResponse<TaskResponse> findById(long id);

    ApiResponse<Void> move(MoveTaskRequest request);

    ApiResponse<Void> deleteById(long id);

}
