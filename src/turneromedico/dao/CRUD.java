package turneromedico.dao;

import java.util.List;
import turneromedico.exceptions.DAOException;

public interface CRUD<T> {
    void crear(T t) throws DAOException;
    T leer(Integer id) throws DAOException;
    void actualizar(T t) throws DAOException;
    void eliminar(Integer id) throws DAOException;
    List<T> listar() throws DAOException;
}