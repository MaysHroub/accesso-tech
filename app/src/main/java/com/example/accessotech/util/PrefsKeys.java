package com.example.accessotech.util;

import lombok.Getter;

@Getter
public enum PrefsKeys {
    CART_ITEMS_SHARED_PREF("cart_items_shared_pref"),
    CART("cart"),
    CART_SAVED("is_cart_saved"),
    ITEM_DATA_SHARED_PREF("item_data_shared_pref"),
    DATA("data"),
    DATA_SAVED("is_data_saved"),
    ACCOUNT_INFO_SHARED_PREF("account_info_shared_pref"),
    ACCOUNT_EXISTS("account_exists"),
    USERNAME("username"),
    EMAIL("email"),
    PASSWORD("password"),
    ADDRESS("address"),
    PHONE_NUMBER("phone_number"),
    CARD_NUMBER("card_number");

    private final String key;

    PrefsKeys(String key) {
        this.key = key;
    }

}
