package hellopaths;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloPathsController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
    
    @RequestMapping("/paths")
    @ResponseBody
    public String paths(){
        return "Paths";
    }

}
