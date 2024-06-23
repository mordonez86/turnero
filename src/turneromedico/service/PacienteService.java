package turneromedico.service;

import java.util.List;
import turneromedico.dao.PacienteDAOImpl;
import turneromedico.exceptions.DAOException;
import turneromedico.exceptions.ServiceException;
import turneromedico.model.Paciente;

public class PacienteService {
    private PacienteDAOImpl pacienteDAO;

    public PacienteService() throws ServiceException {
        try {
            this.pacienteDAO = new PacienteDAOImpl();
        } catch (DAOException e) {
            throw new ServiceException("Error initializing PacienteService", e);
        }
    }

    public void crear(Paciente paciente) throws ServiceException {
        try {
            pacienteDAO.crear(paciente);
        } catch (DAOException e) {
            throw new ServiceException("Error creating Paciente", e);
        }
    }

    public Paciente leer(Integer id) throws ServiceException {
        try {
            return pacienteDAO.leer(id);
        } catch (DAOException e) {
            throw new ServiceException("Error reading Paciente with ID: " + id, e);
        }
    }

    public void actualizar(Paciente paciente) throws ServiceException {
        try {
            pacienteDAO.actualizar(paciente);
        } catch (DAOException e) {
            throw new ServiceException("Error updating Paciente", e);
        }
    }

    public void eliminar(Integer id) throws ServiceException {
        try {
            pacienteDAO.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting Paciente with ID: " + id, e);
        }
    }

    public List<Paciente> listar() throws ServiceException {
        try {
            return pacienteDAO.listar();
        } catch (DAOException e) {
            throw new ServiceException("Error listing Pacientes", e);
        }
    }
}
