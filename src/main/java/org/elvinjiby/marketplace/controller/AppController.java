package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.model.User;
import org.elvinjiby.marketplace.model.UserRole;
import org.elvinjiby.marketplace.repository.UserRepository;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
    private final UserRepository userRepo;
    private final ProductService productService;

    public AppController(UserRepository userRepo, ProductService productService) {
        this.userRepo = userRepo;
        this.productService = productService;
    }

    // Product Page
    @GetMapping("/products")
    public String listProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productsList", products);
        return "products";  // products.html
    }

    // User Registration & Login
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

    // Other Pages
    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    @GetMapping("/orders")
    public String ordersPage() {
        return "orders";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }
}
