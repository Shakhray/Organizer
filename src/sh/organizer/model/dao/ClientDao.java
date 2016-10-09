package sh.organizer.model.dao;

import sh.organizer.model.entities.Client;

import java.util.List;

/**
 * @author Sherhan
 */
public interface ClientDao {

    List<Client> list() throws Exception;

    void insert(Client newClient) throws Exception;

    void delete(Client clientToDelete) throws Exception;

    void update(Client clientToUpdate) throws Exception;
}
