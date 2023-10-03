package co.edu.cue.jakartaee.services;

import co.edu.cue.jakartaee.domain.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listar();

    Student porId(Integer id);

    void guardar(Student t);

    void eliminar(Long id);
}