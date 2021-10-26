package backendReto.web;


import backendReto.model.Boat;
import backendReto.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Boat")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BoatController {

    @Autowired
    BoatService boatService;

    @GetMapping("/all")
    public List<Boat> getBoat() {
        return boatService.obtenerBoat();
    }

    @GetMapping("/(id)")
    public Optional<Boat> getBoat(@PathVariable("id") int boatId) {
        return boatService.obtenerBoatId(boatId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Boat postBoat(@RequestBody Boat boat){
        return boatService.crearBoat(boat);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Boat putBoat(@RequestBody Boat boat) {
        return boatService.actualizarBoat(boat);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteBoat(@PathVariable("id") int boatId) {
        return boatService.eliminarBoat(boatId);
    }
}
