package greeting;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    @RequestMapping("/greet")
    @ResponseBody
    public String greeting(@RequestParam Map<String, String> params) {
        return params.get("greeting") + ", " + params.get("name");
    }
}
