package com.company.View;

import com.company.Controller.ProductController;
import com.company.Model.Product;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class UIAddItemsMenuModel implements TableModel {
    private static final String[] COLUMN_HEADERS = {
            "Артикул",
            "Наименование",
            "Цвет",
            "Цена",
            "Остаток"
    };

    private static final Class<?>[] COLUMN_TYPES = {
            Long.class,
            String.class,
            String.class,
            float.class,
            int.class
    };

    private ProductController productController;
    private List<TableModelListener> listeners;

    public UIAddItemsMenuModel(ProductController productController) {
        this.productController = productController;
        this.listeners = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return productController.getProductsCount();
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
        Product product = productController.get(rowIndex);
        switch (columnIndex) {
            case 0: return product.getVendorCode();
            case 1: return product.getName();
            case 2: return product.getColor();
            case 3: return product.getPrice();
            case 4: return product.getNumber();
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
        for(TableModelListener l : listeners)
            l.tableChanged(e);
    }

    public void addingItem(int i, int number) {
        productController.reduceProducts(i, number);
        fireTableModelEvent(new TableModelEvent(this, i, i, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }

    /*public void resetPrevItems(int index) {
        fireTableModelEvent(new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE));
    }*/


}
