package turneromedico.service;

import java.util.List;
import turneromedico.dao.MedicoDAOImpl;
import turneromedico.exceptions.DAOException;
import turneromedico.exceptions.ServiceException;
import turneromedico.model.Medico;

public class MedicoService {
    private MedicoDAOImpl medicoDAO;

    public MedicoService() throws ServiceException {
        try {
            this.medicoDAO = new MedicoDAOImpl();
        } catch (DAOException e) {
            throw new ServiceException("Error initializing MedicoService", e);
        }
    }

    public void crear(Medico medico) throws ServiceException {
        try {
            medicoDAO.crear(medico);
        } catch (DAOException e) {
            throw new ServiceException("Error creating Medico", e);
        }
    }

    public Medico leer(Integer id) throws ServiceException {
        try {
            return medicoDAO.leer(id);
        } catch (DAOException e) {
            throw new ServiceException("Error reading Medico with ID: " + id, e);
        }
    }

    public void actualizar(Medico medico) throws ServiceException {
        try {
            medicoDAO.actualizar(medico);
        } catch (DAOException e) {
            throw new ServiceException("Error updating Medico", e);
        }
    }

    public void eliminar(Integer id) throws ServiceException {
        try {
            medicoDAO.eliminar(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting Medico with ID: " + id, e);
        }
    }

    public List<Medico> listar() throws ServiceException {
        try {
            return medicoDAO.listar();
        } catch (DAOException e) {
            throw new ServiceException("Error listing Medicos", e);
        }
    }
}
