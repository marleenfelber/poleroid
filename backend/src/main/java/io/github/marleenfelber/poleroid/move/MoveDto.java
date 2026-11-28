package io.github.marleenfelber.poleroid.move;

import java.util.Set;

/* DTO (Data Transfer Object) = The "finished" plate the backend sends to the frontend.
   Move entity isn't sent as it's bound to the database (Hibernate).
   record = type of class only for data; Java automatically creates constructors, getters (dto.name()), equals, hashCode & toString.
   Values can't be changed. */
public record MoveDto(
    Long id,
    String name,
    PoleMode poleMode,
    Level level,
    String description,
    Set<Category> categories,
    Set<String> aliases
) {}