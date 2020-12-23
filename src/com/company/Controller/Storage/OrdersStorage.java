package com.company.Controller.Storage;

import com.company.Controller.OrdersController;
import com.company.Model.Order;

import java.io.*;
import java.util.List;

public class OrdersStorage implements Serializable {
    private List<Order> list;

    public OrdersStorage(List<Order> list) {
        this.list = list;
    }

    public List<Order> getList() {
        return list;
    }
}
