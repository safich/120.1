package com.company.Controller.Storage;

import com.company.Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsStorage implements Serializable {
    private List<Product> list;

    public ProductsStorage(List<Product> list) {
        this.list = list;
    }

    public List<Product> getList() {
        return list;
    }


}
