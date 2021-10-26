package backendReto.service;

import backendReto.model.Client;
import backendReto.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> obtenerClient() {
        return clientRepository.obtenerClient();
    }

    public Optional<Client> obtenerClientId(int id) {
        return clientRepository.obtenerClientId(id);
    }

    public Client crearClient(Client client) {
        if (client.getIdClient() == null) {
            return clientRepository.crearClient(client);
        }else {
            Optional<Client> e = clientRepository.obtenerClientId(client.getIdClient());
            if (e.isEmpty()) {
                return clientRepository.crearClient(client);
            } else {
                return client;
            }
        }
    }

    public Client actualizarClient(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> e = clientRepository.obtenerClientId(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.crearClient(e.get());
                return e.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }

    public boolean eliminarClient(int id) {
        Boolean aBoolean = obtenerClientId(id).map(client -> {
            clientRepository.eliminarClient(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
