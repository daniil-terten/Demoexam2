package com.GreatBritain.app.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTableManager {
    public static void refreshTable(JTable table){
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(new String[]{"ID", "Название", "Цена", "Картинка", "Наличие товара",
                "Производитель"});
        DBHandler.openConnection();
        ResultSet resultSet = DBHandler.execQuery("SELECT p.ID,p.Title,p.Cost,p.MainImagePath,p.IsActive,ps.Name " +
                "FROM product as p INNER JOIN manufacturer as ps ON ps.ID = p.ManufacturerID");
        try{
            while (resultSet.next()){
                defaultTableModel.addRow(new String[]{
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5).equals("1") ? "В наличии" : "Нет в наличии",
                        resultSet.getString(6)
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBHandler.closeConnection();
        table.setModel(defaultTableModel);

    }
}
