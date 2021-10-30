package backendReto.web;


import backendReto.Reports.CustomerAccountant;
import backendReto.Reports.StatusReserve;
import backendReto.model.Reservation;
import backendReto.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getReservation() {
        return reservationService.obtenerReservation();
    }

    @GetMapping("/(id)")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationService.obtenerReservationId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation postReservation(@RequestBody Reservation reservation){
        return reservationService.crearReservation(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation putReservation(@RequestBody Reservation reservation) {
        return reservationService.actualizarReservation(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation(@PathVariable("id") int id) {
        return reservationService.eliminarReservation(id);
    }

    @GetMapping("/report-status")
    public StatusReserve getReservas(){
        return reservationService.getReporteStatusReservaciones();
    }

    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo){
        return reservationService.getReportesTiempoReservaciones(dateOne, dateTwo);
    }

    @GetMapping("/report-clients")
    public List<CustomerAccountant> getClientes(){
        return reservationService.servicioTopClientes();

    }
}
