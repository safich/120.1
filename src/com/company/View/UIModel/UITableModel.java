package com.company.View.UIModel;

import com.company.Controller.OrdersController;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.OrderStatus;
import com.company.Model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UITableModel implements TableModel {

    private static final String[] COLUMN_HEADERS = {
            "Дата заказа",
            "ФИО клиента",
            "Номер клиента",
            "Адрес доставки",
            "Скидка",
            "Статус",
            "Дата отгрузки",
            "Товар",
            "Сумма"
    };

    private static final Class<?>[] COLUMN_TYPES = {
            Date.class,
            String.class,
            Long.class,
            String.class,
            Float.class,
            OrderStatus.class,
            Date.class,
            OrderItems.class,
            Integer.class
    };

    private OrdersController ordersController;
    private ProductController productController;
    private List<TableModelListener> listeners;

    public UITableModel(OrdersController ordersController, ProductController productController) {
        this.ordersController = ordersController;
        this.productController = productController;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return ordersController.getOrdersCount();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_HEADERS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_HEADERS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order o = ordersController.get(rowIndex);
        switch (columnIndex) {
            case 0: return o.getOrderDate();
            case 1: return o.getCustomerName();
            case 2: return o.getCustomerPhoneNumber();
            case 3: return o.getDeliveryAddress();
            case 4: return o.getDiscount();
            case 5: return o.getOrderStatus();
            case 6: if (o.getOrderStatus().equals(OrderStatus.SHIPPED)) {
                return o.getShippingDate();
            }
            case 7: return o.getOrderItems();
            case 8: return o.getOrderDate();
            default: return null;
            }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    private void fireTableModelEvent(TableModelEvent e) {
        for(TableModelListener l: listeners)
            l.tableChanged(e);
    }

    public void addOrder(String customerName, String customerPhone, String deliveryAddress, float discount) {
        ordersController.add(customerName, customerPhone, deliveryAddress, discount);
        int i = ordersController.getOrdersCount() - 1;
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    public void removeOrder(int i) {
        ordersController.removeOrder(i);
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }

    public void editOrder (int i, String customerName, String customerPhone, String deliveryAddress,
                           float discount, OrderStatus status) {
        Order o = ordersController.get(i);
        o.setCustomerName(customerName);
        o.setCustomerPhoneNumber(customerPhone);
        o.setDeliveryAddress(deliveryAddress);
        o.setDiscount(discount);
        o.setOrderStatus(status);
        if (status.equals(OrderStatus.SHIPPED)) o.setShippingDate(new Date());
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }
}
