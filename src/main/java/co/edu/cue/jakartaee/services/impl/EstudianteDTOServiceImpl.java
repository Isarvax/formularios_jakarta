package co.edu.cue.jakartaee.services.impl;



import co.edu.cue.jakartaee.domain.model.StudentDto;
import co.edu.cue.jakartaee.services.EstudianteService;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDTOServiceImpl implements EstudianteService<StudentDto> {
    private List<StudentDto> students;

    public EstudianteDTOServiceImpl() {
        this.students = new ArrayList<StudentDto>();
        students.add(new StudentDto("1","Andres","toroide@gmail.com","IV"));
        students.add(new StudentDto("2","Katerin","batman@gmail.com","III"));
        students.add(new StudentDto("3","Isabella","isaad@gmail.com","I"));
    }

    @Override
    public List<StudentDto> listar() {
        return students;
    }
}
