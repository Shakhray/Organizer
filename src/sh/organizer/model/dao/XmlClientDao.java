package sh.organizer.model.dao;

import sh.organizer.model.entities.Client;
import sh.organizer.model.entities.Clients;
import sh.organizer.model.parsers.JaxbParser;
import sh.organizer.model.parsers.Parser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sherhan
 */
public class XmlClientDao implements ClientDao {
    private static final String FILE_NAME = "src/resources/clients.xml";
    private Parser xmlParser;
    private Clients cashedClients;
    private File xmlFile;
    private IdGenerator idGenerator;

    public XmlClientDao() {
        xmlFile = new File(FILE_NAME);
        xmlParser = new JaxbParser();
        idGenerator = new ClientIdGenerator();
    }

    @Override
    public List<Client> list() throws JAXBException {
        return new LinkedList<>(clientsList());
    }

    private List<Client> clientsList() throws JAXBException {
        return getCurrentClients().getClients();
    }

    private Clients getCurrentClients() throws JAXBException {
        if (cashedClients == null) {
            cashedClients = getClients();
        }
        return cashedClients;
    }

    private Clients getClients() throws JAXBException {
        return (Clients) xmlParser.getObject(xmlFile, Clients.class);
    }

    @Override
    public void insert(Client newClient) throws JAXBException {
        newClient.setId(idGenerator.generateId());
        clientsList().add(newClient);
        saveChanges();
    }

    @Override
    public void delete(Client clientToDelete) throws JAXBException {
        clientsList().remove(clientToDelete);
        saveChanges();
    }

    @Override
    public void update(Client clientToUpdate) throws JAXBException {
        for (Client client : clientsList()) {
            if (client.getId() == clientToUpdate.getId()) {
                clientsList().remove(client);
                clientsList().add(clientToUpdate);
                saveChanges();
                break;
            }
        }
    }

    private void saveChanges() throws JAXBException {
        xmlParser.saveObject(xmlFile, getCurrentClients());
    }

    private class ClientIdGenerator implements IdGenerator {
        private int currentId;
        private boolean isFirstUsing = true;

        @Override
        public int generateId() {
            if (isFirstUsing) {
                currentId = findCurrentId();
                isFirstUsing = false;
            }

            return ++currentId;
        }

        private int findCurrentId() {
            try {
                int currentId = 0;
                for (Client client : list()) {
                    if (client.getId() > currentId) {
                        currentId = client.getId();
                    }
                }
                return currentId;
            } catch (JAXBException e) {
                return 1;
            }
        }
    }
}
