package com.company.Model.Storage;

import com.company.Model.Product;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProductsStorage implements Serializable {
    private ArrayList<Product> products;

    public ProductsStorage() {
        products = new ArrayList<>();
    }

    public void loadProducts() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(getFilepath()));
        String line;
        while ((line = reader.readLine()) != null ) {
            String[] s = line.split(";");
            Product p = new Product(Long.valueOf(s[0]), s[1], s[2], Float.parseFloat(s[3]), Integer.parseInt(s[4]));
            products.add(p);
        }
        reader.close();
    }

    public void saveProducts() throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(getFilepath()), StandardCharsets.UTF_8);
        for (Product p : products) {
            writer.write(p.getVendorCode() + ";" + p.getName() + ";" + p.getColor() + ";"
                    + p.getPrice() + ";" + p.getNumber() + "\n");
        }
        writer.close();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getFilepath() throws IOException {
        BufferedReader pathfinder = new BufferedReader(new FileReader("Config"));
        String path = pathfinder.readLine();
        pathfinder.close();
        return path;
    }
}
