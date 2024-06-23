package turneromedico.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import turneromedico.exceptions.DAOException;
import turneromedico.model.Medico;

public class MedicoDAOImpl implements MedicoDAO {

    public MedicoDAOImpl() throws DAOException {
        try (Connection connection = DBManager.connect();
             Statement statement = connection.createStatement()) {
            // Crear tabla si no existe
            String sql = "CREATE TABLE IF NOT EXISTS medicos (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "nombre VARCHAR(255)," +
                         "apellido VARCHAR(255)," +
                         "telefono VARCHAR(255)," +
                         "email VARCHAR(255)," +
                         "especialidad VARCHAR(255))";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DAOException("Error creating Medico table", e);
        }
    }

    @Override
    public void crear(Medico medico) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "INSERT INTO medicos (nombre, apellido, telefono, email, especialidad) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, medico.getNombre());
                statement.setString(2, medico.getApellido());
                statement.setString(3, medico.getTelefono());
                statement.setString(4, medico.getEmail());
                statement.setString(5, medico.getEspecialidad());
                statement.executeUpdate();

                // Obtener el ID generado
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    medico.setId(generatedKeys.getInt(1));
                }
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating Medico", e);
        }
    }

    @Override
    public Medico leer(Integer id) throws DAOException {
        Medico medico = null;
        try (Connection connection = DBManager.connect()) {
            String sql = "SELECT * FROM medicos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    medico = new Medico(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("especialidad")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error reading Medico with ID: " + id, e);
        }
        return medico;
    }

    @Override
    public void actualizar(Medico medico) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "UPDATE medicos SET nombre = ?, apellido = ?, telefono = ?, email = ?, especialidad = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, medico.getNombre());
                statement.setString(2, medico.getApellido());
                statement.setString(3, medico.getTelefono());
                statement.setString(4, medico.getEmail());
                statement.setString(5, medico.getEspecialidad());
                statement.setInt(6, medico.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating Medico", e);
        }
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "DELETE FROM medicos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting Medico with ID: " + id, e);
        }
    }

    @Override
    public List<Medico> listar() throws DAOException {
        List<Medico> medicos = new ArrayList<>();
        try (Connection connection = DBManager.connect()) {
            String sql = "SELECT * FROM medicos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Medico medico = new Medico(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("especialidad")
                    );
                    medicos.add(medico);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error listing Medicos", e);
        }
        return medicos;
    }
}
