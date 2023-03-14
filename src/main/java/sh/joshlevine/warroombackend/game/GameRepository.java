package sh.joshlevine.warroombackend.game;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

  // @Query("SELECT g FROM Game g Where g.id = ?1")
  Optional<Game> findById(Long id);
  // Game getGameById(Long id);
}

/*
 * Also have access to JpaRespository's methods: save(), findOne(), findById(),
 * findAll(), count(), delete(), deleteById() ... without explicitly
 * implementing them
 */