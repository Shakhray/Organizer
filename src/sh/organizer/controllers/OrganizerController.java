package sh.organizer.controllers;

import sh.organizer.model.dao.XmlClientDao;
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
    private XmlClientDao xmlClientDao = new XmlClientDao();

    public String list() throws JAXBException {
        StringBuilder result = new StringBuilder();
        for (Client client : xmlClientDao.list()) {
            result.append(client.toString());
            result.append("\n");
        }
        return result.toString();
    }

    public void insert(Map<String, String> clientInfo) throws JAXBException {
        Client newClient = new Client();
        newClient.setName(clientInfo.get(ClientFields.NAME.getFieldName()));
        newClient.setOrganization(clientInfo.get(ClientFields.ORGANIZATION.getFieldName()));
        newClient.setPosition(clientInfo.get(ClientFields.POSITION.getFieldName()));
        newClient.setEmail(clientInfo.get(ClientFields.EMAIL.getFieldName()));
        List<String> phones = Arrays.asList(clientInfo.get(ClientFields.PHONES.getFieldName()).replace(" ", "").split(","));
        newClient.setPhones(phones);
        xmlClientDao.insert(newClient);
    }

    public String find(String key) throws JAXBException {
        key = key.toUpperCase();
        List<Client> clients = xmlClientDao.list();
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

    public boolean delete(String id) throws JAXBException {
        int idToDelete = Integer.valueOf(id);
        for (Client client : xmlClientDao.list()) {
            if (client.getId() == idToDelete) {
                xmlClientDao.delete(client);
                return true;
            }
        }
        return false;
    }

    public boolean update(Map<String, String> clientInfo) throws JAXBException {
        int idToUpdate = Integer.valueOf(clientInfo.get(ClientFields.ID.getFieldName()));
        for (Client client : xmlClientDao.list()) {
            if (client.getId() == idToUpdate) {

                String name = clientInfo.get(ClientFields.NAME.getFieldName());
                String organization = clientInfo.get(ClientFields.ORGANIZATION.getFieldName());
                String position = clientInfo.get(ClientFields.POSITION.getFieldName());
                String email = clientInfo.get(ClientFields.EMAIL.getFieldName());
                String phones = clientInfo.get(ClientFields.PHONES.getFieldName());

                if (!name.isEmpty()) {
                    client.setName(name);
                }
                if (!organization.isEmpty()) {
                    client.setOrganization(organization);
                }
                if (!position.isEmpty()) {
                    client.setPosition(position);
                }
                if (!email.isEmpty()) {
                    client.setEmail(email);
                }
                if (!phones.isEmpty()) {
                    client.setPhones(Arrays.asList(phones.replace(" ", "").split(",")));
                }

                xmlClientDao.update(client);
                return true;
            }
        }
        return false;
    }
}
