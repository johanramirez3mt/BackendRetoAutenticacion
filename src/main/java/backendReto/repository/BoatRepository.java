package backendReto.repository;

import backendReto.model.Boat;
import backendReto.repository.crud.BoatCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoatRepository {

    @Autowired
    BoatCrudRepository boatCrudRepository;

    public List<Boat> obtenerBoat(){
        return (List<Boat>)boatCrudRepository.findAll();
    }

    public Optional<Boat> obtenerBoatId(int id){
        return boatCrudRepository.findById(id);
    }

    public Boat crearBoat(Boat boat) {
        return boatCrudRepository.save(boat);
    }

    public void eliminarBoat(Boat boat) {
        boatCrudRepository.delete(boat);
    }
}
