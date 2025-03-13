package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private double priceAtPurchase;

    @Column(nullable = false)
    private int quantity;

    public OrderItem() {}
    public OrderItem(String productName, double priceAtPurchase, int quantity) {
        this.productName = productName;
        this.priceAtPurchase = priceAtPurchase;
        this.quantity = quantity;
    }

    public Long getId() { return id; }
    public String getProductName() { return productName; }
    public double getPriceAtPurchase() { return priceAtPurchase; }
    public int getQuantity() { return quantity; }
    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }
}
