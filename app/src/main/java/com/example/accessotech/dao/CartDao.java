package com.example.accessotech.dao;

import android.content.Context;

import com.example.accessotech.model.CartItem;
import com.example.accessotech.model.Item;
import com.example.accessotech.util.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

public class CartDao {

    private final Context context;
    private List<CartItem> cartItems;

    public CartDao(Context context) {
        this.context = context;
        cartItems = new ArrayList<>();
        if (SharedPrefsManager.isCartPreviouslySaved(context))
            cartItems = new ArrayList<>(SharedPrefsManager.loadCartItems(context));
    }

    public void saveAllCartItems() {
        SharedPrefsManager.saveCartItems(context, cartItems);
    }

    public List<CartItem> findAllCartItems() {
        return cartItems;
    }

    public void addItem(Item item) {
        CartItem cartItem = new CartItem(item);
        cartItems.add(cartItem);
    }

    public void removeItem(Item item) {
        CartItem cartItem = new CartItem(item);
        cartItems.remove(cartItem);
    }

    public boolean hasItem(Item item) {
        return cartItems.contains(new CartItem(item));
    }

    public void clear() {
        cartItems.clear();
    }

    public void incrementQuantity(CartItem cartItem) {
        cartItem.incrementQuantityInCart();
    }

    public void decrementQuantity(CartItem cartItem) {
        cartItem.decrementQuantityInCart();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public float getUpdatedTotalPrice() {
        float totalPrice = 0f;
        for (CartItem cartItem : cartItems)
            totalPrice += cartItem.getQuantityInCart() * cartItem.getItem().getUnitPrice();
        return totalPrice;
    }

}
