package com.company.View;

import com.company.Controller.OrderController;
import com.company.Controller.ProductController;
import com.company.Model.OrderItems;
import com.company.Model.OrderStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private final OrderController orderController;
    private final ProductController productController;
    private final UITableModel uiTableModel;
    private final JTable mainTable;

    public MainFrame(OrderController orderController, ProductController productController) {
        super("Orders list");

        setBounds(400,300,1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.orderController = orderController;
        this.productController = productController;

        uiTableModel = new UITableModel(orderController, productController);
        mainTable = new JTable(uiTableModel);
        Container c = getContentPane();
        c.add(mainTable.getTableHeader(), BorderLayout.NORTH);
        c.add(new JScrollPane(mainTable), BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Опции");
        JMenuItem mi;

        mi = new JMenuItem("Добавить новый");
        mi.addActionListener(e -> addOrder());
        m.add(mi);

        mi = new JMenuItem("Редактировать позиции");
        mi.addActionListener(e -> viewDetails());
        m.add(mi);

        mi = new JMenuItem("Изменить данные");
        mi.addActionListener(e -> editOrder());
        m.add(mi);

        mi = new JMenuItem("Удалить");
        mi.addActionListener(e -> removeOrder());
        m.add(mi);

        mi = new JMenuItem("Просмотр остатков");
        mi.addActionListener(e -> viewProducts());
        m.add(mi);

        mb.add(m);
        setJMenuBar(mb);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    orderController.end();
                    productController.end();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void viewDetails() {
        int i = mainTable.getSelectedRow();
        if(i == -1) return;
        ViewDetails viewDetails = new ViewDetails(this, orderController.get(i), productController, orderController);
        viewDetails.setLocationRelativeTo(this);
        viewDetails.setVisible(true);
    }

    private void viewProducts() {
        ViewProducts viewProducts = new ViewProducts(productController);
        viewProducts.setLocationRelativeTo(this);
        viewProducts.setVisible(true);
    }

    private void addOrder() {
        EditOrder editOrderMenu = new EditOrder(this, productController);
        editOrderMenu.setLocationRelativeTo(this);
        editOrderMenu.setVisible(true);
        if(editOrderMenu.isModalResult()) {
            String customerName = editOrderMenu.getCustomerName(),
                    customerPhone = editOrderMenu.getCustomerPhone(),
                    deliveryAddress = editOrderMenu.getDeliveryAddress(),
                    discount = editOrderMenu.getDiscount();
            ArrayList<OrderItems> orderItems = editOrderMenu.getOrderItems();
            try {
                uiTableModel.addOrder(customerName, customerPhone, deliveryAddress, Float.parseFloat(discount), orderItems);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editOrder() {
        int i = mainTable.getSelectedRow();
        if (i == -1) return;
        if (orderController.get(i).getOrderStatus() != OrderStatus.PREPARING) {
            JOptionPane.showMessageDialog(this,"Заказ уже отгружен/отменен");
            return;
        }
        EditOrder editOrder = new EditOrder(this, orderController.get(i));
        editOrder.setLocationRelativeTo(this);
        editOrder.setVisible(true);

        if(editOrder.isModalResult()) {
            String customerName = editOrder.getCustomerName(),
                    customerPhone = editOrder.getCustomerPhone(),
                    deliveryAddress = editOrder.getDeliveryAddress(),
                    discount = editOrder.getDiscount();
            OrderStatus status = editOrder.getStatus();
            if (status == null) status = OrderStatus.PREPARING;
            try {
            uiTableModel.editOrder(i, customerName, customerPhone, deliveryAddress, Float.parseFloat(discount), status);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void removeOrder() {
        int i = mainTable.getSelectedRow();
        if(i == -1) return;
        String s = orderController.get(i).toString();
        if(JOptionPane.showConfirmDialog(this, "Вы уверены, что хотите удалить заказ " + s + " ?",
                "Удаление заказа", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            uiTableModel.removeOrder(i);
        }
    }
}
