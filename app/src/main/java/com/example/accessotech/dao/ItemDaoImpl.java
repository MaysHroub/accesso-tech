package com.example.accessotech.dao;

import android.content.Context;

import com.example.accessotech.model.Item;
import com.example.accessotech.util.DataLoader;
import com.example.accessotech.util.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private final Context context;

    public ItemDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Item> findAllItems() {
        return new ArrayList<>(SharedPrefsManager.loadAppData(context));
    }

    @Override
    public void saveAllItems(List<Item> items) {
        SharedPrefsManager.saveAppData(context, items);
    }

}
