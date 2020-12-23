package com.company.View.UIModel;

import com.company.Controller.ProductController;

import javax.swing.*;
import java.util.ArrayList;

public class UIProductListModel extends AbstractListModel<String> {
    private ArrayList<String> pl;
    private ProductController pc;

    public UIProductListModel(ProductController pc) {
        this.pc = pc;
        this.pl = new ArrayList<>();
        for (int i = 0; i < pc.getList().size(); i++) {
            pl.add(pc.get(i).toString());
        }
    }

    @Override
    public int getSize() {
        return pl.size();
    }

    @Override
    public String getElementAt(int index) {
        return pl.get(index);
    }
}
