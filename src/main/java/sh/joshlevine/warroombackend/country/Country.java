package sh.joshlevine.warroombackend.country;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import sh.joshlevine.warroombackend.casualty.Casualty;
import sh.joshlevine.warroombackend.game.Game;

@Setter
@Getter
@Entity
@Table(name = "country")
public class Country {
  @Id
  // @SequenceGenerator(name = "country_seq", sequenceName = "country_seq",
  // allocationSize = 1)
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
  // "country_seq")
  // @Column(name = "countryId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Integer casualtyTotalValue;
  private Integer stressLevel;
  private Integer medalCount;
  private Integer consumerGoodsCount;
  private Integer moralePenalty;
  private Integer moraleTriggerPoint;
  private Integer gameIndex;

  @JsonBackReference // prevents the infinite loop when looking up countries and games. Necessary for
                     // bidirectional oneToMany
  // // @ManyToOne(cascade = CascadeType.ALL)
  // // @ManyToOne(fetch = FetchType.LAZY)
  // // or, possibly...
  // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

  @ManyToOne
  // @JoinColumn(name = "game_id", nullable = false)
  private Game game;

  @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
  private List<Casualty> casualties = new ArrayList<>();

  public void addCasualtyToCountry(Casualty casualty) {
    casualty.setCountry(this);
    casualties.add(casualty);
    this.casualtyTotalValue += casualty.getValue();
  }

  public void removeCasualty(Casualty casualty) {
    this.casualtyTotalValue -= casualty.getValue();
  }

  public Country() {
  }

  public Country(String name, Integer moraleTriggerPoint, Integer gameIndex) {
    this.name = name;
    this.casualtyTotalValue = 0;
    this.stressLevel = 0;
    this.medalCount = 0;
    this.consumerGoodsCount = 0;
    this.moralePenalty = 0;
    this.moraleTriggerPoint = moraleTriggerPoint;
    this.gameIndex = gameIndex;
  }

  @Override
  public String toString() {
    return "Country [id=" + id + ", name=" + name + ", casualtyTotalValue=" + casualtyTotalValue + ", stressLevel="
        + stressLevel
        + ", medalCount=" + medalCount + ", consumerGoodsCount=" + consumerGoodsCount + ", moralePenalty="
        + moralePenalty + ", moraleTriggerPoint=" + moraleTriggerPoint + ", game=" + game + ", casualties=" + casualties
        + "]";
  }

}
