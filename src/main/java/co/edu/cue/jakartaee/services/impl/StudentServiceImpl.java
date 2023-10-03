package co.edu.cue.jakartaee.services.impl;

import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.repositories.impl.StudentRepositoryLogicImpl;
import co.edu.cue.jakartaee.services.StudentService;

import java.util.List;
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repository;

    public StudentServiceImpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Student> listar() {
        return repository.listar();
    }

    @Override
    public Student porId(Integer id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Student t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}