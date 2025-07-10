package com.example.project;
import java.sql.*;

public class LibraryDataManager {
    public void addBook(Books b) {
        try (Connection con = JDBC_Connectivity.getConnection()) {
            String sql = "Insert into books(book_title,author,availability) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, b.get_title());
            ps.setString(2, b.get_author());
            ps.setBoolean(3, b.get_availability());
            ps.executeUpdate();
            ps.close();
        } 
        catch (SQLException e) {
            System.out.println("Cannot add books ");
            e.printStackTrace();
        }
    }

    public int deleteBook(int id) {
        try (Connection con = JDBC_Connectivity.getConnection()) {
            String sql = "Delete from books where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();
            return rows;
        } 
        catch (SQLException e) {
            System.out.println("Cannot delete book ");
            e.printStackTrace();
            return 0;
        }
    }

    public boolean updateAvailability(int id,boolean availability){
        try(Connection con=JDBC_Connectivity.getConnection()){
            String sql="Update books set availability=? where id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1, availability);
            ps.setInt(2,id);
            int rows=ps.executeUpdate();
            ps.close();
            return rows>0;
        }
        catch(SQLException e){
            System.out.println("Cannot update availability ");
            e.printStackTrace();
            return false;
        }
    }

    public void getAllBooks() {
        try (Connection con = JDBC_Connectivity.getConnection()) {
            String sql = "Select * from books";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\nID  Title  Author  Availability");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String title = rs.getString("book_title");
                String author = rs.getString("author");
                boolean availability = rs.getBoolean("availability");
                System.out.println(id + "  " + title + "  " + author + "  " + availability);
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException e) {
            System.out.println("Cannot fetch books ");
            e.printStackTrace();
        }
    }

    public Books getBookById(int id){
        Books b=null;
        try(Connection con=JDBC_Connectivity.getConnection()){
            String sql="Select * from books where id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                b=new Books(rs.getInt("id"),rs.getString("book_title"),rs.getString("author"),rs.getBoolean("availability"));
            }
            if(b!=null)
                System.out.println(b);
            else
                System.out.println("Book not found");
            rs.close();
            ps.close();
        }
        catch(SQLException e){
            System.out.println("Cannot fetch book ");
            e.printStackTrace();
        }
        return b;
    }
    
}
