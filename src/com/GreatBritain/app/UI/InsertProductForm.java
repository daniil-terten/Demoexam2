package com.GreatBritain.app.UI;

import com.GreatBritain.app.utils.DBHandler;
import com.GreatBritain.app.utils.ProductTableManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertProductForm extends JFrame{
    private JTextField TitleTextField;
    private JTextField CostTextField;
    private JTextField ImageTextField;
    private JCheckBox IsActiveCheckBox;
    private JTextField ManufacturerTextField;
    private JButton SaveButton;
    private JButton BackButton;
    private JPanel panel;

    public InsertProductForm(JTable table){
        setContentPane(panel);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("school_logo.png")).getImage());

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = TitleTextField.getText();
                String imagePath = ImageTextField.getText();
                double cost = 0;
                int isActive = 0;
                boolean check = true;
                boolean checkMan = false;
                String manufacturer = ManufacturerTextField.getText();
                try{
                    cost = Double.parseDouble(CostTextField.getText());
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Неверный формат в числовых полях",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                    check = false;
                }
                if (title.length()>100 || title.length()<=0){
                    JOptionPane.showMessageDialog(null,
                            "Длина названия должны быть не меньше 0 и не больше 100",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                    check = false;
                }
                if (manufacturer.length()>100 || manufacturer.length()<=0){
                    JOptionPane.showMessageDialog(null,
                            "Длина производителя должна быть не меньше 0 и не больше 100",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                    check = false;
                }
                if (imagePath.length()>1000 || imagePath.length()<=0){
                    JOptionPane.showMessageDialog(null,
                            "Длина пути к картинке не должна быть больше 1000 и меньше 0",
                            "Внимание",
                            JOptionPane.WARNING_MESSAGE);
                }
                if (IsActiveCheckBox.isSelected()){
                    isActive = 1;
                }
                DBHandler.openConnection();
                ResultSet resultSet1 = DBHandler.execQuery("SELECT * FROM manufacturer");
                try{
                    while (resultSet1.next()){
                        if (manufacturer.equals(resultSet1.getString(2))){
                            checkMan = true;
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println(checkMan);

                if (check){
                    if (!checkMan){
                        DBHandler.execQuery("INSERT INTO manufacturer(Name,StartDate) VALUES ('"+manufacturer+"', CURRENT_DATE())");
                    }
                    ResultSet resultSet = DBHandler.execQuery("SELECT ID FROM manufacturer WHERE Name = '"+manufacturer+"'");
                    try{
                        while (resultSet.next()){
                            manufacturer = resultSet.getString(1);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    DBHandler.execQuery("INSERT INTO product(Title,Cost,MainImagePath,IsActive,ManufacturerID) VALUES ('" +
                            title +
                            "', " +cost+
                            ", '" +imagePath+
                            "', " +isActive+
                            ", "+Integer.parseInt(manufacturer)+")");

                }
                DBHandler.closeConnection();
                ProductTableManager.refreshTable(table);
                dispose();
            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
