package com.company.View;

import com.company.Controller.ProductController;
import com.company.View.UIModel.UIAddItemsMenuModel;

import javax.swing.*;
import java.awt.*;

public class ViewProducts extends JFrame {
    private UIAddItemsMenuModel uiProductTableModel;
    private JTable productTable;

    public ViewProducts(ProductController productController) {
        super("Просмотр продуктов");

        setBounds(400, 300,1000,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        uiProductTableModel = new UIAddItemsMenuModel(productController);
        productTable = new JTable(uiProductTableModel);
        Container c = getContentPane();
        c.add(productTable.getTableHeader(), BorderLayout.NORTH);
        c.add(new JScrollPane(productTable), BorderLayout.CENTER);
    }
}
