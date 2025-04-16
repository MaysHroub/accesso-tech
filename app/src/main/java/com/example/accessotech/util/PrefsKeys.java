package com.example.accessotech.util;

import lombok.Getter;

@Getter
public enum PrefsKeys {
    ACCOUNT_INFO_SHARED_PREF("account_info_shared_pref"),
    ACCOUNT_EXISTS("account_exists"),
    USERNAME("username"),
    EMAIL("email"),
    PASSWORD("password"),
    ADDRESS("address"),
    PHONE_NUMBER("phone_number"),
    CARD_NUMBER("card_number");

    private final String key;

    private PrefsKeys(String key) {
        this.key = key;
    }

}
