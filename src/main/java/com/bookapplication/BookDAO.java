/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laptop88
 */
public class BookDAO implements IBookDAO{
    private static final String INSERT_BOOKS_SQL = "INSERT INTO books" + "  (id, title, price, quantity) VALUES " +
        " ( ?, ?, ?, ?);";
    private static final String FIND_TITEL = "SELECT * FROM books ORDER BY title ASC";
    
    private static final String MAX_PRICE = "SELECT title, MAX(price) AS maxPrice FROM books";
    
    private static final String BOOK_LIKE = "SELECT title, MIN(quantity) AS minQuantity FROM books";

    @Override
    public void insertBook(List<Book> book) {
       System.out.println(INSERT_BOOKS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = Connect.getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKS_SQL)) {
            for(Book list : book){
            preparedStatement.setInt(1, list.getId());
            preparedStatement.setString(2, list.getTitle());
            preparedStatement.setFloat(3, list.getPrice());
            preparedStatement.setInt(4, list.getQuantity());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
             }
        } catch (SQLException e) {
            printSQLException(e);
        }    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    @Override
    public List<Book> findAll() {
        List<Book> book = new ArrayList<>();
        
                try (Connection connection =Connect.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_TITEL)) {
            System.out.println(preparedStatement);
          
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                book.add(new Book(id, title, price, quantity));
            }
        } catch (SQLException e) {
           
        }
        return book;    }

    @Override
    public List<Book> findAllMaxPrice() {
 List<Book> book = new ArrayList<>();
        
                try (Connection connection =Connect.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(MAX_PRICE)) {
            System.out.println(preparedStatement);
          
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                book.add(new Book(id, title, price, quantity));
            }
        } catch (SQLException e) {
           
        }
        return book;     }

    @Override
    public List<Book> findAllMinQuatity() {
 List<Book> book = new ArrayList<>();
        
                try (Connection connection =Connect.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(BOOK_LIKE)) {
            System.out.println(preparedStatement);
          
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                book.add(new Book(id, title, price, quantity));
            }
        } catch (SQLException e) {
           
        }
        return book;     }
    
        public static void main(String[] args) {
		BookDAO dao = new BookDAO();
		
		ArrayList<Book> books = new ArrayList<>();
                books.add(new Book(1, "name", 120000, 10));
                books.add(new Book(2, "name2", 150000, 10));
                books.add(new Book(3, "name3", 10000, 10));
                books.add(new Book(4, "name4", 160000, 10));
                books.add(new Book(5, "name5", 20000, 1));
                dao.insertBook(books);
                
                
                List<Book> list = dao.findAll();
		
		for (Book book : list) {
			System.out.println(book);
		}
                
                List<Book> list2 = dao.findAllMaxPrice();
		
		for (Book book : list2) {
			System.out.println(book);
		}
                
                List<Book> list3 = dao.findAllMinQuatity();
		
		for (Book book : list3) {
			System.out.println(book);
		}

	}
    
}
