package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.User;
import org.elvinjiby.marketplace.model.UserRole;
import org.elvinjiby.marketplace.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserRepository userRepo;
    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") User user, Model model) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage",
                    "Username already exists. Please choose another one.");
            return "register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        userRepo.save(user);

        model.addAttribute("errorMessage", "Registration successful. You may log in.");

        return "register";
    }
}
