package persondatabase;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author tuppu
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    
}
