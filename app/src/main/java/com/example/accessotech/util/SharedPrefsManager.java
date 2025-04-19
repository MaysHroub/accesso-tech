package com.example.accessotech.util;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.accessotech.dao.Cart;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.model.Item;
import com.example.accessotech.model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPrefsManager {

    public static void saveCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.CART_ITEMS_SHARED_PREF.getKey(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Cart.getInstance().getCartItems());
        editor.putString(PrefsKeys.CART.getKey(), json);
        editor.putBoolean(PrefsKeys.CART_SAVED.getKey(), true);
        editor.apply();
    }

    public static void loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.CART_ITEMS_SHARED_PREF.getKey(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(PrefsKeys.CART.getKey(), "[]");
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);
        Cart.getInstance().clear();
        Cart.getInstance().addAll(cartItems);
    }

    public static boolean isCartPreviouslySaved(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.CART_ITEMS_SHARED_PREF.getKey(), MODE_PRIVATE);
        return prefs.getBoolean(PrefsKeys.CART_SAVED.getKey(), false);
    }

    public static void saveAppData(Context context, List<Item> items) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ITEM_DATA_SHARED_PREF.getKey(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(PrefsKeys.DATA.getKey(), json);
        editor.putBoolean(PrefsKeys.DATA_SAVED.getKey(), true);
        editor.apply();
    }

    public static List<Item> loadAppData(Context context) {
        if (!isDataPreviouslySaved(context))
            return DataLoader.loadItems();
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ITEM_DATA_SHARED_PREF.getKey(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(PrefsKeys.DATA.getKey(), "[]");
        Type type = new TypeToken<List<Item>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private static boolean isDataPreviouslySaved(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ITEM_DATA_SHARED_PREF.getKey(), MODE_PRIVATE);
        return prefs.getBoolean(PrefsKeys.DATA_SAVED.getKey(), false);
    }

    public static void saveUserInfo(Context context, UserInfo userInfo) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ACCOUNT_INFO_SHARED_PREF.getKey(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PrefsKeys.ACCOUNT_EXISTS.getKey(), true);
        editor.putString(PrefsKeys.USERNAME.getKey(), userInfo.getName());
        editor.putString(PrefsKeys.EMAIL.getKey(), userInfo.getEmail());
        editor.putString(PrefsKeys.PASSWORD.getKey(), userInfo.getPassword());
        editor.putString(PrefsKeys.ADDRESS.getKey(), userInfo.getAddress());
        editor.putString(PrefsKeys.PHONE_NUMBER.getKey(), userInfo.getPhoneNumber());
        editor.putString(PrefsKeys.CARD_NUMBER.getKey(), userInfo.getCardNumber());
        editor.apply();
    }

    public static UserInfo loadUserInfo(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ACCOUNT_INFO_SHARED_PREF.getKey(), MODE_PRIVATE);
        return new UserInfo(
                prefs.getString(PrefsKeys.USERNAME.getKey(), "None"),
                prefs.getString(PrefsKeys.EMAIL.getKey(), "None"),
                prefs.getString(PrefsKeys.PASSWORD.getKey(), "None"),
                prefs.getString(PrefsKeys.ADDRESS.getKey(), "None"),
                prefs.getString(PrefsKeys.PHONE_NUMBER.getKey(), "None"),
                prefs.getString(PrefsKeys.CARD_NUMBER.getKey(), "None")
        );
    }

}
