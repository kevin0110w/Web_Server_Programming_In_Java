/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author woohoo
 */
@Controller
public class AccountController {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
   @PostMapping("/accounts")
   public String add(@RequestParam String username, @RequestParam String password) {
       if (accountRepository.findByUsername(username) != null) {
           return "redirect:/reservations";
       }
       
       Account anAccount = new Account(username, passwordEncoder.encode(password));
       accountRepository.save(anAccount);
       return "redirect:/reservations";
   }
}
