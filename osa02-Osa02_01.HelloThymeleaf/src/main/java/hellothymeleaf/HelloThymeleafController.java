package hellothymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloThymeleafController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/video")
    public String video() {
        return "video";
    }
}
