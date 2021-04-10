package com.company.Controller;

import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.Storage.OrdersStorage;
import java.io.*;
import java.util.ArrayList;

public class OrderController {
    private final OrdersStorage storage;

    public OrderController() {
        storage = new OrdersStorage();
    }

    public void start() {
        storage.loadOrders();
    }

    public void end() throws IOException {
        storage.saveOrders();
    }

    public void add(String customerName,
                    String customerPhone,
                    String deliveryAddress,
                    float discount,
                    ArrayList<OrderItems> orderItems)
    {
        storage.getOrders().add(new Order(customerName, customerPhone, deliveryAddress, discount, orderItems));
    }

    public void removeOrder(int i) {
        storage.getOrders().remove(i);
    }

    public int getOrdersCount() {
        return storage.getOrders().size();
    }

    public Order get(int i) {
        return storage.getOrders().get(i);
    }
}
