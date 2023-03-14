package sh.joshlevine.warroombackend.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import sh.joshlevine.warroombackend.country.Country;

@Setter
@Getter
@Entity
@Table(name = "game")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // just let database come up with unique id using its own method
  private Long id;

  private String name;
  private LocalDateTime date;
  private String scenario;
  private Integer roundNum;
  private Boolean battlePhase;
  private Boolean moralePhase;

  @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST) // so when a game is saved, both the game and all country
                                                               // entities will be persisted to the database
  // @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
  // @OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST,
  // CascadeType.MERGE, CascadeType.DETACH,
  // CascadeType.REFRESH })
  // @OneToMany
  // @JoinColumn(name = "game_id")
  @OrderBy("id ASC") // ensures country list sorted when fetched
  private List<Country> countries = new ArrayList<>();

  public void addCountryToGame(Country country) {
    country.setGame(this);
    countries.add(country);
  }

  public Game() {
  }

  public Game(String name, String scenario) {
    this.name = name;
    this.scenario = scenario;
    this.date = LocalDateTime.now();
    this.roundNum = 1;
    this.battlePhase = true;
    this.moralePhase = false;
    buildCountryList();
  }

  private void buildCountryList() {
    if (scenario.equals("Global")) {
      Country china = new Country("China", 4, 1);
      this.addCountryToGame(china);
      Country usa = new Country("USA", 5, 2);
      this.addCountryToGame(usa);
      Country uk = new Country("UK", 6, 3);
      this.addCountryToGame(uk);
      Country ussr = new Country("USSR", 6, 4);
      this.addCountryToGame(ussr);
      Country germany = new Country("Germany", 6, 5);
      this.addCountryToGame(germany);
      Country italy = new Country("Italy", 4, 6);
      this.addCountryToGame(italy);
      Country japan = new Country("Japan", 7, 7);
      this.addCountryToGame(japan);
      // return new ArrayList<Country>(List.of(china, usa, uk, ussr, germany, italy,
      // japan));
    } else if (scenario.equals("War In Europe")) {
      Country usa = new Country("USA", 4, 1);
      this.addCountryToGame(usa);
      Country uk = new Country("UK", 4, 2);
      this.addCountryToGame(uk);
      Country ussr = new Country("USSR", 5, 3);
      this.addCountryToGame(ussr);
      Country germany = new Country("Germany", 6, 4);
      this.addCountryToGame(germany);
      Country italy = new Country("Italy", 4, 5);
      this.addCountryToGame(italy);
      // return new ArrayList<Country>(List.of(usa, uk, ussr, germany, italy));
    } else if (scenario.equals("Eastern Front")) {
      Country ussr = new Country("USSR", 6, 1);
      this.addCountryToGame(ussr);
      Country germany = new Country("Germany", 6, 2);
      this.addCountryToGame(germany);
      // return new ArrayList<Country>(List.of(ussr, germany));
    } else if (scenario.equals("Pacific")) {
      Country china = new Country("China", 4, 1);
      this.addCountryToGame(china);
      Country usa = new Country("USA", 4, 2);
      this.addCountryToGame(usa);
      Country uk = new Country("UK", 4, 3);
      this.addCountryToGame(uk);
      Country japan = new Country("Japan", 6, 4);
      this.addCountryToGame(japan);
      // return new ArrayList<Country>(List.of(china, usa, uk, japan));
    } else if (scenario.equals("North Africa")) {
      Country usa = new Country("USA", 4, 1);
      this.addCountryToGame(usa);
      Country uk = new Country("UK", 4, 2);
      this.addCountryToGame(uk);
      Country germany = new Country("Germany", 6, 3);
      this.addCountryToGame(germany);
      Country italy = new Country("Italy", 4, 4);
      this.addCountryToGame(italy);
      // return new ArrayList<Country>(List.of(usa, uk, germany, italy));
    } else {
      throw new Error("invalid scenario selection");
    }
  }

  @Override
  public String toString() {
    return "Game [id=" + id + ", name=" + name + ", date=" + date + ", scenario=" + scenario + ", countries="
        + countries + "]";
  }

}
