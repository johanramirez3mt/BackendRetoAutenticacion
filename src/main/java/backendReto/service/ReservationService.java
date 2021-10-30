package backendReto.service;

import backendReto.model.Reservation;
import backendReto.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backendReto.Reports.CustomerAccountant;
import backendReto.Reports.StatusReserve;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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




    public StatusReserve getReporteStatusReservaciones(){
        List<Reservation>completed= reservationRepository.ReservacionStatus("completed");
        List<Reservation>cancelled= reservationRepository.ReservacionStatus("cancelled");
        return new StatusReserve(completed.size(), cancelled.size());
    }

    public List<Reservation> getReportesTiempoReservaciones(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();

        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return reservationRepository.ReservacionTiempo(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }

    public List<CustomerAccountant> servicioTopClientes(){
        return reservationRepository.getTopClientes();
    }
}
