package com.example.accessotech.util;

import lombok.Getter;

@Getter
public enum PrefsKeys {
    MY_SHARED_PREFS("my_shared_pref"),
    HAS_ACCOUNT("has_account");

    private final String key;

    private PrefsKeys(String key) {
        this.key = key;
    }

}
