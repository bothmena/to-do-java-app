package com.database;

import java.sql.*;

/**
 * Created by Aymen Ben Othmen on 15/07/16.
 */

public class ConnectDB {

    protected static Connection connection;
    protected Statement statement;

    public ConnectDB() throws SQLException, ClassNotFoundException {

        setConnection();
        statement = connection.createStatement();
    }

    private static void setConnection() throws SQLException, ClassNotFoundException {

        try{
            final String url = "jdbc:mysql://localhost:3306/toDoJavaAppDB";
            final String username = "matejerc_aymen";
            final String password = "love50434012MUFC";

            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        }catch (SQLException | ClassNotFoundException ex){
            System.out.printf("Error occured when conntecting to Database:%n %s", ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
