package com.example.parcial.parcial2.repositories;

import com.example.parcial.parcial2.domain.entities.Book;
import com.example.parcial.parcial2.domain.entities.Lector;
import com.example.parcial.parcial2.domain.entities.Movement;
import com.example.parcial.parcial2.domain.entities.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovementRepository extends JpaRepository<Movement, UUID> {
    @Query(value = "SELECT COUNT(m) FROM Movement m WHERE m.lector = ?1 and m.book = ?2 and m.type = ?3")
    public long CountMovementsByLectorAndBookAndType(Lector lector, Book book, MovementType type);
}
