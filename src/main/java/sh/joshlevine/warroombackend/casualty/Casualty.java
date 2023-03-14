package sh.joshlevine.warroombackend.casualty;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import sh.joshlevine.warroombackend.country.Country;

@Setter
@Getter
@Entity
@Table(name = "casualty")
public class Casualty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String unitType;
  private Integer value;
  private Integer roundOccurred;

  @JsonBackReference
  @ManyToOne
  private Country country;

  public Casualty() {
  }

  public Casualty(String unitType, Integer roundOccurred) {
    this.roundOccurred = roundOccurred;
    this.unitType = unitType;
    this.value = determineCasualtyValue();
  }

  private Integer determineCasualtyValue() {
    if (unitType.equals("Infantry") || unitType.equals("Artillery")) {
      return 2;
    } else if (unitType.equals("Armor") || unitType.equals("Fighter-Plane")) {
      return 4;
    } else if (unitType.equals("Bomber-Plane") || unitType.equals("Submarine")) {
      return 6;
    } else if (unitType.equals("Cruiser")) {
      return 10;
    } else if (unitType.equals("Aircraft-Carrier") || unitType.equals("Battleship")) {
      return 20;
    } else {
      throw new IllegalStateException("Invalid casualty unitType");
    }
  }

  @Override
  public String toString() {
    return "Casualty [id=" + id + ", unitType=" + unitType + ", value=" + value + ", roundOccurred=" + roundOccurred
        + ", country=" + country + "]";
  }

}