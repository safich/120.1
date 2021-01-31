package com.company.Controller.Storage;

import com.company.Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsStorage implements Serializable {

    public List<Product> loadProducts(String filePath, List<Product> list) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null ) {
            String[] s = line.split(";");
            Product p = new Product(Long.valueOf(s[0]), s[1], s[2], Float.valueOf(s[3]), Integer.valueOf(s[4]));
            list.add(p);
        }
        reader.close();
        return list;
    }

    public void saveProducts(String filePath, List<Product> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Product p : list) {
            writer.write(p.getVendorCode() + ";" + p.getName() + ";" + p.getColor() + ";"
                    + p.getPrice() + ";" + p.getNumber());
        }
        writer.close();
    }

}
