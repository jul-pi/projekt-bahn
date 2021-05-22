package railwaystationdb;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository containing objects of type {@link RailwayStation}.
 * It uses a unique abbreviation String for the object as the ID.
 */
@Repository
public interface RailwayStationRepository extends CrudRepository<RailwayStation, String> { 
}
