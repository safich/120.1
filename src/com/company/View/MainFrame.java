package com.company.View;

import com.company.Controller.OrdersController;
import com.company.Controller.ProductController;
import com.company.Model.OrderStatus;
import com.company.View.UIModel.UIProductListModel;
import com.company.View.UIModel.UITableModel;

import javax.swing.*;
import java.awt.*;
import com.company.Model.Order;

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

        mi = new JMenuItem("Позиции заказа");
        mi.addActionListener(e -> viewDetails());
        m.add(mi);

        mi = new JMenuItem("Изменить");
        mi.addActionListener(e -> editOrder());
        m.add(mi);

        mi = new JMenuItem("Удалить");
        mi.addActionListener(e -> removeOrder());
        m.add(mi);

        mb.add(m);
        setJMenuBar(mb);
    }

    private void viewDetails() {
        int row = mainTable.getSelectedRow();
        if (row == -1)
            return;
      //  ViewDetails vo = new ViewDetails(this, productController.get(row));
      //  vo.setVisible(true);
       // vo.setLocationRelativeTo(this);
    }

    private void addOrder() {
        EditOrder eo = new EditOrder(this, productController);
        eo.setLocationRelativeTo(this);
        eo.setVisible(true);
        if(eo.isModalResult()) {
            String cn = eo.getCustomerName(),
                    cp = eo.getCustomerPhone(),
                    da = eo.getDeliveryAddress(),
                    d = eo.getDiscount();

            try {
                uiTableModel.addOrder(cn, cp, da, Float.valueOf(d));
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
            uiTableModel.editOrder(i, eo.getCustomerName(), eo.getCustomerPhone(), eo.getDeliveryAddress(),
                    Float.valueOf(eo.getDiscount()), eo.getStatus());
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
