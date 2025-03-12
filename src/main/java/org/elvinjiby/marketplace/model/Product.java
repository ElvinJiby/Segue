package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name="products") // manually set the name of sql table
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // primary key
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String description;

    @Column
    private String albumCoverURL;

    @Column(nullable = false)
    private boolean hidden;

    // Constructors
    public Product() {}
    public Product(String name, String artist, double price, String description, String albumCoverURL) {
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.description = description;
        this.albumCoverURL = albumCoverURL;
        this.hidden = false;
    }

    // Getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getArtist() { return artist; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getAlbumCoverURL() {
        return (albumCoverURL == null || albumCoverURL.isEmpty()) ? "/images/defaultcover.png" : albumCoverURL;
    }
    public boolean isHidden() { return hidden; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setAlbumCoverURL(String albumCoverURL) { this.albumCoverURL = albumCoverURL; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
}
