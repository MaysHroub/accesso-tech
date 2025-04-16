package com.example.accessotech.util;

import lombok.Getter;

@Getter
public enum PrefsKeys {
    MY_SHARED_PREFS("my_shared_pref"),
    ACCOUNT_EXISTS("account_exists");

    private final String key;

    private PrefsKeys(String key) {
        this.key = key;
    }

}
