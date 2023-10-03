package co.edu.cue.jakartaee.services.impl;

import co.edu.cue.jakartaee.domain.model.Teacher;
import co.edu.cue.jakartaee.repositories.impl.TeacherRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryLogicImpl repository;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Teacher> listar() {
        return repository.listar();
    }

    @Override
    public Teacher porId(Integer id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Teacher t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
