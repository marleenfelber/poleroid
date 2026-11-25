package io.github.marleenfelber.poleroid.move;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "move")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // fits "GENERATED ALWAYS AS IDENTITY", database assigns the id
    private Long id; // Long is Java type that suits BIGINT, L because id can be null

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Enumerated(EnumType.STRING) // stores enum as text, not as its position
    @Column(name = "pole_mode", nullable = false, length = 20) // SQL uses snake_case (pole_mode), poleMode ≠ pole_mode
    private PoleMode poleMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) // no name needed: field and column both are called "level"
    private Level level;

    @Column(columnDefinition = "TEXT")
    private String description;
}