package org.elvinjiby.marketplace.controller;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.model.User;
import org.elvinjiby.marketplace.repository.UserRepository;
import org.elvinjiby.marketplace.service.CartService;
import org.elvinjiby.marketplace.service.OrderService;
import org.elvinjiby.marketplace.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final UserRepository userRepo;

    public CustomerController(ProductService productService, CartService cartService, OrderService orderService, UserRepository userRepo) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.userRepo = userRepo;
    }

    @GetMapping("/customer/home")
    public String customerHome() {
        return "customer-home";
    }

    // Products page
    @GetMapping("/customer/products")
    public String customerProducts(Model model) {
        List<Product> products = productService.getVisibleProducts();
        model.addAttribute("productsList", products);
        return "customer-products";
    }

    @GetMapping("/customer/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null || product.isHidden()) {
            return "redirect:/customer/products";
        }
        model.addAttribute("product", product);
        return "customer-product-details";
    }

    // Orders page
    @GetMapping("/customer/orders")
    public String customerOrders(Model model, Authentication auth) {
        User customer = userRepo.findByUsername(auth.getName());
        if (customer != null) {
            model.addAttribute("orders", orderService.findOrdersByCustomer(customer));
        }
        return "customer-orders";
    }

    @PostMapping("/customer/order")
    public String placeOrder(Authentication auth) {
        User customer = userRepo.findByUsername(auth.getName());
        if (customer != null) {
            orderService.placeOrder(customer);
        }
        return "redirect:/customer/orders";
    }

    // Cart page
    @GetMapping("/customer/cart")
    public String customerCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalCartPrice());
        return "customer-cart";
    }

    @PostMapping("/customer/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null && !product.isHidden()) {
            cartService.addToCart(product);
        }
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        // removal is skipped if not found or null
        cartService.removeFromCart(id);
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/cart/update/{id}")
    public String updateCart(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateQuantity(id, quantity);
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/cart/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/customer/cart";
    }

    @PostMapping("/customer/checkout")
    public String checkout(Authentication auth) {
        User customer = userRepo.findByUsername(auth.getName());
        if (customer != null) {
            orderService.placeOrder(customer);
        }
        return "redirect:/customer/cart";
    }
}
