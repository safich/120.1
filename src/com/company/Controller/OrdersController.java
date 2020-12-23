package com.company.Controller;

import com.company.Controller.Storage.OrdersStorage;
import com.company.Model.Order;
import com.company.Model.OrderStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersController {
    private List<Order> orders = new ArrayList<>();

    public void loadOrders(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        if (ois.readObject() != null) {
            OrdersStorage os = (OrdersStorage) ois.readObject();
            orders = os.getList();
            ois.close();
        }
        else {
            ois.close();
            return;
        }
    }

    public void saveOrders(String filePath) throws IOException {
        OrdersStorage os = new OrdersStorage(orders);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(os);
        oos.close();
    }

    public void add(String customerName, String customerPhone, String deliveryAddress, float discount) {
        orders.add(new Order(new Date(), customerName, customerPhone, deliveryAddress, discount, OrderStatus.PREPARING));

    }

    public void removeOrder(int i) {
        orders.remove(i);
    }

    public int getOrdersCount() {
        return orders.size();
    }

    public Order get(int i) {
        return orders.get(i);
    }

}
