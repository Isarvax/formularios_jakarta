package co.edu.cue.jakartaee.repositories;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    void setConn(Connection conn) throws SQLException;
    List<T> listar();

    T porId(Integer id);

    void guardar(T t);

    void eliminar(Long id);
}