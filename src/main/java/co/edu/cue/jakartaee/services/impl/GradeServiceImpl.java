package co.edu.cue.jakartaee.services.impl;

import co.edu.cue.jakartaee.domain.model.Grade;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.repositories.impl.GradeRepositoryLogicImpl;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {

    private final GradeRepositoryLogicImpl repository;

    public GradeServiceImpl(GradeRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Grade> listar() {
        return repository.listar();
    }

    @Override
    public Grade porId(Integer id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Grade t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
