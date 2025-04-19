package com.example.accessotech.model;

import lombok.Getter;

@Getter
public class CartItem {

    private final Item item;
    private int quantityInCart;

    public CartItem(Item item) {
        this.item = item;
        quantityInCart = 1;
    }

    public void incrementQuantityInCart() {
        if (item.getQuantityInStock() == 0 || quantityInCart >= item.getQuantityInStock())
            return;
        quantityInCart++;
    }

    public void decrementQuantityInCart() {
        if (quantityInCart == 1)
            return;
        quantityInCart--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return item.equals(cartItem.item);
    }

}
