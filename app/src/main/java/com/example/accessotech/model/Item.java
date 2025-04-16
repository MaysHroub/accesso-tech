package com.example.accessotech.model;

import androidx.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Item {

    private int id;
    private String name, description, category, manufacturer;
    private String imgUrl;
    private float unitPrice;
    private int quantityInStock;
    private int rating; // range: 0-5
    private int discount; // range: 0-100

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Item)) return false;
        return id == ((Item) obj).id;
    }

}
