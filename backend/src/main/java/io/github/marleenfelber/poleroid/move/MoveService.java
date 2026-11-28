package io.github.marleenfelber.poleroid.move;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoveService {
    
    // service needs repository
    private final MoveRepository moveRepository;

    /* Dependency Injection = Service doesn't create repository but tells the constructor it needs one.
       → Spring automatically passes it to the service. */
    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    @Transactional(readOnly = true) // keeps database connection open until method ends, so categories & aliases are only fetched from database when toDto uses them
    public List<MoveDto> findAll() {
        return moveRepository.findAll() // get all moves
                .stream() // one element after the other...
                .map(this::toDto) // pass every single move through toDto
                .toList(); // collect all as list
    }

    // finds a move by its id; throws MoveNotFoundException if it doesn't exist
    @Transactional(readOnly = true)
    public MoveDto findById(Long id) {
        Move move = moveRepository.findById(id)
                .orElseThrow(() -> new MoveNotFoundException(id));
        
        return toDto(move);
    }

    // creates a new move from the request and saves it in the database
    @Transactional // writes to database instead of only reading; if one part fails, nothing is saved
    public MoveDto create(MoveRequest request) {
        Move move = new Move(request.name(), request.poleMode(), request.level()); // new move with the required fields (record getters have no "get")
        applyOptionalFields(move, request); // optional: set description, categories & aliases
        Move saved = moveRepository.save(move); // save in database (INSERT); saved move now has an id
        return toDto(saved); // return it as a DTO, including the new id
    }

    // sets description, categories & aliases from the request (all optional); used by CREATE and UPDATE
    private void applyOptionalFields(Move move, MoveRequest request) {
        move.setDescription(request.description()); // take over the description sent from frontend (can be null, it's optional)

        move.getCategories().clear(); // remove old categories (important when editing a move)
        if (request.categories() != null) { // only if frontend sent categories (optional as it could be null)
            move.getCategories().addAll(request.categories()); // add all new categories
        }

        move.getAliases().clear();
        if (request.aliases() != null) {
            move.getAliases().addAll(request.aliases());
        }
    }


    /* Converts a Move (entity) into a MoveDto ("finished plate").
       Takes each value from the entity with its getter and puts it into the new DTO.
       Order must be the same as the fields in the MoveDto record.
       private: only used inside this service. */
    private MoveDto toDto(Move move) {
        return new MoveDto(
                move.getId(),
                move.getName(),
                move.getPoleMode(),
                move.getLevel(),
                move.getDescription(),
                Set.copyOf(move.getCategories()),
                Set.copyOf(move.getAliases())
        );
    }
}