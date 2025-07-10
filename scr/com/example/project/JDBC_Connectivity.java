package com.example.project;
import java.sql.*;

public class JDBC_Connectivity {
    public static Connection getConnection() throws SQLException {
    try{
        Class.forName("org.postgresql.Driver"); 
    }
    catch(ClassNotFoundException e){
        System.out.println("PostgreSQL JDBC driver not found.");
    }
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/library",
        "postgres",
        "1234"
    );
    }
    public static void createTable(String table, String sql) {
        try (Connection con = getConnection()) {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println(table + " created");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String tbl = "Books";
        String query = "CREATE TABLE IF NOT EXISTS " + tbl + " (" + " ID serial primary key,"
                + " Book_Title varchar(100)," + " Author varchar(100)," + " Availability boolean default true" + ")";
        JDBC_Connectivity.createTable(tbl, query);
    }
}
