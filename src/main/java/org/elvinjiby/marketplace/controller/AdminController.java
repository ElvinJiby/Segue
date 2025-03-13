package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.OrderStatus;
import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.model.User;
import org.elvinjiby.marketplace.repository.UserRepository;
import org.elvinjiby.marketplace.service.OrderService;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserRepository userRepository;

    public AdminController(ProductService productService, OrderService orderService, UserRepository userRepository) {
        this.productService = productService;
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
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

    @PostMapping("/admin/products/add-samples")
    public String addSampleProducts() {
        productService.addSampleProducts();
        return "redirect:/admin/products";
    }

    // orders page
    @GetMapping("/admin/orders")
    public String adminOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin-orders";
    }

    @PostMapping("/admin/orders/update/{id}")
    public String updateOrder(@PathVariable Long id, @RequestParam OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
        return "redirect:/admin/orders";
    }
}
