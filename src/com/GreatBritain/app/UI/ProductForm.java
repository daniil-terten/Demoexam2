package com.GreatBritain.app.UI;

import com.GreatBritain.app.utils.DBHandler;
import com.GreatBritain.app.utils.ProductTableManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductForm  extends BaseForm{
    private JTable ProductTable;
    private JPanel panel;
    private JButton insertButton;
    private JButton deleteButton;

    public ProductForm(){
        setContentPane(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ProductTableManager.refreshTable(ProductTable);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertProductForm insertProductForm = new InsertProductForm(ProductTable);
                insertProductForm.setVisible(true);
                insertProductForm.pack();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();
                DBHandler.execQuery("DELETE FROM product WHERE ID = '"+ProductTable.getValueAt(ProductTable.getSelectedRow(), 0)+"'");
                DBHandler.closeConnection();
                ProductTableManager.refreshTable(ProductTable);
            }
        });
    }

}
