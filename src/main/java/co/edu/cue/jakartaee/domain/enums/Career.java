package co.edu.cue.jakartaee.domain.enums;


import co.edu.cue.jakartaee.exceptions.UniversityException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Career {
    SOFTWARE("Software"),
    CIVIL("Civil"),
    INDUSTRIAL("Industrial"),
    ADMINISTRACION("Administracion")
    ;

    Career(String value) {
        this.value = value;
    }

    private String value;

    public static Career fromValue(String value) {
        return Arrays.stream(Career.values()).filter(e->value.equalsIgnoreCase(e.value)).findFirst()
                .orElseThrow(()->new UniversityException("Invalid value"));
    }
}
