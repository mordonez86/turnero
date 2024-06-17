package turneromedico.ui.views;

import turneromedico.model.Medico;
import turneromedico.service.MedicoService;

import java.util.List;

public class VerMedicosFrame extends VerEntidadesFrame<Medico> {
    private MedicoService medicoService;

    public VerMedicosFrame() {
        super("Lista de Médicos", new String[]{"ID", "Nombre", "Apellido", "Teléfono", "Email", "Especialidad"}, new MedicoService().listar());
        medicoService = new MedicoService();
    }

    @Override
    protected Object[][] getData(List<Medico> medicos) {
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
}
