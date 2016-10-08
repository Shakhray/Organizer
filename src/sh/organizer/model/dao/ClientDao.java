package sh.organizer.model.dao;

import sh.organizer.model.entities.Client;
import sh.organizer.model.entities.Clients;
import sh.organizer.model.parser.JaxbParser;
import sh.organizer.model.parser.Parser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sherhan
 */
public class ClientDao {
    private static final String FILE_NAME = "src/resources/clients.xml";
    private Parser xmlParser;
    private Clients clients;
    private File xmlFile = new File(FILE_NAME);

    public ClientDao() {
        xmlParser = new JaxbParser();
    }

    public List<Client> getClientsList() throws JAXBException {
        return new LinkedList<>(clientsList());
    }

    private List<Client> clientsList() throws JAXBException {
        return getCurrentClients().getClients();
    }

    private Clients getCurrentClients() throws JAXBException {
        if (clients == null) {
            clients = getClients();
        }
        return clients;
    }

    private Clients getClients() throws JAXBException {
        return (Clients) xmlParser.getObject(xmlFile, Clients.class);
    }

    public void insertClient(Client newClient) throws JAXBException {
        clientsList().add(newClient);
        saveChanges();
    }

    public void delete(Client clientToDelete) throws JAXBException {
        clientsList().remove(clientToDelete);
        saveChanges();
    }

    private void saveChanges() throws JAXBException {
        xmlParser.saveObject(xmlFile, getCurrentClients());
    }
}
