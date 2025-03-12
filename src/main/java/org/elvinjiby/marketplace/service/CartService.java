package org.elvinjiby.marketplace.service;

import org.elvinjiby.marketplace.model.CartItem;
import org.elvinjiby.marketplace.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SessionAttributes("cart")
public class CartService {
    private final Map<Long, CartItem> cart = new HashMap<>();

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cart.values());
    }

    public void clearCart() {
        cart.clear();
    }

    public void addToCart(Product product) {
        cart.putIfAbsent(product.getId(), new CartItem(product, 1));
    }

    public void removeFromCart(Long productId) {
        cart.remove(productId);
    }

    public void updateQuantity(Long productId, int quantity) {
        if (cart.containsKey(productId)) {
            if (quantity <= 0) {
                cart.remove(productId);
            }
            else {
                cart.get(productId).setQuantity(quantity);
            }
        }
    }

    public double getTotalCartPrice() {
        return cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

}
