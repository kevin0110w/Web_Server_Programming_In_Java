package airports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AircraftController {

    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/aircrafts")
    public String list(Model model) {
        model.addAttribute("aircrafts", aircraftRepository.findAll());
        model.addAttribute("airports", airportRepository.findAll());

        return "aircrafts";
    }

    @PostMapping("/aircrafts")
    public String create(@RequestParam String name) {
        Aircraft a = new Aircraft();
        a.setName(name);

        aircraftRepository.save(a);
        return "redirect:/aircrafts";
    }

    @PostMapping("/aircrafts/{aircraftId}/airports")
    public String assignAirport(@PathVariable String aircraftId, @RequestParam Long airportId) {

        Airport theAirport = airportRepository.getOne(airportId);
        Aircraft theAircraft = aircraftRepository.getOne(Long.parseLong(aircraftId));
        theAircraft.setAirport(theAirport);

        aircraftRepository.save(theAircraft);
        
        return "redirect:/aircrafts";
    }

}
