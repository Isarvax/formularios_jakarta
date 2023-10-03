package co.edu.cue.jakartaee.domain.model;

import co.edu.cue.jakartaee.domain.enums.Career;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {

    private Integer id;
    private String name;
    private Career career;

    public Student(String name, Career career) {
        this.name = name;
        this.career = career;
    }
}