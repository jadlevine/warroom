package sh.joshlevine.warroombackend.country;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

  // @Query("SELECT c FROM Country c Where c.id = ?1")
  // Optional<Country> findCountryById(Long id);
  // join fetch below explicity instructs JPQL to join fetch the records in ONE
  // action
  @Query("select c from Country c join fetch c.game where c.id = ?1")
  public Optional<Country> findById(Long id);
  // public Country findCountryById(Long id);
}

/*
 * Also have access to JpaRespository's methods: save(), findOne(), findById(),
 * findAll(), count(), delete(), deleteById() ... without explicitly
 * implementing them
 */