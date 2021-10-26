package backendReto.service;

import backendReto.model.Message;
import backendReto.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> obtenerMessage() {
        return messageRepository.obtenerMessage();
    }

    public Optional<Message> obtenerMessageId(int id) {
        return messageRepository.obtenerMessageId(id);
    }

    public Message crearMessage(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.crearMessage(message);
        }else {
            Optional<Message> e = messageRepository.obtenerMessageId(message.getIdMessage());
            if (e.isEmpty()) {
                return messageRepository.crearMessage(message);
            } else {
                return message;
            }
        }
    }

    public Message actualizarMessage(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> e = messageRepository.obtenerMessageId(message.getIdMessage());
            if (!e.isEmpty()) {
                if (message.getMessageText() != null) {
                    e.get().setMessageText(message.getMessageText());
                }
                messageRepository.crearMessage(e.get());
                return e.get();
            } else {
                return message;
            }
        } else {
            return message;
        }
    }

    public boolean eliminarMessage(int id) {
        Boolean aBoolean = messageRepository.obtenerMessageId(id).map(message -> {
            messageRepository.eliminarMessage(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
