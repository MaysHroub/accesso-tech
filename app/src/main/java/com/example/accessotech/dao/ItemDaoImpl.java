package com.example.accessotech.dao;

import android.content.Context;

import com.example.accessotech.model.Item;
import com.example.accessotech.util.DataLoader;
import com.example.accessotech.util.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private Context context;
    private List<Item> items;
    private final List<String> categories, manufacturers, ratings;

    public ItemDaoImpl(Context context) {
        this.context = context;
        DataLoader dataLoader = new DataLoader();
        categories = new ArrayList<>(dataLoader.loadCategories());
        manufacturers = new ArrayList<>(dataLoader.loadManufacturers());
        ratings = new ArrayList<>(dataLoader.loadRatings());

        if (SharedPrefsManager.isDataPreviouslySaved(context))
            items = new ArrayList<>(SharedPrefsManager.loadAppData(context));
        else {
            items = new ArrayList<>(dataLoader.loadItems());
            SharedPrefsManager.saveAppData(context, items);
        }
    }

    @Override
    public List<Item> findAllItems() {
        return items;
    }

    @Override
    public List<String> findAllCategories() {
        return categories;
    }

    @Override
    public List<String> findAllManufacturers() {
        return manufacturers;
    }

    @Override
    public List<String> findAllRatings() {
        return ratings;
    }

    @Override
    public void saveAllItems() {
        SharedPrefsManager.saveAppData(context, items);
    }

    @Override
    public void deleteAllItems() {
        items.clear();
    }

}
