package com.example.accessotech.util;

import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.model.Item;

import java.util.List;
import java.util.Set;

public class DataLoader {

    private final ItemDao itemDao;

    public DataLoader(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void loadData() {
        loadCategories();
        loadManufacturers();
        loadRatings();
        loadItems();
    }

    private void loadRatings() {
        itemDao.saveAllRatings(List.of("1", "2", "3", "4", "5"));
    }

    private void loadCategories() {
        itemDao.saveAllCategories(List.of(
                "Earbuds",
                "Headphones",
                "Chargers",
                "Power Banks",
                "Phone Cases",
                "Screen Protectors",
                "Smart Watches",
                "Cables",
                "Adapters",
                "Bluetooth Speakers",
                "Keyboards",
                "Mice",
                "USB Hubs",
                "Webcams",
                "Laptop Stands",
                "Cooling Pads"
        ));
    }

    private void loadManufacturers() {
        itemDao.saveAllManufacturers(List.of(
                "Apple",
                "Samsung",
                "Anker",
                "Sony",
                "JBL",
                "Xiaomi",
                "Baseus",
                "Logitech",
                "Lenovo",
                "Huawei",
                "Razer",
                "Ugreen",
                "Aukey",
                "HyperX",
                "Beats"
        ));
    }

    private void loadItems() {
        List<Item> items = List.of(
                new Item(1, "AirPods Pro", "Wireless earbuds with active noise cancellation.",
                        "Earbuds", "Apple", "https://example.com/images/airpods_pro.png",
                        249.99f, 15, 5, 10),

                new Item(2, "Galaxy Buds2", "Comfortable, noise-canceling wireless earbuds.",
                        "Earbuds", "Samsung", "https://example.com/images/galaxy_buds2.png",
                        149.99f, 20, 4, 5),

                new Item(3, "Anker Soundcore", "High quality sound with deep bass.",
                        "Headphones", "Anker", "https://example.com/images/soundcore.png",
                        89.99f, 25, 4, 15),

                new Item(4, "JBL Flip 6", "Portable Bluetooth speaker with powerful sound.",
                        "Speakers", "JBL", "https://example.com/images/jbl_flip6.png",
                        119.99f, 10, 4, 20),

                new Item(5, "Sony WH-1000XM5", "Top-tier noise canceling headphones.",
                        "Headphones", "Sony", "https://example.com/images/sony_xm5.png",
                        349.99f, 8, 5, 0),

                new Item(6, "Baseus GaN Charger", "65W fast charging adapter with USB-C.",
                        "Chargers", "Baseus", "https://example.com/images/baseus_gan.png",
                        49.99f, 30, 4, 25),

                new Item(7, "Logitech MX Master 3", "High precision wireless mouse.",
                        "Mice", "Logitech", "https://example.com/images/mx_master3.png",
                        99.99f, 12, 2, 0),

                new Item(8, "Razer BlackWidow V3", "Mechanical gaming keyboard with RGB.",
                        "Keyboards", "Razer", "https://example.com/images/blackwidow_v3.png",
                        139.99f, 18, 4, 10),

                new Item(9, "HyperX Cloud II", "Gaming headset with 7.1 surround sound.",
                        "Headphones", "HyperX", "https://example.com/images/cloud_ii.png",
                        89.99f, 14, 5, 5),

                new Item(10, "Ugreen USB-C Hub", "Multiport hub with HDMI and SD card slots.",
                        "Accessories", "Ugreen", "https://example.com/images/ugreen_hub.png",
                        39.99f, 22, 3, 15)
        );
        itemDao.saveAllItems(items);
    }

}
