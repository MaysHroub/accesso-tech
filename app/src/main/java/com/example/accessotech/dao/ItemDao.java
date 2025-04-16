package com.example.accessotech.dao;

import com.example.accessotech.model.Item;

import java.util.List;
import java.util.Set;

public interface ItemDao {

    void saveAllItems(List<Item> items);
    void saveAllCategories(Set<String> categories);
    void saveAllManufacturers(Set<String> manufacturers);
    List<Item> findAllItems();
    Item findItemById(int id);
    Set<String> findAllCategories();
    Set<String> findAllManufacturers();
    void deleteAllItems();
    void deleteItemById(int id);

}
