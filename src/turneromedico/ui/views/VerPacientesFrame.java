package turneromedico.ui.views;

import turneromedico.model.Paciente;
import turneromedico.service.PacienteService;

import java.util.List;

public class VerPacientesFrame extends VerEntidadesFrame<Paciente> {
    private PacienteService pacienteService;

    public VerPacientesFrame() {
        super("Lista de Pacientes", new String[]{"ID", "Nombre", "Apellido", "Teléfono", "Email", "Historia Clínica"}, new PacienteService().listar());
        pacienteService = new PacienteService();
    }

    @Override
    protected Object[][] getData(List<Paciente> pacientes) {
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
}
