package com.company.View;

import com.company.Controller.ProductController;
import javax.swing.*;
import java.awt.*;

public class ViewProducts extends JFrame {
    public ViewProducts(ProductController productController) {
        super("Просмотр продуктов");

        setBounds(400, 300,1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        UIAddItemsMenuModel uiProductTableModel = new UIAddItemsMenuModel(productController);
        JTable productTable = new JTable(uiProductTableModel);
        Container container = getContentPane();
        container.add(productTable.getTableHeader(), BorderLayout.NORTH);
        container.add(new JScrollPane(productTable), BorderLayout.CENTER);
    }
}
