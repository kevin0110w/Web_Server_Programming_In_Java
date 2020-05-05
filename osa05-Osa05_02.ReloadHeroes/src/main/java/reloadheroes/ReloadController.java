package reloadheroes;

import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {
        Random rand = new Random();
        ReloadStatus r = new ReloadStatus();
        String name = "";
        while (true) {
             name = "Person " + rand.nextInt();
             if (this.reloadStatusRepository.findByName(name) == null) {
                 break;
             }
        }
        r.setName(name);
        r.setReloads(1);
        
        if (this.session.getAttribute("name") != null) {
            r = this.reloadStatusRepository.findByName((String) this.session.getAttribute("name"));
            int rel = r.getReloads();
            rel = rel + 1;
            r.setReloads(rel);
        }
        this.session.setAttribute("name", name);
        this.reloadStatusRepository.save(r);
        
        model.addAttribute("status", r);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        
        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        return "index";
    }
}
