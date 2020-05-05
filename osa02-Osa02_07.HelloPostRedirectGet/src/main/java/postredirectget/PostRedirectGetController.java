package postredirectget;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostRedirectGetController {

    private List<String> list;

    public PostRedirectGetController() {
        this.list = new ArrayList<>();
    }
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("list", this.list);
        return "index";
    }
    
    @RequestMapping (value = "/", method = RequestMethod.POST)
    public String post(@RequestParam String content) {
        if (!(content == null) || content.trim().isEmpty()) {
            this.list.add(content);
        }
        return "redirect:/";
    }
}
