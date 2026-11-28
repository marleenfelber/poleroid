package io.github.marleenfelber.poleroid.move;

///Exception in case "this move doesn't exist".
public class MoveNotFoundException extends RuntimeException { // inherits everything an exception needs (message, stack trace, throw)
                                                              // RuntimeException: no try/catch or "throws" needed everywhere

    // constructor gets the id that wasn't found
    public MoveNotFoundException(Long id) {
        super("Move with id " + id + " not found."); // super: passes the error message to the parent class (RuntimeException)
    }
}

/* usage in service: throw new MoveNotFoundException(99L);
   → message: "Move with id 99 not found." */