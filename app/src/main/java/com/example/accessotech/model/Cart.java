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

    public void addAll(List<CartItem> cartItems) {
        this.cartItems.addAll(cartItems);
    }

    public void add(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void remove(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public boolean has(Item item) {
        return cartItems.contains(new CartItem(item));
    }

    public int indexOf(CartItem cartItem) {
        return cartItems.indexOf(cartItem);
    }

    public CartItem getAt(int index) {
        return cartItems.get(index);
    }

    public void clear() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public int size() {
        return cartItems.size();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public float getTotalPrice() {
        float totalPrice = 0f;
        for (CartItem cartItem : cartItems)
            totalPrice += cartItem.getQuantityInCart() * cartItem.getItem().getDiscountedPrice();
        return totalPrice;
    }

}
