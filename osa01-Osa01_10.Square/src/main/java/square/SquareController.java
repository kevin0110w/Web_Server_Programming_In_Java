package square;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SquareController {
    
    @RequestMapping("/square")
    @ResponseBody
    public String square(@RequestParam int num) {
        return "" + num * num;
    }

}
