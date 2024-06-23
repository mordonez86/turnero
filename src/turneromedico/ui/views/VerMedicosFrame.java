package turneromedico.ui.views;

import turneromedico.model.Medico;
import turneromedico.service.MedicoService;
import turneromedico.exceptions.ServiceException;

import javax.swing.*;
import java.util.List;

public class VerMedicosFrame extends VerEntidadesFrame<Medico> {
    private MedicoService medicoService;

    public VerMedicosFrame() {
        super("Lista de Médicos", new String[]{"ID", "Nombre", "Apellido", "Teléfono", "Email", "Especialidad"}, obtenerDatosIniciales());
        try {
            medicoService = new MedicoService();
        } catch (ServiceException e) {
            mostrarError("Error al inicializar el servicio de médicos: " + e.getMessage());
        }
    }

    private static Object[][] obtenerDatosIniciales() {
        try {
            MedicoService medicoService = new MedicoService();
            List<Medico> medicos = medicoService.listar();
            return convertirDatos(medicos);
        } catch (ServiceException e) {
            mostrarErrorEstatico("Error al obtener la lista de médicos: " + e.getMessage());
            return new Object[0][6];
        }
    }

    @Override
    protected Object[][] getData(List<Medico> medicos) {
        return convertirDatos(medicos);
    }

    private static Object[][] convertirDatos(List<Medico> medicos) {
        Object[][] data = new Object[medicos.size()][6];
        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            data[i][0] = medico.getId();
            data[i][1] = medico.getNombre();
            data[i][2] = medico.getApellido();
            data[i][3] = medico.getTelefono();
            data[i][4] = medico.getEmail();
            data[i][5] = medico.getEspecialidad();
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
