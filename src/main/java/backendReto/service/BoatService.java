package backendReto.service;

import backendReto.model.Boat;
import backendReto.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class BoatService {

    @Autowired
    BoatRepository boatRepository;

    public List<Boat> obtenerBoat() {
        return boatRepository.obtenerBoat();
    }

    public Optional<Boat> obtenerBoatId(int id) {
        return boatRepository.obtenerBoatId(id);
    }

    public Boat crearBoat(Boat boat) {
        if(boat.getId() == null){
            return boatRepository.crearBoat(boat);
        }else{
            Optional<Boat> e = boatRepository.obtenerBoatId(boat.getId());
            if (e.isEmpty()){
                return boatRepository.crearBoat(boat);
            } else {
                return boat;
            }
        }
    }

    public Boat actualizarBoat(Boat boat) {
        if (boat.getId() != null) {
            Optional<Boat> e = boatRepository.obtenerBoatId(boat.getId());
            if (!e.isEmpty()) {
                if (boat.getName() != null) {
                    e.get().setName(boat.getName());
                }
                if (boat.getBrand() != null) {
                    e.get().setBrand(boat.getBrand());
                }
                if (boat.getYear() != null) {
                    e.get().setYear(boat.getYear());
                }
                if (boat.getDescription() != null) {
                    e.get().setDescription(boat.getDescription());
                }
                if (boat.getCategory() != null) {
                    e.get().setCategory(boat.getCategory());
                }
                boatRepository.crearBoat(e.get());
                return e.get();
            } else {
                return boat;
            }
        } else {
            return boat;
        }
    }

    public boolean eliminarBoat(int boatId) {
        Boolean aBoolean = obtenerBoatId(boatId).map(boat -> {
            boatRepository.eliminarBoat(boat);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
