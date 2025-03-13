package org.elvinjiby.marketplace.service;

import org.elvinjiby.marketplace.model.*;
import org.elvinjiby.marketplace.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepo, CartService cartService) {
        this.orderRepo = orderRepo;
        this.cartService = cartService;
    }

    public List<Order> findOrdersByCustomer(User customer) {
        return orderRepo.findByCustomer(customer);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public void placeOrder(User customer) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (!cartItems.isEmpty()) {
            Order order = new Order(customer, new ArrayList<>());
            for (CartItem item : cartItems) {
                OrderItem orderItem = new OrderItem(item.getProduct().getName(),
                        item.getProduct().getPrice(), item.getQuantity());
                orderItem.setOrder(order);
                order.getItems().add(orderItem);
            }

            orderRepo.save(order);
            cartService.clearCart();
        }
    }

    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(orderStatus);
            orderRepo.save(order);
        }
    }
}
