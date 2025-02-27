package hellorequestparams;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloRequestParamsController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String param) {
        return "Hello " + param;
    }
    
    @RequestMapping("/params")
    @ResponseBody
    public String params(@RequestParam Map<String, String> params) {
        return params.keySet().toString();
    }
}
