package sh.organizer.controllers;

import sh.organizer.model.dao.ClientDao;
import sh.organizer.model.entities.Client;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.LinkedList;
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

    public String find(String key) throws JAXBException {
        key = key.toUpperCase();
        List<Client> clients = clientDao.getClientsList();
        List<Client> foundClients = new LinkedList<>();

        for (Client client : clients) {
            if (Integer.toString(client.getId()).toUpperCase().contains(key)
                    || client.getName().toUpperCase().contains(key)
                    || client.getOrganization().toUpperCase().contains(key)
                    || client.getPosition().toUpperCase().contains(key)
                    || client.getEmail().toUpperCase().contains(key)
                    || client.getPhones().toString().toUpperCase().contains(key)) {
                foundClients.add(client);
            }
        }

        return prepareResults(foundClients);
    }

    private String prepareResults(List<Client> foundClients) {
        StringBuilder result = new StringBuilder();
        for (Client findClient : foundClients) {
            result.append(findClient.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
