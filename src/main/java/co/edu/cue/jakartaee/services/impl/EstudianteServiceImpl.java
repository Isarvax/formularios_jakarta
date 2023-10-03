package co.edu.cue.jakartaee.services.impl;


import co.edu.cue.jakartaee.domain.model.Estudiante;
import co.edu.cue.jakartaee.services.EstudianteService;

import java.util.ArrayList;
import java.util.List;

public class EstudianteServiceImpl implements EstudianteService<Estudiante> {

    private List<Estudiante> estudiantes;

    public EstudianteServiceImpl() {
        this.estudiantes = new ArrayList<Estudiante>();
        estudiantes.add(new Estudiante("1","Andres","Ingenieria de software"));
        estudiantes.add(new Estudiante("2","Katerin","Artes y diseño grafico"));
        estudiantes.add(new Estudiante("3","Isabella","Medicina"));
    }
    @Override
    public List<Estudiante> listar() {
        return estudiantes;
    }
}
