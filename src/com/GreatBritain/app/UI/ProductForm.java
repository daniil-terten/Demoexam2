package com.GreatBritain.app.UI;

import com.GreatBritain.app.utils.ProductTableManager;

import javax.swing.*;

public class ProductForm  extends BaseForm{
    private JTable ProductTable;
    private JPanel panel;
    public ProductForm(){
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ProductTableManager.refreshTable(ProductTable);
    }

}
