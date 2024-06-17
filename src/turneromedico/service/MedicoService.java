package turneromedico.service;

import turneromedico.dao.MedicoDAOImpl;
import turneromedico.model.Medico;

import java.util.List;

public class MedicoService {
    private MedicoDAOImpl medicoDAO;

    public MedicoService() {
        this.medicoDAO = new MedicoDAOImpl();
    }

    public void crear(Medico medico) {
        medicoDAO.crear(medico);
    }

    public Medico leer(Integer id) {
        return medicoDAO.leer(id);
    }

    public void actualizar(Medico medico) {
        medicoDAO.actualizar(medico);
    }

    public void eliminar(Integer id) {
        medicoDAO.eliminar(id);
    }

    public List<Medico> listar() {
        return medicoDAO.listar();
    }
}
