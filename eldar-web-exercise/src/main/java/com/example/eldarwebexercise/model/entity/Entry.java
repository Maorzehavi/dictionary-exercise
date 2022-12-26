package com.example.eldarwebexercise.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "entries")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private String definition;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<Example> examples;



}
