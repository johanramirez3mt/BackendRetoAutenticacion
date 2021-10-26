package backendReto.service;

import backendReto.model.Reservation;
import backendReto.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> obtenerReservation() {
        return reservationRepository.obtenerReservation();
    }

    public Optional<Reservation> obtenerReservationId(int id) {
        return reservationRepository.obtenerReservationId(id);
    }

    public Reservation crearReservation(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.crearReservation(reservation);
        }else {
            Optional<Reservation> e = reservationRepository.obtenerReservationId(reservation.getIdReservation());
            if (e.isEmpty()) {
                return reservationRepository.crearReservation(reservation);
            } else {
                return reservation;
            }
        }
    }


    public Reservation actualizarReservation(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> e = reservationRepository.obtenerReservationId(reservation.getIdReservation());
            if (!e.isEmpty()) {
                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.crearReservation(e.get());
                return e.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean eliminarReservation(int id) {
        Boolean aBoolean = obtenerReservationId(id).map(reservation -> {
            reservationRepository.eliminarReservation(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
