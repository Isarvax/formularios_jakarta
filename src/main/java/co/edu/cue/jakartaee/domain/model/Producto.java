package co.edu.cue.jakartaee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Producto {
    private String id;
    private String nombre;
    private String tipo;
    private Double precio;
}
