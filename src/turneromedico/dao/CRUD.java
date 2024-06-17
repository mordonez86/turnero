package turneromedico.dao;

import java.util.List;

public interface CRUD<T> {
    void crear(T t);
    T leer(Integer id);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> listar();
}
