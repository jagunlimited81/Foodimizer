package edu.ilstu.Foodimizer.app.db;

import java.sql.*;

public class CreateTable {
    static final String DB_URL = "";
    static final String USER = "System";
    static final String PASSWORD = "1234567890";

    public CreateTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            // load the driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
            // TODO continue
            String sql = "CREATE TABLE Recipes " +
                    "";
            statement.executeUpdate(sql);
        } catch (SQLException se) {

        } catch (Exception e) {

        } finally {
            try {
                if (statement != null) {
                    connection.close();
                }
            } catch (Exception e) {

            }
        }
    }
}
