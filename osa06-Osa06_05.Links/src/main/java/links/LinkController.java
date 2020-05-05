package links;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LinkController {

    @Autowired
    LinkRepository linkRepository;

    @GetMapping("*")
    public String main() {
        return "redirect:/links";
    }

    @GetMapping("/links")
    public String list(Model model) {
        model.addAttribute("links", linkRepository.findAll());
        return "links";
    }

//    @PostMapping("/links")
//    public String create(@RequestParam String title, @RequestParam String description, @RequestParam String url) {
//        linkRepository.save(new Link(title, description, url));
//        return "redirect:/links";
//    }
    
    /** Using the @ModelAttribute annotation to use the values from the request to set the object variables in the Link class
     * and saving in the database.
     * @param link
     * @return 
     */
    @PostMapping("/links")
    public String create(@ModelAttribute Link link) {
        linkRepository.save(link);
        return "redirect:/links";
    }
}
