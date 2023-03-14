package sh.joshlevine.warroombackend.country;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

  @Autowired
  private CountryRepository countryRepository;

  public List<Country> getCountries() {
    return countryRepository.findAll();
  }

  public Optional<Country> getCountry(Long countryId) {
    return countryRepository.findById(countryId);
  }

  public void addCountry(Country country) {
    countryRepository.save(country);
  }

  public void updateCountry(Country countryRequest) {
    Country country = countryRepository.findById(countryRequest.getId())
        .orElseThrow(() -> new IllegalStateException("country with id: " + countryRequest.getId() + ", not found"));

    // there is probably a way to map through (certain) fields and update them, but
    // this is fine for now

    // this.casualtyTotalValue = 0;
    if (countryRequest.getCasualtyTotalValue() != country.getCasualtyTotalValue()) {
      country.setCasualtyTotalValue(countryRequest.getCasualtyTotalValue());
    }
    // this.stressLevel = 0;
    if (countryRequest.getStressLevel() != country.getStressLevel()) {
      country.setStressLevel(countryRequest.getStressLevel());
    }
    // this.medalCount = 0;
    if (countryRequest.getMedalCount() != country.getMedalCount()) {
      country.setMedalCount(countryRequest.getMedalCount());
    }
    // this.consumerGoodsCount = 0;
    if (countryRequest.getConsumerGoodsCount() != country.getConsumerGoodsCount()) {
      country.setConsumerGoodsCount(countryRequest.getConsumerGoodsCount());
    }
    // this.moralePenalty = 0;
    if (countryRequest.getMoralePenalty() != country.getMoralePenalty()) {
      country.setMoralePenalty(countryRequest.getMoralePenalty());
    }

    countryRepository.save(country);
  }
}
