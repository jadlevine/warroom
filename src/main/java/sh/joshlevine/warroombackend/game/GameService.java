package sh.joshlevine.warroombackend.game;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private GameRepository gameRepository;

  public List<Game> getGames() {
    return gameRepository.findAll();
  }

  public Optional<Game> getGame(Long gameId) {
    return gameRepository.findById(gameId);
  }

  public void addNewGame(Game game) {
    gameRepository.save(game);
  }

  public void deleteGame(Long gameId) {
    boolean exists = gameRepository.existsById(gameId);
    if (!exists) {
      throw new IllegalStateException("game with id " + gameId + " does not exist");
    }
    gameRepository.deleteById(gameId);
  }

  // update a game
  // @Transactional, @Transient?
  public void updateGame(Game gameRequest) {
    Game game = gameRepository.findById(gameRequest.getId())
        .orElseThrow(() -> new IllegalStateException("game with id: " + gameRequest.getId() + ", not found"));

    // this.roundNum = 1;
    if (gameRequest.getRoundNum() != game.getRoundNum()) {
      game.setRoundNum(gameRequest.getRoundNum());
    }
    // this.battlePhase = true;
    if (gameRequest.getBattlePhase() != game.getBattlePhase()) {
      game.setBattlePhase(gameRequest.getBattlePhase());
    }
    // this.moralePhase = false;
    if (gameRequest.getMoralePhase() != game.getMoralePhase()) {
      game.setMoralePhase(gameRequest.getMoralePhase());
    }

    gameRepository.save(game);
  }

}
