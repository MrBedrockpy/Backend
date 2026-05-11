package ru.neocode.neocode.tasktracker.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.neocode.neocode.tasktracker.entity.BoardColumn;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BoardColumnResponse {

    private final long id;
    private final String name;
    private final Integer position;

    @JsonIgnore private BoardResponse board;
    private final List<TaskResponse> tasks;

    public static BoardColumnResponse from(BoardColumn column) {
        List<TaskResponse> tasks = column.getTasks().stream().map(TaskResponse::from).toList();
        BoardColumnResponse columnResponse = new BoardColumnResponse(column.getId(), column.getName(), column.getPosition(), tasks);
        tasks.forEach(task -> task.setColumn(columnResponse));
        return columnResponse;
    }
}
