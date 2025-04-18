package com.example.accessotech.dao;

import com.example.accessotech.model.Item;

import java.util.List;

public interface ItemDao {

    void saveAllItems(List<Item> items);
    void saveAllCategories(List<String> categories);
    void saveAllManufacturers(List<String> manufacturers);
    void saveAllRatings(List<String> ratings);
    List<Item> findAllItems();
    Item findItemById(int id);
    List<String> findAllCategories();
    List<String> findAllManufacturers();
    void deleteAllItems();
    void deleteItemById(int id);
    List<String> findAllRatings();
}
