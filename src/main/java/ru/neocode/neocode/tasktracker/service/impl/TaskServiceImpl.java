package ru.neocode.neocode.tasktracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.tasktracker.dto.request.CreateTaskRequest;
import ru.neocode.neocode.tasktracker.dto.request.MoveTaskRequest;
import ru.neocode.neocode.tasktracker.dto.response.TaskResponse;
import ru.neocode.neocode.tasktracker.entity.BoardColumn;
import ru.neocode.neocode.tasktracker.entity.Task;
import ru.neocode.neocode.tasktracker.repository.BoardColumnRepo;
import ru.neocode.neocode.tasktracker.repository.TaskRepo;
import ru.neocode.neocode.tasktracker.service.TaskService;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final BoardColumnRepo columnRepo;

    @Override
    public ApiResponse<TaskResponse> create(CreateTaskRequest request) {
        BoardColumn column = columnRepo.findById(request.getColumnId()).orElse(null);
        if (column == null) return ApiResponse.error(ApiError.notFound("Column not found"));
        List<Task> tasks = taskRepo.findAllByColumnOrderByPosition(column);
        Task task = Task.builder().column(column).title(request.getTitle())
                .description(request.getDescription()).position(tasks.size()).build();
        taskRepo.save(task);
        return ApiResponse.success(TaskResponse.from(task));
    }

    @Override
    public ApiResponse<TaskResponse> findById(long id) {
        return taskRepo.findById(id)
                .map(TaskResponse::from)
                .map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("Task not found")));
    }

    @Override
    public ApiResponse<Void> move(MoveTaskRequest request) {
        Task task = taskRepo.findById(request.getTaskId()).orElse(null);
        if (task == null) return ApiResponse.error(ApiError.notFound("Task not found"));
        BoardColumn oldColumn = task.getColumn();
        BoardColumn newColumn = columnRepo.findById(request.getNewColumnId()).orElse(null);
        if (newColumn == null) return ApiResponse.error(ApiError.notFound("Target column not found"));
        List<Task> oldTasks = taskRepo.findAllByColumnOrderByPosition(oldColumn);
        oldTasks.remove(task);
        for (int i = 0; i < oldTasks.size(); i++) oldTasks.get(i).setPosition(i);
        List<Task> newTasks = taskRepo.findAllByColumnOrderByPosition(newColumn);
        if (request.getNewPosition() < 0 || request.getNewPosition() > newTasks.size())
            return ApiResponse.error(ApiError.badRequest("Invalid position"));
        newTasks.add(request.getNewPosition(), task);
        task.setColumn(newColumn);
        for (int i = 0; i < newTasks.size(); i++) newTasks.get(i).setPosition(i);
        taskRepo.saveAll(oldTasks);
        taskRepo.saveAll(newTasks);
        return ApiResponse.success(null);
    }

    @Override
    public ApiResponse<Void> deleteById(long id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null) return ApiResponse.error(ApiError.notFound("Task not found"));
        BoardColumn column = task.getColumn();
        taskRepo.delete(task);
        List<Task> tasks = taskRepo.findAllByColumnOrderByPosition(column);
        for (int i = 0; i < tasks.size(); i++) tasks.get(i).setPosition(i);
        taskRepo.saveAll(tasks);
        return ApiResponse.success(null);
    }
}