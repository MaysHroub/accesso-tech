package com.example.accessotech.dao;

import com.example.accessotech.model.Item;

import java.util.List;

public interface ItemDao {

    List<Item> findAllItems();
    List<String> findAllCategories();
    List<String> findAllManufacturers();
    List<String> findAllRatings();
    void saveAllItems();
    void deleteAllItems();
}
