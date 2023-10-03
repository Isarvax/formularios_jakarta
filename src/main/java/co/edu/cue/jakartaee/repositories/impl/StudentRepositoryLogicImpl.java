package co.edu.cue.jakartaee.repositories.impl;

import co.edu.cue.jakartaee.domain.enums.Career;
import co.edu.cue.jakartaee.domain.model.Grade;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.exceptions.UniversityException;
import co.edu.cue.jakartaee.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<Student> {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    private List<Student> students;

    private Student createStudent(ResultSet resultSet) throws SQLException {
        Student student=new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setCareer(Career.fromValue(resultSet.getString("career")));
        return student;
    }

    @Override
    public List<Student> listar() {
        List<Student> s = new ArrayList<>();
        try (Statement statement=conn.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM student")
        ){
            while (resultSet.next()) {
                Student grade = createStudent(resultSet);
                s.add(grade);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Student porId(Integer id) {
        Student student = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM student where id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudent(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void guardar(Student student) {
        String sql = null;
        if (student.getId() != null && student.getId() > 0) {
            sql = "UPDATE student SET name=?,career=? WHERE id=?";
        } else {
            sql = "INSERT INTO student(name,career) VALUES(?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, student.getName());
            stmt.setString(2,student.getCareer().getValue());
            if (student.getId() != null && student.getId() > 0) {
                stmt.setInt(3,student.getId());
            }
            stmt.executeUpdate();
            if (student.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        student.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        Student g = null;
        try (PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM student WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}