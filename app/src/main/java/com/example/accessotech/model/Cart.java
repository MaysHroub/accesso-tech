package com.example.accessotech.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cart {

    private static Cart instance;

    private final List<CartItem> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null)
            instance = new Cart();
        return instance;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public boolean addItem(Item item) {
        CartItem cartItem = new CartItem(item, 1);
        return !cartItems.contains(cartItem) && cartItems.add(cartItem);
    }

    public boolean removeItem(Item item) {
        CartItem cartItem = new CartItem(item, 0);
        return cartItems.remove(cartItem);
    }

    public boolean hasItem(Item item) {
        return cartItems.contains(new CartItem(item, 0));
    }

    public boolean incrementQuantity(CartItem cartItem) {
        return cartItem.incrementQuantityInCart();
    }

    public boolean decrementQuantity(CartItem cartItem) {
        return cartItem.decrementQuantityInCart();
    }

}
