package calculator;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam Map<String, String> params) {
        int first = 0;
        int second = 0;
        int sum = 0;
        try {
            first = Integer.parseInt(params.get("first"));
            second = Integer.parseInt(params.get("second"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        sum = first + second;
        return "" + sum;
    }
    
    @RequestMapping("/multiply")
    @ResponseBody
    public String multiply(@RequestParam Map<String, String> params) {
        int first = 0;
        int second = 0;
        int sum = 0;
        try {
            first = Integer.parseInt(params.get("first"));
            second = Integer.parseInt(params.get("second"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        sum = first * second;
        return "" + sum;
    }
}
