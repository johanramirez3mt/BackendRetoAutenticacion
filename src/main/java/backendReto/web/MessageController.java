package backendReto.web;


import backendReto.model.Message;
import backendReto.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<Message> getMessage() {
        return messageService.obtenerMessage();
    }

    @GetMapping("/(id)")
    public Optional<Message> getMessage(@PathVariable("id") int id) {
        return messageService.obtenerMessageId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message postMessage(@RequestBody Message message){
        return messageService.crearMessage(message);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message putMessage(@RequestBody Message message) {
        return messageService.actualizarMessage(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMessage(@PathVariable("id") int id) {
        return messageService.eliminarMessage(id);
    }
}
