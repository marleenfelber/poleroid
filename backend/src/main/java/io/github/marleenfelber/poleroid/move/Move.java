package io.github.marleenfelber.poleroid.move;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "move")
public class Move {
    // move id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // fits "GENERATED ALWAYS AS IDENTITY", database assigns the id
    private Long id; // Long is Java type that suits BIGINT, L because id can be null

    // move name
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    // pole mode
    @Enumerated(EnumType.STRING) // stores enum as text, not as its position
    @Column(name = "pole_mode", nullable = false, length = 20) // SQL uses snake_case (pole_mode), poleMode ≠ pole_mode
    private PoleMode poleMode;

    // level of move
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20) // no name needed: field and column both are called "level"
    private Level level;

    // move description
    @Column(columnDefinition = "TEXT")
    private String description;

    // categories of move
    @ElementCollection // collection of simple values like text, enums, etc.
    @CollectionTable(name = "move_category", joinColumns = @JoinColumn(name = "move_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 30)
    private Set<Category> categories = new HashSet<>(); // a collection of categories without duplicates, set starts empty instead of null

    // aliases of move
    @ElementCollection
    @CollectionTable(name = "move_alias", joinColumns = @JoinColumn(name = "move_id"))
    @Column(name = "alias", nullable = false, length = 100)
    private Set<String> aliases = new HashSet<>(); // a collection of aliases without duplicates, set starts empty instead of null; no separate Alias class
}