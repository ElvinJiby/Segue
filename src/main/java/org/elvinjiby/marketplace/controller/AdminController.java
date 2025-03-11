package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final ProductService productService;
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin-home";
    }

    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productsList", products);
        return "admin-products";
    }

    @GetMapping("/admin/orders")
    public String adminOrders() {
        return "admin-orders";
    }

    @GetMapping("/admin/contacts")
    public String adminContacts() {
        return "admin-contact";
    }
}
