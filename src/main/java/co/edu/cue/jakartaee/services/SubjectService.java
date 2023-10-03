package co.edu.cue.jakartaee.services;

import co.edu.cue.jakartaee.domain.model.Subject;
import co.edu.cue.jakartaee.domain.model.Teacher;

import java.util.List;

public interface SubjectService {
    List<Subject> listar();

    Subject porId(Integer id);

    void guardar(Subject t);

    void eliminar(Long id);
}
