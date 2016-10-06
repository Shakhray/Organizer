package sh.organizer.model.entities;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Sherhan
 */
@XmlRootElement(name = "clients")
public class Clients {
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    @XmlElement(name = "client")
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
