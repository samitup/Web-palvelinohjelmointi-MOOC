package persondatabase;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 *
 * @author tuppu
 */
public class Person extends AbstractPersistable<Long>{
    private String name;
    
}
