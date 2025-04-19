package com.example.accessotech.dao;

import com.example.accessotech.model.Item;

import java.util.List;

public interface ItemDao {

    List<Item> findAllItems();
    void saveAllItems(List<Item> items);

}
