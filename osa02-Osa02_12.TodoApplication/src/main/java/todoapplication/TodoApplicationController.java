package todoapplication;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TodoApplicationController {
    private Map<String, Task> tasks;
            
    public TodoApplicationController() {
        this.tasks = new TreeMap<>();
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", this.tasks.values());
        return "index";
    }
    
    @PostMapping("/")
    public String post(@RequestParam String name) {
        Task aTask = new Task(name);
        this.tasks.putIfAbsent(aTask.getId(), aTask);
        return "redirect:/";
    }
    
    @RequestMapping("/{id}")
    public String singleTask(Model model, @PathVariable String id) {
        if (id == null || id.trim().isEmpty()) {
            return home(model);
        }
        
        Task aTask = this.tasks.get(id);
        aTask.setChecked(aTask.getChecked()+1);
        this.tasks.put(aTask.getId(), aTask);
        model.addAttribute("item", aTask);
        return "todo";
    }
    
    @RequestMapping(value="/{item}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String item) {
        if (item == null || item.trim().isEmpty()) {
            return "redirect:/";
        }
        
        Iterator<Task> iterator = this.tasks.values().iterator();
        while (iterator.hasNext()) {
            Task aTask = iterator.next();
            if (aTask.getId().equals(item)) {
                iterator.remove();
            }
        }
        return "redirect:/";
    }
    
    
}
