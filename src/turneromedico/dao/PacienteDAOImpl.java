package turneromedico.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import turneromedico.exceptions.DAOException;
import turneromedico.model.Paciente;

public class PacienteDAOImpl implements PacienteDAO {

    public PacienteDAOImpl() throws DAOException {
        try (Connection connection = DBManager.connect();
             Statement statement = connection.createStatement()) {
            // Crear tabla si no existe
            String sql = "CREATE TABLE IF NOT EXISTS pacientes (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "nombre VARCHAR(255)," +
                         "apellido VARCHAR(255)," +
                         "telefono VARCHAR(255)," +
                         "email VARCHAR(255)," +
                         "historia_clinica VARCHAR(255))";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DAOException("Error creating Paciente table", e);
        }
    }

    @Override
    public void crear(Paciente paciente) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "INSERT INTO pacientes (nombre, apellido, telefono, email, historia_clinica) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, paciente.getNombre());
                statement.setString(2, paciente.getApellido());
                statement.setString(3, paciente.getTelefono());
                statement.setString(4, paciente.getEmail());
                statement.setString(5, paciente.getHistoriaClinica());
                statement.executeUpdate();

                // Obtener el ID generado
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    paciente.setId(generatedKeys.getInt(1));
                }
                connection.commit();
            }
        } catch (SQLException e) {
            throw new DAOException("Error creating Paciente", e);
        }
    }

    @Override
    public Paciente leer(Integer id) throws DAOException {
        Paciente paciente = null;
        try (Connection connection = DBManager.connect()) {
            String sql = "SELECT * FROM pacientes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    paciente = new Paciente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("historia_clinica")
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error reading Paciente with ID: " + id, e);
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "UPDATE pacientes SET nombre = ?, apellido = ?, telefono = ?, email = ?, historia_clinica = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, paciente.getNombre());
                statement.setString(2, paciente.getApellido());
                statement.setString(3, paciente.getTelefono());
                statement.setString(4, paciente.getEmail());
                statement.setString(5, paciente.getHistoriaClinica());
                statement.setInt(6, paciente.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating Paciente", e);
        }
    }

    @Override
    public void eliminar(Integer id) throws DAOException {
        try (Connection connection = DBManager.connect()) {
            String sql = "DELETE FROM pacientes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting Paciente with ID: " + id, e);
        }
    }

    @Override
    public List<Paciente> listar() throws DAOException {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection connection = DBManager.connect()) {
            String sql = "SELECT * FROM pacientes";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Paciente paciente = new Paciente(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("historia_clinica")
                    );
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error listing Pacientes", e);
        }
        return pacientes;
    }
}
