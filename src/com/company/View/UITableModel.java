package com.company.View;

import com.company.Controller.OrderController;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import com.company.Controller.ProductController;
import com.company.Model.Order;
import com.company.Model.OrderItems;
import com.company.Model.OrderStatus;
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
            String.class,
            String[].class,
            Double.class
    };

    private OrderController orderController;
    private ProductController productController;
    private List<TableModelListener> listeners;

    public UITableModel(OrderController orderController, ProductController productController) {
        this.orderController = orderController;
        this.productController = productController;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return orderController.getOrdersCount();
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
        Order order = orderController.get(rowIndex);
        switch (columnIndex) {
            case 0: return order.getOrderDate();
            case 1: return order.getCustomerName();
            case 2: return order.getCustomerPhoneNumber();
            case 3: return order.getDeliveryAddress();
            case 4: return order.getDiscount();
            case 5: return order.getStringOrderStatus();
            case 6: if (order.getOrderStatus().equals(OrderStatus.SHIPPED)) {
                return order.getShippingDate();
            } else return null;
            case 7: return order.getOrderItemsNames();
            case 8: return order.getOrderItemsPrice();
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

    public void addOrder(String customerName, String customerPhone, String deliveryAddress, float discount, ArrayList<OrderItems> orderItems) {
        orderController.add(customerName, customerPhone, deliveryAddress, discount, orderItems);
        int i = orderController.getOrdersCount() - 1;
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }

    public void removeOrder(int i) {
        orderController.removeOrder(i);
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
    }

    public void editOrder (int i, String customerName, String customerPhone, String deliveryAddress,
                           float discount, OrderStatus status) {
        Order order = orderController.get(i);
        order.setCustomerName(customerName);
        order.setCustomerPhoneNumber(customerPhone);
        order.setDeliveryAddress(deliveryAddress);
        order.setDiscount(discount);
        order.setOrderStatus(status);
        if (status.equals(OrderStatus.SHIPPED)) order.setShippingDate(new Date());
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }
}
