package sh.joshlevine.warroombackend.casualty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CasualtyRepository extends JpaRepository<Casualty, Long> {
  @Query("select cas from Casualty cas join fetch cas.country where cas.id = ?1")
  public Optional<Casualty> findById(Long id);

  public Optional<Casualty> findTopByCountryIdAndUnitTypeOrderByIdDesc(Long countryId, String unitType);

}
