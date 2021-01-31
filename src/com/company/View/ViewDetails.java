package com.company.View;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.Product;
import com.company.View.UIModel.UIAddItemsMenuModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class ViewDetails extends JDialog {
    private ProductController productController;
    private OrdersController ordersController;
    private ArrayList<OrderItems> orderItems;
    private boolean modalResult;

    public ViewDetails(MainFrame owner, Order order, ProductController productController, OrdersController ordersController) {
        super(owner,"Детали заказа", true);
        this.productController = productController;
        this.ordersController = ordersController;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        for (OrderItems oi : order.getOrderItems()) {
            p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(new JLabel("Артикул: " + oi.getProduct().getVendorCode()));
            p.add(new JLabel("Название товара: " + oi.getProduct().getName()));
            p.add(new JLabel("Стоимость: " + oi.getBuyPrice()));
            p.add(new JLabel("Количество: " + oi.getAmount()));
            JButton editButton = new JButton("Изменить");
            JButton clearButton = new JButton("Удалить");
            p.add(editButton);
            p.add(clearButton);
            /*editButton.addActionListener(e -> editingProducts(oi.getAmount()));*/
            clearButton.addActionListener(e -> {
                clearPosition(order.getOrderItems(), oi, productController);
                JOptionPane.showMessageDialog(this, "Позиция удалена");
                modalResult = true;
                dispose();
            });
            topPanel.add(p);
        }
        p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("Сохранить");
        JButton addButton = new JButton("Добавить");
        okButton.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        addButton.addActionListener(e -> {
            addingProducts(productController, order);
            JOptionPane.showMessageDialog(this, "Позиция добавлена");
        });
        p.add(okButton);
        p.add(addButton);
        topPanel.add(p);

        getContentPane().add(topPanel, BorderLayout.CENTER);
        pack();
    }

    public void addingProducts(ProductController productController, Order order) {
        AddItemsMenu addItemsMenu = new AddItemsMenu(this, productController);
        addItemsMenu.setVisible(true);
        addItemsMenu.setLocationRelativeTo(this);
        if(addItemsMenu.isModalResult()) {
            for (Product p : addItemsMenu.getProductList()) {
                order.getOrderItems().add(new OrderItems(p,p.getPrice(), addItemsMenu.getSelectedProductNumber(addItemsMenu.getProductList().indexOf(p))));
            }
        }
    }

   /* public void editingProducts(int i) {
        AddItemsMenu addItemsMenu = new AddItemsMenu(this, productController, i);
        addItemsMenu.setVisible(true);
        addItemsMenu.setLocationRelativeTo(this);
        if (addItemsMenu.isModalResult()) {
        }
    }*/

    public void clearPosition(ArrayList<OrderItems> list, OrderItems orderItem, ProductController productController) {
        productController.resetProducts(list.indexOf(orderItem), orderItem.getAmount());
        list.remove(orderItem);
        /*fireTableReset(list, orderItem);*/
    }

    /*public void fireTableReset(ArrayList<OrderItems> list, OrderItems orderItem) {
        AddItemsMenu addItemsMenu = new AddItemsMenu(this, productController);
        addItemsMenu.fireTableReset(list, orderItem);
    }*/

    public boolean isModalResult() {
        return modalResult;
    }

}
