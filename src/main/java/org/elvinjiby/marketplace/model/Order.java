package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // one user has multiple orders
    private User customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(nullable = false)
    private final LocalDateTime orderDate = LocalDateTime.now();

    // constructors
    public Order() {}
    public Order(User customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    // getters and setters
    public Long getId() { return id; }
    public User getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity()).sum();
    }

    public void setStatus(OrderStatus status) { this.status = status; }
}
