package sh.organizer.view;

import sh.organizer.model.entities.Client;

import java.util.List;

/**
 * @author Sherhan
 */
public class Printer {
    public void printCommandLine() {
        System.out.print("organizer> ");
    }

    public void printClients(List<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }

}
