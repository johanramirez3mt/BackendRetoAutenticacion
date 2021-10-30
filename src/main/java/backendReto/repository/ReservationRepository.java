package backendReto.repository;

import backendReto.Reports.CustomerAccountant;
import backendReto.model.Client;
import backendReto.model.Reservation;
import backendReto.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
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




    public List<Reservation> ReservacionStatus (String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> ReservacionTiempo (Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<CustomerAccountant> getTopClientes(){
        List<CustomerAccountant> res=new ArrayList<>();
        List<Object[]>report = reservationCrudRepository.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
            res.add(new CustomerAccountant((Long)report.get(i)[1],(Client) report.get(i)[0]));

        }
        return res;
    }
}
