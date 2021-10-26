package backendReto.repository;

import backendReto.model.Reservation;
import backendReto.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public class ReservationRepository {

    @Autowired
    ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> obtenerReservation(){
        return (List<Reservation>)reservationCrudRepository.findAll();
    }

    public Optional<Reservation> obtenerReservationId(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation crearReservation(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public void eliminarReservation(Reservation reservation) {
        reservationCrudRepository.delete(reservation);
    }
}
