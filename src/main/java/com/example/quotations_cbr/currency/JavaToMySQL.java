package com.example.quotations_cbr.currency;


import java.sql.*;

public class JavaToMySQL {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3308/test";

    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement stmt;


    public static void createColumn(Date date, int id, String charCode, String name, double value) {
        String query = String.format("INSERT into `currencys` (id, charCode, name, value, dateOfValue) Values (%d, %s, %s, %.2f, %tF);", id, charCode, name, value, date);
                //"( id int NOT NULL, " +
                //"charCode char(10) NOT NULL," +
                //"name char(50)," +
                //"value double, " +
                //"dateOfValue date, " +
                //"PRIMARY KEY(id));"
        executeQuery(query);
    }
    public static void executeQuery(String query) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
