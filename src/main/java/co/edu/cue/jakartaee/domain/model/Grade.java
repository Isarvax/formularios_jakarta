package co.edu.cue.jakartaee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Grade {
    private Integer id;
    private Integer grade;

    public Grade(Integer grade) {
        this.grade = grade;
    }
}
