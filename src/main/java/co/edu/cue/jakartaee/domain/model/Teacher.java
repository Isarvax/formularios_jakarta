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
public class Teacher {
    private Integer id;
    private String name;
    private String email;

    public Teacher(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
