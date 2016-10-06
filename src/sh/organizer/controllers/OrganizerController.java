package sh.organizer.controllers;

import sh.organizer.model.dao.ClientDao;
import sh.organizer.model.entities.Client;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * @author Sherhan
 */
public class OrganizerController {
    private ClientDao clientDao = new ClientDao();

    public List<Client> list() throws JAXBException {
        return clientDao.getAllClients();
    }
}
