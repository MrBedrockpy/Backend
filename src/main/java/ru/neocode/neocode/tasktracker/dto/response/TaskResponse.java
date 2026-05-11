package ru.neocode.neocode.tasktracker.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.neocode.neocode.tasktracker.entity.Task;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskResponse {

    private final long id;
    private final String title;
    private final String description;
    private final Integer position;

    @JsonIgnore private BoardColumnResponse column;

    public static TaskResponse from(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getPosition());
    }

}