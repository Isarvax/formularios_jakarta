package co.edu.cue.jakartaee.repositories.impl;

import co.edu.cue.jakartaee.domain.enums.Career;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.domain.model.Teacher;
import co.edu.cue.jakartaee.exceptions.UniversityException;
import co.edu.cue.jakartaee.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<Teacher> {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private Teacher createTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher=new Teacher();
        teacher.setId(resultSet.getInt("id"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        return teacher;
    }

    @Override
    public List<Teacher> listar() {
        List<Teacher> s = new ArrayList<>();
        try (Statement statement=conn.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM teacher")
        ){
            while (resultSet.next()) {
                Teacher teacher = createTeacher(resultSet);
                s.add(teacher);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Teacher porId(Integer id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM teacher where id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void guardar(Teacher teacher) {
        String sql = null;
        if (teacher.getId() != null && teacher.getId() > 0) {
            sql = "UPDATE teacher SET name=?,email=? WHERE id=?";
        } else {
            sql = "INSERT INTO teacher(name,email) VALUES(?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2,teacher.getEmail());
            if (teacher.getId() != null && teacher.getId() > 0) {
                stmt.setInt(3,teacher.getId());
            }
            stmt.executeUpdate();
            if (teacher.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        teacher.setId(rs.getInt(1));
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
        try (PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM teacher WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
