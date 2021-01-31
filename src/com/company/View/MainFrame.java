package com.company.View;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.Model.OrderItems;
import com.company.Model.OrderStatus;
import com.company.View.UIModel.UITableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private OrdersController ordersController;
    private ProductController productController;
    private UITableModel uiTableModel;
    private JTable mainTable;

    public MainFrame(OrdersController oc, ProductController pc) {
        super("Orders list");

        setBounds(400,300,1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.ordersController = oc;
        this.productController = pc;

        uiTableModel = new UITableModel(oc, pc);
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
    }

    private void viewDetails() {
        int i = mainTable.getSelectedRow();
        if(i == -1) return;
        ViewDetails viewDetails = new ViewDetails(this, ordersController.get(i), productController, ordersController);
        viewDetails.setLocationRelativeTo(this);
        viewDetails.setVisible(true);
    }

    private void viewProducts() {
        ViewProducts vp = new ViewProducts(productController);
        vp.setLocationRelativeTo(this);
        vp.setVisible(true);
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
        if (ordersController.get(i).getOrderStatus() != OrderStatus.PREPARING) {
            JOptionPane.showMessageDialog(this,"Заказ уже отгружен/отменен");
            return;
        }
        EditOrder eo = new EditOrder(this, ordersController.get(i));
        eo.setLocationRelativeTo(this);
        eo.setVisible(true);

        if(eo.isModalResult()) {
            String customerName = eo.getCustomerName(),
                    customerPhone = eo.getCustomerPhone(),
                    deliveryAddress = eo.getDeliveryAddress(),
                    discount = eo.getDiscount();
            OrderStatus status = eo.getStatus();
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
        String s = ordersController.get(i).toString();
        if(JOptionPane.showConfirmDialog(this, "Вы уверены, что хотите удалить заказ " + s + " ?",
                "Удаление заказа", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            uiTableModel.removeOrder(i);
        }
    }

}
