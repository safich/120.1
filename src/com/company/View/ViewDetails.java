package com.company.View;

import com.company.Controller.ProductController;
import com.company.Model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class ViewDetails extends JDialog {

    public ViewDetails(MainFrame owner, Product product) {
        super(owner,"Детали заказа", true);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel p;

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Название товара: " + product.getName()));
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("Стоимость: " + product.getPrice()));
        topPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel(("Количество: ") + product.getNumber()));
        topPanel.add(p);

        getContentPane().add(topPanel, BorderLayout.CENTER);

        pack();

    }

}
