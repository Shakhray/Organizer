package sh.organizer.model.dao;

import sh.organizer.model.entities.Client;
import sh.organizer.model.entities.Clients;
import sh.organizer.model.parser.JaxbParser;
import sh.organizer.model.parser.Parser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * @author Sherhan
 */
public class ClientDao {
    private static final String FILE_NAME = "src/resources/clients.xml";
    private Parser xmlParser;

    public ClientDao() {
        xmlParser = new JaxbParser();
    }

    public List<Client> getAllClients() throws JAXBException {
        Clients clients = (Clients) xmlParser.getObject(new File(FILE_NAME), Clients.class);
        return clients.getClients();
    }
}
