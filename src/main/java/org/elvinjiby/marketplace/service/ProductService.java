package org.elvinjiby.marketplace.service;

import org.elvinjiby.marketplace.model.Product;
import org.elvinjiby.marketplace.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getVisibleProducts() {
        return productRepository.findAll().stream()
                .filter(product -> !product.isHidden())
                .collect(Collectors.toList());
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProductbyId(Long id, String artist, double price, String description, String albumCoverURL) {
        Product product = getProductById(id);
        if (product != null) {
            product.setArtist(artist);
            product.setPrice(price);
            product.setDescription(description);
            product.setAlbumCoverURL(albumCoverURL);
            productRepository.save(product);
        }
    }

    public void toggleProductVisibility(Long id) {
        Product product = getProductById(id);
        if (product != null) {
            product.setHidden(!product.isHidden());
            productRepository.save(product);
        }
    }
}
