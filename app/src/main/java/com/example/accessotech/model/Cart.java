package com.example.accessotech.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static Cart instance;

    private final List<CartItem> cartItems;
    private float totalPrice;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null)
            instance = new Cart();
        return instance;
    }

    public void addItem(Item item) {
        CartItem cartItem = new CartItem(item, 1);
        cartItems.add(cartItem);
        updateTotalPrice();
    }

    public void removeItem(Item item) {
        CartItem cartItem = new CartItem(item, 0);
        cartItems.remove(cartItem);
        updateTotalPrice();
    }

    public boolean hasItem(Item item) {
        return cartItems.contains(new CartItem(item, 0));
    }

    public void clear() {
        cartItems.clear();
        totalPrice = 0f;
    }

    public void reset() {
        cartItems.clear();
        // TODO: reset item quantity in stock
        totalPrice = 0f;
    }

    public void incrementQuantity(CartItem cartItem) {
        cartItem.incrementQuantityInCart();
        updateTotalPrice();
    }

    public void decrementQuantity(CartItem cartItem) {
        cartItem.decrementQuantityInCart();
        updateTotalPrice();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    private void updateTotalPrice() {
        totalPrice = 0f;
        for (CartItem cartItem : cartItems)
            totalPrice += cartItem.getQuantityInCart() * cartItem.getItem().getUnitPrice();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
