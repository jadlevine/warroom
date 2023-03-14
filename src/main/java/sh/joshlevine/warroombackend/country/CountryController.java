package sh.joshlevine.warroombackend.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/warroom")
public class CountryController {
  @Autowired
  private CountryService countryService;

  @GetMapping(path = "/countries")
  public List<Country> getCountries() {
    return countryService.getCountries();
  }

  @GetMapping(path = "/countries/{countryId}")
  public Optional<Country> getCountry(@PathVariable("countryId") Long countryId) {
    return countryService.getCountry(countryId);
  }

  // NO need for @PostMapping, because the only time we will create a country is
  // when a game is initialized

  @PutMapping(path = "/countries/update")
  public void updateCountry(@RequestBody Country countryRequest) {
    countryService.updateCountry(countryRequest);
  }

}
