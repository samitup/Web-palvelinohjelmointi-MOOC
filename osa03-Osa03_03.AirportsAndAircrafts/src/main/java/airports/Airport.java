package airports;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport extends AbstractPersistable<Long> {

    private String identifier;
    private String name;
    @OneToMany(mappedBy="airport") 
    private List<Aircraft> aircrafts = new ArrayList<>();
}
