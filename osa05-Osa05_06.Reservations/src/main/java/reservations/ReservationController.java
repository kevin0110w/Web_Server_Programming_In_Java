package reservations;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservations")
    public String index(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reservationTo) {

        if (reservationFrom != null && reservationTo != null) {
            Reservation r = new Reservation();
            r.setReservationTo(reservationTo);
            r.setReservationFrom(reservationFrom);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Account theUser = accountRepository.findByUsername(username);

            List<Reservation> reservationsFromUser = reservationRepository.findAll().stream().filter(reservation -> reservation.getUser().equals(theUser)).collect(Collectors.toList());

            for (Reservation reservation : reservationsFromUser) {
                if (reservation.getReservationFrom().isBefore(reservationTo) && reservationFrom.isBefore(reservation.getReservationTo())) {
                    return "redirect:/reservations";
                }
            }
            r.setUser(theUser);
            reservationRepository.save(r);
        }

        return "redirect:/reservations";
    }

}
