package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;

@Entity // defines entity in sql
@Table(name="products") // manually set the name of sql table
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // primary key
    private Long id;

    private String name;
    private double price;
    private String description;

    public Product() {}

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
}
