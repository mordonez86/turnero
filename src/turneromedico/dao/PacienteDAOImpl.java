package turneromedico.dao;

import turneromedico.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements PacienteDAO {
    private String url = "jdbc:h2:~/turneromedico";  // URL de la base de datos H2
    private String user = "sa";  // Usuario por defecto de H2
    private String password = "";  // Contraseña por defecto de H2

    static {
        try {
            // Registrar el controlador H2
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PacienteDAOImpl() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
            e.printStackTrace();
        }
    }

    @Override
    public void crear(Paciente paciente) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente leer(Integer id) {
        Paciente paciente = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM pacientes WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
            e.printStackTrace();
        }
        return pacientes;
    }
}
