package com.example.accessotech.model;

import java.util.ArrayList;
import java.util.List;

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

    public boolean addItem(Item item) {
        CartItem cartItem = new CartItem(item, 1);
        if (cartItems.contains(cartItem)) return false;
        cartItems.add(cartItem);
        return true;
    }

    public boolean removeItem(Item item) {
        CartItem cartItem = new CartItem(item, 0);
        return cartItems.remove(cartItem);
    }

    public boolean incrementQuantity(CartItem cartItem) {
        return cartItem.incrementQuantityInCart();
    }

    public boolean decrementQuantity(CartItem cartItem) {
        return cartItem.decrementQuantityInCart();
    }

}
