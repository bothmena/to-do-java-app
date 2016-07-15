package sample;

import java.sql.*;

/**
 * Created by Aymen Ben Othmen on 15/07/16.
 */

public class ConnectDB {

    private static Connection connection;
    private Statement statement;
    private ResultSet result;

    public ConnectDB() throws SQLException, ClassNotFoundException {

        setConnection();
        statement = connection.createStatement();
    }

    public void getData(String query) {
        try {
            result = statement.executeQuery(query);
            System.out.println("printing data from DB.");
            while( result.next() ){
                System.out.printf("Username: %s%nFirst name: %s%nMiddle name: %s%n--------------%n",
                        result.getString("username"),
                        result.getString("firstname"),
                        result.getString("middlename")
                );
            }
        }catch (Exception ex){
            System.out.printf("Error in getData method%n: %s", ex);
        }
    }

    private static void setConnection() throws SQLException, ClassNotFoundException {

        try{
            String url = "jdbc:mysql://localhost:3306/toDoJavaAppDB";
            String username = "matejerc_aymen";
            String password = "love50434012MUFC";

            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        }catch (SQLException | ClassNotFoundException ex){
            System.out.printf("Error occured when conntecting to Database:%n %s", ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
