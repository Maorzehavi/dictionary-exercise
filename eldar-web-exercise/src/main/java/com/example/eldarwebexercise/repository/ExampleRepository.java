package com.example.eldarwebexercise.repository;

import com.example.eldarwebexercise.model.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ExampleRepository extends JpaRepository<Example, Long> {

    @Query("select e from Example e where e.entry.id = ?1")
    Optional<Example>getAllByEntryId(Long entryId);
}