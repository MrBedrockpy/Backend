package ru.neocode.neocode.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.tasktracker.entity.Board;
import ru.neocode.neocode.tasktracker.entity.BoardColumn;

import java.util.List;

@Repository
public interface BoardColumnRepo extends JpaRepository<BoardColumn, Long> {

    List<BoardColumn> findAllByBoardOrderByPosition(Board board);

}