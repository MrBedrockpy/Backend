package ru.neocode.neocode.tasktracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoveTaskRequest {

    private long taskId;
    private long newColumnId;
    private int newPosition;

}
