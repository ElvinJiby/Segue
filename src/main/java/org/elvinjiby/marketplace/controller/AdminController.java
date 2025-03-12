package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    // products page
    @GetMapping("/admin/products")
    public String adminProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("productsList", products);
        model.addAttribute("newProduct", new Product());
        return "admin-products";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) { return "redirect:/admin/products"; }
        model.addAttribute("product", product);
        return "admin-products-edit";
    }

    @PostMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        productService.updateProductbyId(id, product.getArtist(), product.getPrice(),
                product.getDescription(), product.getAlbumCoverURL());
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/products/toggle-visibility/{id}")
    public String toggleVisibility(@PathVariable Long id) {
        productService.toggleProductVisibility(id);
        return "redirect:/admin/products";
    }


    // orders page
    @GetMapping("/admin/orders")
    public String adminOrders() {
        return "admin-orders";
    }

    @GetMapping("/admin/contacts")
    public String adminContacts() {
        return "admin-contact";
    }
}
