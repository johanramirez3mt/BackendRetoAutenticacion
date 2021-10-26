package backendReto.repository;

import backendReto.model.Client;
import backendReto.repository.crud.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    ClientCrudRepository clientCrudRepository;

    public List<Client> obtenerClient(){
        return (List<Client>)clientCrudRepository.findAll();
    }

    public Optional<Client> obtenerClientId(int id) {
        return clientCrudRepository.findById(id);
    }

    public Client crearClient(Client client) {
        return clientCrudRepository.save(client);
    }

    public void eliminarClient(Client client) {
        clientCrudRepository.delete(client);
    }
}
