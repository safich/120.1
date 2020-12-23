package com.company.Controller;

import com.company.Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private List<Product> products = new ArrayList<>();

    public void loadProducts(String filePath) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null ) {
            String[] s = line.split(";");
            Product p = new Product(Long.valueOf(s[0]), s[1], s[2], Float.valueOf(s[3]), Integer.valueOf(s[4]));
            products.add(p);
            }
        reader.close();
    }

    public void saveProducts(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Product p : products) {
            writer.write(p.getVendorCode() + ";" + p.getName() + ";" + p.getColor() + ";"
                    + p.getPrice() + ";" + p.getNumber());
        }
        writer.close();
    }

    public Product get(int i) {
        return products.get(i);
    }

    public List<Product> getList() {
        return products;
    }
}
