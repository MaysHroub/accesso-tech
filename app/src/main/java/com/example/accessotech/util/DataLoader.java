package com.example.accessotech.util;

import com.example.accessotech.R;
import com.example.accessotech.dao.ItemDao;
import com.example.accessotech.model.Item;

import java.util.List;
import java.util.Set;

public class DataLoader {

    public static List<String> loadRatings() {
        return List.of("1", "2", "3", "4", "5");
    }

    public static List<String> loadCategories() {
        return List.of(
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
        );
    }

    public static List<String> loadManufacturers() {
        return List.of(
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
        );
    }

    public static List<Item> loadItems() {
        return List.of(
                new Item(1, "AirPods Pro", "Wireless earbuds with active noise cancellation.", "Earbuds", "Apple", R.drawable.airpods_pro, 249.99f, 15, 5, 10),
                new Item(2, "Galaxy Buds 2", "Compact earbuds with clear sound and ANC.", "Earbuds", "Samsung", R.drawable.galaxy_buds_2, 149.99f, 20, 4, 15),
                new Item(3, "Anker Soundcore Life P3", "Bass-heavy earbuds with noise isolation.", "Earbuds", "Anker", R.drawable.soundcore_life_p3, 79.99f, 30, 4, 20),
                new Item(4, "Sony WH-1000XM5", "Industry-leading noise-canceling headphones.", "Headphones", "Sony", R.drawable.sony_wh1000xm5, 349.99f, 10, 5, 5),
                new Item(5, "JBL Tune 510BT", "Wireless headphones with pure bass.", "Headphones", "JBL", R.drawable.jbl_tune_510bt, 49.99f, 25, 4, 30),
                new Item(6, "Xiaomi Mi Charger 33W", "Fast charging for various phones.", "Chargers", "Xiaomi", R.drawable.mi_charger_33w, 19.99f, 50, 3, 10),
                new Item(7, "Baseus 65W GaN Charger", "Compact multi-port charger.", "Chargers", "Baseus", R.drawable.baseus_gan_charger, 39.99f, 40, 4, 25),
                new Item(8, "Logitech K380 Keyboard", "Compact Bluetooth keyboard.", "Keyboards", "Logitech", R.drawable.k380_keyboard, 39.99f, 18, 4, 15),
                new Item(9, "Lenovo 20,000mAh Power Bank", "High-capacity power bank with dual output.", "Power Banks", "Lenovo", R.drawable.lenovo_powerbank, 29.99f, 35, 4, 10),
                new Item(10, "Razer Basilisk V3", "High-performance gaming mouse.", "Mice", "Razer", R.drawable.razer_basilisk_v3, 59.99f, 12, 5, 5),
                new Item(11, "Ugreen USB-C Hub", "7-in-1 hub with HDMI and USB 3.0.", "USB Hubs", "Ugreen", R.drawable.ugreen_usb_c_hub, 34.99f, 15, 4, 18),
                new Item(12, "Beats Flex", "Neckband-style wireless earbuds.", "Earbuds", "Beats", R.drawable.beats_flex, 69.99f, 16, 4, 15),
                new Item(13, "Aukey Webcam 1080p", "Full HD webcam with noise-reducing mic.", "Webcams", "Aukey", R.drawable.aukey_webcam, 49.99f, 10, 4, 20),
                new Item(14, "HyperX Cloud Alpha", "Wired gaming headset with rich audio.", "Headphones", "HyperX", R.drawable.hyperx_cloud_alpha, 89.99f, 14, 5, 10),
                new Item(15, "Beats Studio Buds", "Noise-canceling earbuds with clear sound.", "Earbuds", "Beats", R.drawable.beats_studio_buds, 149.99f, 19, 4, 15),
                new Item(16, "Samsung Wireless Charger Duo", "Charge phone and watch simultaneously.", "Chargers", "Samsung", R.drawable.samsung_charger_duo, 59.99f, 25, 4, 10),
                new Item(17, "HyperX Wrist Rest", "Memory foam wrist rest for keyboards.", "Keyboards", "HyperX", R.drawable.hyperx_wrist_rest, 24.99f, 18, 4, 15),
                new Item(18, "Apple Watch Series 8", "Advanced fitness tracking and ECG.", "Smart Watches", "Apple", R.drawable.apple_watch_8, 399.99f, 8, 5, 5),
                new Item(19, "Xiaomi Mi Band 7", "Affordable fitness band with AMOLED display.", "Smart Watches", "Xiaomi", R.drawable.mi_band_7, 49.99f, 35, 4, 15),
                new Item(20, "Ugreen HDMI Adapter", "4K output HDMI to USB-C adapter.", "Adapters", "Ugreen", R.drawable.ugreen_hdmi_adapter, 19.99f, 25, 4, 20),
                new Item(21, "Logitech MX Master 3S", "Ergonomic mouse for productivity.", "Mice", "Logitech", R.drawable.mx_master_3s, 99.99f, 10, 5, 10),
                new Item(22, "Lenovo Laptop Stand", "Adjustable and foldable stand.", "Laptop Stands", "Lenovo", R.drawable.lenovo_laptop_stand, 29.99f, 20, 4, 18),
                new Item(23, "Razer Cooling Pad", "RGB cooling pad for gaming laptops.", "Cooling Pads", "Razer", R.drawable.razer_cooling_pad, 49.99f, 12, 4, 10),
                new Item(24, "JBL Flip 6", "Waterproof Bluetooth speaker with punchy bass.", "Bluetooth Speakers", "JBL", R.drawable.jbl_flip_6, 129.99f, 18, 5, 15)
        );
    }

}
