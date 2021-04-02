package com.company.Model.Storage;

import com.company.Model.Order;

import java.io.*;
import java.util.ArrayList;

public class OrdersStorage implements Serializable {
    private ArrayList<Order> orders;

    public OrdersStorage() {
        orders = new ArrayList<>();
    }

    public void loadOrders() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Orders"))) {
            orders = (ArrayList<Order>)ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveOrders() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Orders"))) {
            oos.writeObject(orders);
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
