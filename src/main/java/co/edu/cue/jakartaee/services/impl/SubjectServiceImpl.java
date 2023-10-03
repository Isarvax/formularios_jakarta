package co.edu.cue.jakartaee.services.impl;

import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.domain.model.Subject;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.repositories.impl.SubjectRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepositoryLogicImpl repository;

    public SubjectServiceImpl(SubjectRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Subject> listar() {
        return repository.listar();
    }

    @Override
    public Subject porId(Integer id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Subject t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
