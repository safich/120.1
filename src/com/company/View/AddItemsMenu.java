package com.company.View;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.Product;
import com.company.View.UIModel.UIAddItemsMenuModel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddItemsMenu extends JDialog {
    private ProductController productController;
    private UIAddItemsMenuModel uiAddItemsMenuModel;
    private JTable addItemsMenuTable;
    private JTextField addedItems;
    private ArrayList<Product> tempProductList;
    private boolean modalResult;
    private ArrayList<Integer> selectedProductNumber;

    public AddItemsMenu(EditOrder owner, ProductController productController) {
        super(owner,"Позиции товаров", true);

        tempProductList = new ArrayList<>();
        selectedProductNumber = new ArrayList<>();

        setBounds(300,200,1200,600);
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.productController = productController;
        uiAddItemsMenuModel = new UIAddItemsMenuModel(productController);
        addItemsMenuTable = new JTable(uiAddItemsMenuModel);
        Container c = getContentPane();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        tablePanel.add(addItemsMenuTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(addItemsMenuTable), BorderLayout.CENTER);

        tablePanel.add(new JLabel("Добавлено:"));
        addedItems = new JTextField();
        addedItems.setEditable(false);
        tablePanel.add(addedItems);

        JButton addButton = new JButton("Добавить");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> addingItems());

        JButton saveButton = new JButton("Сохранить");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            modalResult = true;
            dispose();
        });

        topPanel.add(tablePanel);
        topPanel.add(buttonPanel);
        c.add(topPanel, BorderLayout.CENTER);
        pack();
    }

    public AddItemsMenu(ViewDetails owner, ProductController productController) {
        super(owner,"Позиции товаров", true);

        tempProductList = new ArrayList<>();
        selectedProductNumber = new ArrayList<>();

        setBounds(300,200,1200,600);
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.productController = productController;
        uiAddItemsMenuModel = new UIAddItemsMenuModel(productController);
        addItemsMenuTable = new JTable(uiAddItemsMenuModel);
        Container c = getContentPane();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        tablePanel.add(addItemsMenuTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(addItemsMenuTable), BorderLayout.CENTER);

        tablePanel.add(new JLabel("Добавлено:"));
        addedItems = new JTextField();
        addedItems.setEditable(false);
        tablePanel.add(addedItems);

        JButton addButton = new JButton("Добавить");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> addingItems());

        JButton saveButton = new JButton("Сохранить");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            modalResult = true;
            dispose();
        });

        topPanel.add(tablePanel);
        topPanel.add(buttonPanel);
        c.add(topPanel, BorderLayout.CENTER);
        pack();
    }

   /* public AddItemsMenu(ViewDetails owner, ProductController productController, int prevNumber) {
        super(owner,"Позиции товаров", true);

        tempProductList = new ArrayList<>();

        setBounds(300,200,1200,600);
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.productController = productController;
        uiAddItemsMenuModel = new UIAddItemsMenuModel(productController);
        addItemsMenuTable = new JTable(uiAddItemsMenuModel);
        Container c = getContentPane();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        tablePanel.add(addItemsMenuTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(new JScrollPane(addItemsMenuTable), BorderLayout.CENTER);

        tablePanel.add(new JLabel("Добавлено:"));
        addedItems = new JTextField();
        addedItems.setEditable(false);
        tablePanel.add(addedItems);

        JButton addButton = new JButton("Добавить");
        buttonPanel.add(addButton);
        addButton.addActionListener(e -> editingItems(prevNumber));

        JButton saveButton = new JButton("Сохранить");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            modalResult = true;
            dispose();
        });

        topPanel.add(tablePanel);
        topPanel.add(buttonPanel);
        c.add(topPanel, BorderLayout.CENTER);
        pack();
    }*/

    public void addingItems() {
        AddItemsNumberMenu addItemsNumberMenu = new AddItemsNumberMenu(this);
        addItemsNumberMenu.setVisible(true);
        if (addItemsNumberMenu.isModalResult()) {
            try {
                tempProductList.add(productController.get(addItemsMenuTable.getSelectedRow()));
                uiAddItemsMenuModel.addingItem(addItemsMenuTable.getSelectedRow(), addItemsNumberMenu.getNumber());
                selectedProductNumber.add(addItemsNumberMenu.getNumber());

                addedItems.setText("Добавлено: " + productController.get(addItemsMenuTable.getSelectedRow()).getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /*public void editingItems(int prevNumber) {
        AddItemsNumberMenu addItemsNumberMenu = new AddItemsNumberMenu(this);
        addItemsNumberMenu.setVisible(true);
        if (addItemsNumberMenu.isModalResult()) {
            try {
                tempProductList.add(productController.get(addItemsMenuTable.getSelectedRow()));
                uiAddItemsMenuModel.addingItem(addItemsMenuTable.getSelectedRow(), addItemsNumberMenu.getNumber());
                uiAddItemsMenuModel.resetPrevItems(addItemsMenuTable.getSelectedRow(), prevNumber);
                selectedProductNumber.add(addItemsNumberMenu.getNumber());

                addedItems.setText("Добавлено: " + productController.get(addItemsMenuTable.getSelectedRow()).getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/

    public ArrayList<Product> getProductList() {
        return tempProductList;
    }

    public boolean isModalResult() {
        return modalResult;
    }

    public int getSelectedProductNumber(int i) {
        return selectedProductNumber.get(i);
    }

    /*public void fireTableReset(ArrayList<OrderItems> list, OrderItems orderItem) {
        uiAddItemsMenuModel.resetPrevItems(list.indexOf(orderItem));
    }*/

    public class AddItemsNumberMenu extends JDialog {
        private JTextField textField;
        private boolean modalResult;

        public AddItemsNumberMenu(AddItemsMenu owner) {
            super(owner,"Количество товара",true);

            setBounds(700,500,200,200);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
            JPanel topPanel = new JPanel();
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
            topPanel.add(new JLabel("Введите количество:"));
            textField = new JTextField(3);

            topPanel.add(textField);
            JButton okButton = new JButton("ОК");
            bottomPanel.add(okButton);
            okButton.addActionListener(e -> okButtonAction());
            JButton cancelButton = new JButton("Отмена");
            bottomPanel.add(cancelButton);
            cancelButton.addActionListener(e -> dispose());
            mainPanel.add(topPanel);
            mainPanel.add(bottomPanel);

            getContentPane().add(mainPanel);
            pack();
        }

        public void okButtonAction() {
            try {
                if (Integer.parseInt(textField.getText()) > productController.get(addItemsMenuTable.getSelectedRow()).getNumber()
                        | Integer.parseInt(textField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Введено недопустимое количество");
                    return;
                } else {
                    modalResult = true;
                    dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error",JOptionPane.ERROR_MESSAGE);
            }
        }

        public int getNumber() {
            return Integer.parseInt(textField.getText());
        }

        public boolean isModalResult() {
            return modalResult;
        }
    }

}
