package co.edu.cue.jakartaee.services;

import co.edu.cue.jakartaee.domain.model.Grade;


import java.util.List;

public interface GradeService {
    List<Grade> listar();

    Grade porId(Integer id);

    void guardar(Grade t);

    void eliminar(Long id);
}
