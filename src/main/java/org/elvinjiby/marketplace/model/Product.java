package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // defines entity in sql
@Table(name="products") // add to sql table products
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // primary key
    private Long id;

    private String name;
    private double price;
    private String description;
}
