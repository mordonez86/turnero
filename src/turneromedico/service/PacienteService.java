package turneromedico.service;

import turneromedico.dao.PacienteDAOImpl;
import turneromedico.model.Paciente;

import java.util.List;

public class PacienteService {
    private PacienteDAOImpl pacienteDAO;

    public PacienteService() {
        this.pacienteDAO = new PacienteDAOImpl();
    }

    public void crear(Paciente paciente) {
        pacienteDAO.crear(paciente);
    }

    public Paciente leer(Integer id) {
        return pacienteDAO.leer(id);
    }

    public void actualizar(Paciente paciente) {
        pacienteDAO.actualizar(paciente);
    }

    public void eliminar(Integer id) {
        pacienteDAO.eliminar(id);
    }

    public List<Paciente> listar() {
        return pacienteDAO.listar();
    }
}
