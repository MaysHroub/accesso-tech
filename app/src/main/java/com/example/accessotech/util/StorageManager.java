package com.example.accessotech.util;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.accessotech.model.Cart;
import com.example.accessotech.model.CartItem;
import com.example.accessotech.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StorageManager {

    public static void saveCartData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.CART_ITEMS_SHARED_PREF.getKey(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Cart.getInstance().getCartItems());
        editor.putString(PrefsKeys.CART.getKey(), json);
        editor.apply();
    }

    public static void loadCartData(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.CART_ITEMS_SHARED_PREF.getKey(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(PrefsKeys.CART.getKey(), "");
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItems = gson.fromJson(json, type);
        Cart.getInstance().setCartItems(cartItems);
    }

    public static void saveAppData(Context context, List<Item> items) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ITEM_DATA_SHARED_PREF.getKey(), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(PrefsKeys.DATA.getKey(), json);
        editor.apply();
    }

    public static List<Item> loadAppData(Context context, List<Item> items) {
        SharedPreferences prefs = context.getSharedPreferences(PrefsKeys.ITEM_DATA_SHARED_PREF.getKey(), MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(PrefsKeys.CART.getKey(), "");
        Type type = new TypeToken<List<Item>>() {}.getType();
        return gson.fromJson(json, type);
    }

}
