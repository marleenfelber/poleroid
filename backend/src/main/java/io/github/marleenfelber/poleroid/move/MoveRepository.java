package io.github.marleenfelber.poleroid.move;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {}
/* interface only describes what methods there are, not how they work;
   interface inherits all methods from JpaRepository;
   Long because id is type Long */