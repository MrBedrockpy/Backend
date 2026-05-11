package ru.neocode.neocode.tasktracker.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RenameColumnRequest {

    private long columnId;
    private String newTitle;

}