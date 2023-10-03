package co.edu.cue.jakartaee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Subject {
    private Integer id;
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
