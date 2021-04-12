package com.GreatBritain.app.utils;

import java.sql.*;

public class DBHandler {

    public static Connection connection;

    public static boolean openConnection(){
        try {
            connection = DriverManager.getConnection(
                    Config.DB_URL,
                    Config.DB_USER,
                    Config.DB_PASS
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean closeConnection(){
        try{
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static ResultSet execQuery(String query){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        try{
            preparedStatement = connection.prepareStatement(query);
            if (query.contains("SELECT")){
                resultSet = preparedStatement.executeQuery();
            } else {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
