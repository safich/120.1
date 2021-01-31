package com.company.View;

import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.OrderStatus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class EditOrder extends JDialog {
    private JTextField orderDate;
    private JTextField customerName;
    private JTextField customerPhone;
    private JTextField deliveryAddress;
    private JTextField discount;
    private OrderStatus status;
    private ProductController productController;
    private boolean modalResult;
    private ArrayList<OrderItems> orderItems;
    private boolean marker;

    public EditOrder(MainFrame owner, ProductController productController) {
        super(owner,"Опции заказа", true);
        this.productController = productController;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("ФИО клиента:"));
        customerName = new JTextField(40);
        p.add(customerName);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Номер клиента:"));
        customerPhone = new JTextField(15);
        p.add(customerPhone);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Адрес клиента:"));
        deliveryAddress = new JTextField(50);
        p.add(deliveryAddress);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Скидка:"));
        discount = new JTextField(2);
        p.add(discount);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Дата заказа:"));
        orderDate = new JTextField(new Date().toString());
        orderDate.setEditable(false);
        p.add(orderDate);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addMenuButton = new JButton("Выбрать позиции");
        p.add(addMenuButton);
        addMenuButton.addActionListener(e -> addItems());

        topPanel.add(p);

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

//        btn = new JButton("Отмена");
//        btn.addActionListener(e -> {
//            dispose();
//        });
//        p.add(btn);

        topPanel.add(p);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        pack();
    }

    public EditOrder(MainFrame owner, Order order) {
        super(owner,"Изменение заказа", true);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("ФИО клиента:"));
        customerName = new JTextField(40);
        p.add(customerName);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Номер клиента:"));
        customerPhone = new JTextField(15);
        p.add(customerPhone);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Адрес клиента:"));
        deliveryAddress = new JTextField(50);
        p.add(deliveryAddress);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Скидка:"));
        discount = new JTextField(2);
        p.add(discount);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Статус: "));
        String[] stats = {"Готовится", "Отгружен", "Отменен"};
        JComboBox cb = new JComboBox(stats);
        cb.setAlignmentX(LEFT_ALIGNMENT);
        cb.addActionListener(boxListener);
        p.add(cb);
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Дата заказа:"));
        orderDate = new JTextField(new Date().toString());
        orderDate.setEditable(false);
        p.add(orderDate);
        topPanel.add(p);

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

//        btn = new JButton("Отмена");
//        btn.addActionListener(e -> {
//            dispose();
//        });
//        p.add(btn);

        topPanel.add(p);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        pack();

        customerName.setText(order.getCustomerName());
        customerPhone.setText(order.getCustomerPhoneNumber());
        deliveryAddress.setText(order.getDeliveryAddress());
        discount.setText(Float.toString(order.getDiscount()));
        orderDate.setText(order.getOrderDate().toString());
        orderDate.setEnabled(false);
    }

    ActionListener boxListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox b = (JComboBox)e.getSource();
            String s = b.getSelectedItem().toString();
            if (s.equals("Готовится")) status = OrderStatus.PREPARING;
            if (s.equals("Отгружен")) status = OrderStatus.SHIPPED;
            if (s.equals("Отменен")) status = OrderStatus.CANCELED;
        }
    };

    public void addItems() {
        if (!marker) {
            AddItemsMenu addItemsMenu = new AddItemsMenu(this, productController);
            addItemsMenu.setLocationRelativeTo(this);
            addItemsMenu.setVisible(true);

            if (addItemsMenu.isModalResult()) {
                try {
                    orderItems = new ArrayList<>();
                    for (int i = 0; i < addItemsMenu.getProductList().size(); i++) {
                        orderItems.add(new OrderItems(addItemsMenu.getProductList().get(i),
                                addItemsMenu.getProductList().get(i).getPrice(), addItemsMenu.getSelectedProductNumber(i)));
                    }
                    marker = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else JOptionPane.showMessageDialog(this, "Позиции уже выбраны");
    }

    public boolean isModalResult() {
        return modalResult;
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    public String getCustomerPhone() {
        return customerPhone.getText();
    }

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public String getDiscount() {
        return discount.getText();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<OrderItems> getOrderItems() {
        if (orderItems != null) return orderItems;
        else return new ArrayList<>();
    }
}
