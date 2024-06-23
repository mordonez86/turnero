package turneromedico.ui.views;

import java.util.List;
import javax.swing.*;
import turneromedico.exceptions.ServiceException;
import turneromedico.model.Paciente;
import turneromedico.service.PacienteService;

public class VerPacientesFrame extends VerEntidadesFrame<Paciente> {
    private PacienteService pacienteService;

    public VerPacientesFrame() {
        super("Lista de Pacientes", new String[]{"ID", "Nombre", "Apellido", "Teléfono", "Email", "Historia Clínica"}, obtenerDatosIniciales());
        try {
            pacienteService = new PacienteService();
        } catch (ServiceException e) {
            mostrarError("Error al inicializar el servicio de pacientes: " + e.getMessage());
        }
    }

    private static Object[][] obtenerDatosIniciales() {
        try {
            PacienteService pacienteService = new PacienteService();
            List<Paciente> pacientes = pacienteService.listar();
            return convertirDatos(pacientes);
        } catch (ServiceException e) {
            mostrarErrorEstatico("Error al obtener la lista de pacientes: " + e.getMessage());
            return new Object[0][6]; // Devuelve una matriz vacía en caso de error
        }
    }

    @Override
    protected Object[][] getData(List<Paciente> pacientes) {
        return convertirDatos(pacientes);
    }

    private static Object[][] convertirDatos(List<Paciente> pacientes) {
        Object[][] data = new Object[pacientes.size()][6];
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente paciente = pacientes.get(i);
            data[i][0] = paciente.getId();
            data[i][1] = paciente.getNombre();
            data[i][2] = paciente.getApellido();
            data[i][3] = paciente.getTelefono();
            data[i][4] = paciente.getEmail();
            data[i][5] = paciente.getHistoriaClinica();
        }
        return data;
    }

    private static void mostrarErrorEstatico(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
