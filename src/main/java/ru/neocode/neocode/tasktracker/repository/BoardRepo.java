package ru.neocode.neocode.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.tasktracker.entity.Board;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> {
}