package com.company;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.View.MainFrame;

import java.io.IOException;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        OrdersController oc = new OrdersController();
        //oc.loadOrders("");

        ProductController pc = new ProductController();
        //pc.loadProducts("");

        MainFrame f = new MainFrame(oc, pc);
        f.setVisible(true);

        //oc.saveOrders("");
    }
}
