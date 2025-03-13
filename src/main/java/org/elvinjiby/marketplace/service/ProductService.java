package org.elvinjiby.marketplace.service;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    public ProductService(ProductRepository productRepository) {
        this.productRepo = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> getVisibleProducts() {
        return productRepo.findByHiddenFalse();
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void updateProductbyId(Long id, String artist, double price, String description, String albumCoverURL) {
        Product product = getProductById(id);
        if (product != null) {
            product.setArtist(artist);
            product.setPrice(price);
            product.setDescription(description);
            product.setAlbumCoverURL(albumCoverURL);
            productRepo.save(product);
        }
    }

    public void toggleProductVisibility(Long id) {
        Product product = getProductById(id);
        if (product != null) {
            product.setHidden(!product.isHidden());
            productRepo.save(product);
        }
    }

    public void addSampleProducts() {
        List<Product> sampleProducts = List.of(
                new Product("The Legend of Zelda: Ocarina of Time", "Koji Kondo", 24.99,
                        "The iconic soundtrack from one of the greatest games of all time.", "https://cdn-images.dzcdn.net/images/cover/7c0225aeab30a7930fbc1a63b346187e/0x1900-000000-80-0-0.jpg"),
                new Product("Final Fantasy VII Original Soundtrack", "Nobuo Uematsu", 29.99,
                        "A legendary RPG soundtrack featuring Aerith’s Theme and One-Winged Angel.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLmfZa7hmTuMB9dS-ho3KfG_-gA6GLELjHgg&s"),
                new Product("Halo: Combat Evolved", "Martin O'Donnell & Michael Salvatori", 19.99,
                        "The powerful and cinematic score that defined a generation of FPS games.", "https://upload.wikimedia.org/wikipedia/en/f/f6/Halo-soundtrack-cover.jpg"),
                new Product("The Elder Scrolls V: Skyrim", "Jeremy Soule", 22.99,
                        "An epic orchestral soundtrack featuring the unforgettable Dragonborn theme.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBAL0pUMMYlvAzTluKsoYg34pQ_SQn-JPPEA&s"),
                new Product("Super Mario Galaxy Original Soundtrack", "Mahito Yokota & Koji Kondo", 21.99,
                        "An uplifting, orchestral soundtrack from one of the best Mario games.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBuArcJgWuboh8KZuEvD9W3sDAfhRy7ydccg&s"),
                new Product("NieR: Automata Original Soundtrack", "Keiichi Okabe", 27.99,
                        "A hauntingly beautiful soundtrack blending electronic, choral, and orchestral elements.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBKvBKYAvFU5OTQ_1MIiYygExAnCqnzjUThw&s"),
                new Product("Persona 5 Original Soundtrack", "Shoji Meguro", 23.99,
                        "A jazz-infused, stylish soundtrack that perfectly complements the game’s aesthetic.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvbvBzBtsd97TG-Qv7aRUQQwdBQfTgRmLV7A&s"),
                new Product("DOOM (2016) Original Soundtrack", "Mick Gordon", 20.99,
                        "Intense, pulse-pounding metal tracks that make demon-slaying even more epic.", "https://cdn-images.dzcdn.net/images/cover/30df9f6ef2a1d11f058868ebe2d29349/0x1900-000000-80-0-0.jpg"),
                new Product("Undertale Soundtrack", "Toby Fox", 15.99,
                        "An indie classic featuring Megalovania and a mix of chiptune and emotional tracks.", "https://external-preview.redd.it/yU4CX156zfrDDVI2Uq7XO-xV_yfB2IJTUqnu0duHI78.jpg?auto=webp&s=98a7a6e13f1d250594e46353743e9e91034a9a47"),
                new Product("Shadow of the Colossus", "Kow Otani", 18.99,
                        "A breathtaking soundtrack that elevates the emotional weight of the game.", "https://i1.sndcdn.com/artworks-ECN1HTm7QOtVkQuT-YjWxHQ-t500x500.jpg")
        );

        productRepo.saveAll(sampleProducts);
    }

}
