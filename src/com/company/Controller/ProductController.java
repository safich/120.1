package com.company.Controller;

import com.company.Controller.Storage.ProductsStorage;
import com.company.Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private List<Product> products = new ArrayList<>();
    private ProductsStorage productsStorage;

    public ProductController (ProductsStorage productsStorage) {
        this.productsStorage = productsStorage;
    }

    public void loadProducts(String filePath) throws IOException {
        productsStorage.loadProducts(filePath, products);
    }

    public void saveProducts(String filePath) throws IOException {
        productsStorage.saveProducts(filePath, products);
    }

    public Product get(int i) {
        return products.get(i);
    }

    public List<Product> getList() {
        return products;
    }

    public int getProductsCount() {
        return products.size();
    }

    public void reduceProducts(int i, int number) {
        products.get(i).setNumber(products.get(i).getNumber() - number);
    }

    public void resetProducts(int productIndex, int prevNumber) {
        products.get(productIndex).setNumber(products.get(productIndex).getNumber() + prevNumber);
    }
}
