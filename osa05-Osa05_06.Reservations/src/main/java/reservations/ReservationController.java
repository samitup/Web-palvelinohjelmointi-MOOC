package reservations;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reseRepo;

    @Autowired
    private AccountRepository accRepo;

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reseRepo.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {

        List<Reservation> reservationList = reseRepo.findAll();
        for (Reservation res : reservationList) {
            if (res.getReservationFrom().equals(reservationFrom) || res.getReservationTo().equals(reservationTo)) {
                return "redirect:/reservations";
            }
        }

        Reservation reservation = new Reservation();
        reservation.setReservationFrom(reservationFrom);
        reservation.setReservationTo(reservationTo);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        reservation.setUser(accRepo.findByUsername(username));
        reseRepo.save(reservation);
        return "redirect:/reservations";
    }

}
