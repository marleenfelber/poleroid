package io.github.marleenfelber.poleroid.move;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;


/* Request DTO = Data the frontend sends to create or update a move.
   It has no id (database assigns it) and the rules (@...) check that the input is correct. */
public record MoveRequest(
    @NotBlank @Size(max = 100) String name, // @NotBlank = NOT NULL (texts)
    @NotNull PoleMode poleMode, // @NotNull = NOT NULL (enums)
    @NotNull Level level, // @NotNull = NOT NULL (enums)
    String description,
    Set<Category> categories,
    Set<String> aliases
) {}