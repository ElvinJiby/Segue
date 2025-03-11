package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    private final ProductService productService;
    public CustomerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/customer/home")
    public String customerHome() {
        return "customer-home";
    }

    @GetMapping("/customer/products")
    public String customerProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productsList", products);
        return "customer-products";
    }

    @GetMapping("/customer/orders")
    public String ordersPage() {
        return "customer-orders";
    }

    @GetMapping("/customer/cart")
    public String customerCart() {
        return "customer-cart";
    }
}
