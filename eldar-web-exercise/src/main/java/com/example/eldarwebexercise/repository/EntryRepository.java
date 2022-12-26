package com.example.eldarwebexercise.repository;

import com.example.eldarwebexercise.model.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Query("select e.id from Entry e where e.value = ?1")
    Optional<Long> getIdByValue(String value);

    Boolean existsByValue(String value);
}