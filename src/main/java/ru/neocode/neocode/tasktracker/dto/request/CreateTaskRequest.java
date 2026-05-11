package ru.neocode.neocode.tasktracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    private long columnId;
    private String title;
    private String description;
    private int position;

}
