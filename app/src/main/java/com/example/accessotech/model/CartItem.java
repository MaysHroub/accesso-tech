package com.example.accessotech.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItem {

    private Item item;
    private int quantityInCart;

    public boolean incrementQuantityInCart() {
        if (item.getQuantityInStock() == 0)
            return false;
        quantityInCart++;
        item.setQuantityInStock(item.getQuantityInStock() - 1);
        return true;
    }

    public boolean decrementQuantityInCart() {
        if (quantityInCart == 1)
            return false;
        quantityInCart--;
        item.setQuantityInStock(item.getQuantityInStock() + 1);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return item.equals(cartItem.item);
    }

}
