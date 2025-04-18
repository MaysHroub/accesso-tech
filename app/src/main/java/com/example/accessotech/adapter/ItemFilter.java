package com.example.accessotech.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemFilter {

    public static final int CATEGORY = 0, MANUFACTURER = 1, RATING = 2, PRICE = 3;

    private String category, manufacturer;
    private int rating, fromPrice, toPrice;

}
