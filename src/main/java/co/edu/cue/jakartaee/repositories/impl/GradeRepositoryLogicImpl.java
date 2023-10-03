package co.edu.cue.jakartaee.repositories.impl;

import co.edu.cue.jakartaee.domain.model.Grade;
import co.edu.cue.jakartaee.exceptions.UniversityException;
import co.edu.cue.jakartaee.repositories.Repository;
import java.sql.*;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryLogicImpl implements Repository<Grade> {
    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private Grade createGrade(ResultSet resultSet) throws SQLException {
        Grade grade=new Grade();
        grade.setId(resultSet.getInt("id"));
        grade.setGrade(resultSet.getInt("grade"));
        return grade;
    }

    @Override
    public List<Grade> listar() {
        List<Grade> grades = new ArrayList<>();
        try (Statement statement=conn.createStatement();
             ResultSet resultSet=statement.executeQuery("SELECT * FROM grade")
        ){
            while (resultSet.next()) {
                Grade grade = createGrade(resultSet);
                grades.add(grade);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    @Override
    public Grade porId(Integer id) {
        Grade grade = null;
        try (PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM grade where id=?")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                grade = createGrade(resultSet);
            }
            resultSet.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public void guardar(Grade grade) {
        String sql = null;
        if (grade.getId() != null && grade.getId() > 0) {
            sql = "UPDATE grade SET grade=? WHERE id=?";
        } else {
            sql = "INSERT INTO grade(grade) VALUES(?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, grade.getGrade());
            if (grade.getId() != null && grade.getId() > 0) {
                stmt.setInt(2,grade.getId());
            }
            stmt.executeUpdate();
            if (grade.getId() == null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        grade.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(Long id) {
        Grade g = null;
        try (PreparedStatement preparedStatement=conn.prepareStatement("DELETE FROM grade WHERE id =?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
