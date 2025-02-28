package org.elvinjiby.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String showProducts(Model model){
        model.addAttribute("message", "Product list");
        return "products";
    }
}
