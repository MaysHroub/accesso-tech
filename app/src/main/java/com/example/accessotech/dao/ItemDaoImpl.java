package com.example.accessotech.dao;

import com.example.accessotech.model.Item;
import com.example.accessotech.util.DataLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemDaoImpl implements ItemDao {

    private final List<Item> items;
    private final List<String> categories, manufacturers;

    public ItemDaoImpl() {
        items = new ArrayList<>();
        categories = new ArrayList<>();
        manufacturers = new ArrayList<>();
        DataLoader dataLoader = new DataLoader(this);
        dataLoader.loadData();
    }

    @Override
    public void saveAllItems(List<Item> items) {
        this.items.addAll(items);
    }

    @Override
    public void saveAllCategories(List<String> categories) {
        this.categories.addAll(categories);
    }

    @Override
    public void saveAllManufacturers(List<String> manufacturers) {
        this.manufacturers.addAll(manufacturers);
    }

    @Override
    public List<Item> findAllItems() {
        return items;
    }

    @Override
    public Item findItemById(int id) {
        Item item = new Item();
        item.setId(id);
        int idx;
        return ((idx = items.indexOf(item)) != -1) ? items.get(idx) : null;
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
    public void deleteAllItems() {
        items.clear();
    }

    @Override
    public void deleteItemById(int id) {
        Item item = new Item();
        item.setId(id);
        items.remove(item);
    }
}
