package namesandaddresses;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = {"address"})
    List<Person> findAll();
}
