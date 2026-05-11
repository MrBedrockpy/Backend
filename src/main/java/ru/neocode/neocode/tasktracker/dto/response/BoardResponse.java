package ru.neocode.neocode.tasktracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.neocode.neocode.tasktracker.entity.Board;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardResponse {

    private final long id;
    private final String title;
    private final List<BoardColumnResponse> columns;

    public static BoardResponse from(Board board) {
        List<BoardColumnResponse> columns = board.getColumns().stream().map(BoardColumnResponse::from).toList();
        BoardResponse boardResponse = new BoardResponse(board.getId(), board.getTitle(), columns);
        columns.forEach(column -> column.setBoard(boardResponse));
        return boardResponse;
    }

}
