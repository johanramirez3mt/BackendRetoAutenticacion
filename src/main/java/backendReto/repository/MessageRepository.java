package backendReto.repository;

import backendReto.model.Message;
import backendReto.repository.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public class MessageRepository {

    @Autowired
    MessageCrudRepository messageCrudRepository;

    public List<Message> obtenerMessage(){
        return (List<Message>)messageCrudRepository.findAll();
    }

    public Optional<Message> obtenerMessageId(int id) {
        return messageCrudRepository.findById(id);
    }

    public Message crearMessage(Message message) {
        return messageCrudRepository.save(message);
    }

    public void eliminarMessage(Message message) {
        messageCrudRepository.delete(message);
    }
}
