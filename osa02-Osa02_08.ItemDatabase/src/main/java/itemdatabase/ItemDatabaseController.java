package itemdatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemDatabaseController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("items", this.itemRepository.findAll());
        return "index";
    }
    
    
    @PostMapping("/")
    public String post(@RequestParam String name) {
        this.itemRepository.save(new Item(name));
        return "redirect:/";
    }

}
