package sh.joshlevine.warroombackend.game;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/warroom")
public class GameController {

  @Autowired
  private GameService gameService;

  @GetMapping(path = "/games")
  public List<Game> getGames() {
    return gameService.getGames();
  }

  @GetMapping(path = "/games/{gameId}")
  public Optional<Game> getGame(@PathVariable("gameId") Long gameId) {
    return gameService.getGame(gameId);
  }

  @PostMapping(path = "/games")
  public void addGame(@RequestBody Game game) {
    Game newGame = new Game(game.getName(), game.getScenario());
    gameService.addNewGame(newGame);
  }

  @DeleteMapping(path = "/games/{gameId}")
  public void deleteGame(@PathVariable("gameId") Long gameId) {
    gameService.deleteGame(gameId);
  }

  @PutMapping(path = "/games/update")
  public void updateGame(@RequestBody Game gameRequest) {
    gameService.updateGame(gameRequest);
  }

}
