package sh.organizer.controllers;

import sh.organizer.model.dao.ClientDao;
import sh.organizer.model.entities.Client;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Sherhan
 */
public class OrganizerController {
    private ClientDao clientDao = new ClientDao();

    public List<Client> list() throws JAXBException {
        return clientDao.getClientsList();
    }

    public void insert(Map<String, String> clientInfo) throws JAXBException {
        Client newClient = new Client();
        newClient.setId(Integer.valueOf(clientInfo.get(ClientInfo.ID.getDescription())));
        newClient.setName(clientInfo.get(ClientInfo.NAME.getDescription()));
        newClient.setOrganization(clientInfo.get(ClientInfo.ORGANIZATION.getDescription()));
        newClient.setPosition(clientInfo.get(ClientInfo.POSITION.getDescription()));
        newClient.setEmail(clientInfo.get(ClientInfo.EMAIL.getDescription()));
        List<String> phones = Arrays.asList(clientInfo.get(ClientInfo.PHONES.getDescription()).replace(" ", "").split(","));
        newClient.setPhones(phones);
        clientDao.insertClient(newClient);
    }
}
