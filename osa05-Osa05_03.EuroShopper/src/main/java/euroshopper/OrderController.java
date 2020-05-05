package euroshopper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    
    @Autowired
    private ShoppingCart cart;
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        Map<Item, Long> items = cart.getItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for (Item i : items.keySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(i);
            orderItem.setItemCount(items.get(i));
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        cart.getItems().clear();
        return "redirect:/orders";
    }
}
