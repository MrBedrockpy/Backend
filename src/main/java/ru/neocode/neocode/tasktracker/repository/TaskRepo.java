package ru.neocode.neocode.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.tasktracker.entity.BoardColumn;
import ru.neocode.neocode.tasktracker.entity.Task;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByColumnOrderByPosition(BoardColumn column);

}