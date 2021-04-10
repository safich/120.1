package com.company.Controller;

import com.company.Model.Storage.ProductsStorage;
import com.company.Model.Product;
import java.io.*;

public class ProductController {
    private final ProductsStorage storage;

    public ProductController () {
        storage = new ProductsStorage();
    }

    public void start() throws IOException {
        storage.loadProducts();
    }

    public void end() throws IOException {
        storage.saveProducts();
    }

    public Product get(int i) {
        return storage.getProducts().get(i);
    }

    public int getProductsCount() {
        return storage.getProducts().size();
    }

    public void reduceProducts(int i, int number) {
        storage.getProducts().get(i).setNumber(storage.getProducts().get(i).getNumber() - number);
    }

    public void resetProducts(int productIndex, int prevNumber) {
        storage.getProducts().get(productIndex).setNumber(storage.getProducts().get(productIndex).getNumber() + prevNumber);
    }
}
