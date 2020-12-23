package com.company.View;

import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderStatus;
import com.company.View.UIModel.UIProductListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EditOrder extends JDialog implements ListSelectionListener {
    private JTextField orderDate;
    private JTextField customerName;
    private JTextField customerPhone;
    private JTextField deliveryAddress;
    private JTextField discount;

    private OrderStatus status;

    private ProductController pc;

    private UIProductListModel productListModel;
    private int selectedProduct;

    private boolean modalResult;

    public EditOrder(MainFrame owner, ProductController pc) {
        super(owner,"Опции заказа", true);
        this.pc = pc;
        status = OrderStatus.PREPARING;
        productListModel = new UIProductListModel(pc);

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
        p.add(new JLabel("Позиции заказа: "));
        JList<String> l = new JList<>(productListModel);
        l.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        l.setSelectedIndices(new int[productListModel.getSize()]);
        p.add(l);
        topPanel.add(p);

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> {
            modalResult = true;
            dispose();
        });
        p.add(btn);

        btn = new JButton("Отмена");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

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

        btn = new JButton("Отмена");
        btn.addActionListener(e -> {
            dispose();
        });
        p.add(btn);

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
            String s = (String)b.getSelectedItem();
            if (s.equals("Готовится")) status = OrderStatus.PREPARING;
            if (s.equals("Отгружен")) status = OrderStatus.SHIPPED;
            if (s.equals("Отменен")) status = OrderStatus.CANCELED;
        }
    };

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        selectedProduct = ((JList<?>)e.getSource()).getSelectedIndex();

    }
}
