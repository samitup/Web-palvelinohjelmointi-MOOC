package examsandquestions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam extends AbstractPersistable<Long> {

    private String subject;

    private LocalDate examDate;
    
    @ManyToMany(mappedBy="exams")
    private List<Question> questions = new ArrayList<>();


}
