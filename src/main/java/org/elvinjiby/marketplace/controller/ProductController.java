package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productsList", products);
        return "products";  // products.html
    }
}
