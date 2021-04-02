package com.company;

import com.company.Controller.OrderController;
import com.company.Controller.ProductController;
import com.company.View.MainFrame;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        OrderController orderController = new OrderController();
        orderController.start();

        ProductController productController = new ProductController();
        productController.start();

        MainFrame mainFrame = new MainFrame(orderController, productController);
        mainFrame.setVisible(true);
    }

}
