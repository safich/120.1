package com.company;

import java.util.HashMap;

public class ProductsStorage {
    void loadList() {
        //TestCode
        HashMap<Integer, Product> products = new HashMap<>();
        products.put(1221, new Product("Xiaomi", 5000, 10));
    }
}
