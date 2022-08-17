package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    static String URL = "jdbc:mysql://localhost:3306/addressbook_service";
    static String USER = "root";
    static String PASS = "Sagar@123";

    public static Connection connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded!");
        } catch (ClassNotFoundException e) {
            System.out.println("can't find the driver in the classpath!");
        }
        try{
            Connection connection= DriverManager.getConnection(URL,USER,PASS);
            System.out.println("connection is successful! " + connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
