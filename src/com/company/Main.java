package com.company;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.Controller.Storage.ProductsStorage;
import com.company.View.MainFrame;

import java.io.IOException;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OrdersController oc = new OrdersController();
        //oc.loadOrders("");

        ProductsStorage productsStorage = new ProductsStorage();
        ProductController pc = new ProductController(productsStorage);
        pc.loadProducts("C:\\Files\\products.csv");

        MainFrame f = new MainFrame(oc, pc);
        f.setVisible(true);
        //pc.saveProducts("C:\\Files\\products.csv");

        //oc.saveOrders("");
    }
}
