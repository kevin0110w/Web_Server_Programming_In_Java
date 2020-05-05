package hellomodel;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloModelController {
    
    @RequestMapping("/")
    public String home(Model model, @RequestParam Map<String, String> params) {
        String title = params.get("title");
        String person = params.get("person");
        
        
        model.addAttribute("title", title);
        model.addAttribute("person", person);
        return "index";
    }

}
