/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author woohoo
 */
@Controller
public class ShoppingCartController {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ShoppingCart cart;
    
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }
    
    @PostMapping("/cart/items/{id}")
    public String addItem(@PathVariable Long id) {
        Item i = this.itemRepository.getOne(id);
        if (i != null) {
            cart.addToCart(i);
        }
        return "redirect:/cart";
    }
}
