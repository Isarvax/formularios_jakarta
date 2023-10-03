package co.edu.cue.jakartaee.repositories.impl;

import co.edu.cue.jakartaee.domain.model.Grade;
import co.edu.cue.jakartaee.domain.model.Student;
import co.edu.cue.jakartaee.domain.model.Subject;
import co.edu.cue.jakartaee.exceptions.UniversityException;
import co.edu.cue.jakartaee.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private Subject createSubject(ResultSet resultSet) throws SQLException {
        Subject subject=new Subject();
        subject.setId(resultSet.getInt("id"));
        subject.setName(resultSet.getString("name"));
        return subject;
    }

    @Override
    public List<Subject> listar() {
        List<Subject> s = new ArrayList<>();
        try (Statement statement=conn.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM subject")
        ){
            while (resultSet.next()) {
                Subject subject = createSubject(resultSet);
                s.add(subject);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public Subject porId(Integer id) {
        Subject subject = null;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM subject where id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = createSubject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public void guardar(Subject subject) {
        String sql = null;
        if (subject.getId() != null && subject.getId() > 0) {
            sql = "UPDATE subject SET name=? WHERE id=?";
        } else {
            sql = "INSERT INTO subject(name) VALUES(?,)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, subject.getName());
            if (subject.getId() != null && subject.getId() > 0) {
                stmt.setInt(2,subject.getId());
            }
            stmt.executeUpdate();
            if (subject.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        subject.setId(rs.getInt(1));
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
        try (PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM subject WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
