/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author laptop88
 */
public interface IBookDAO {
     void insertBook(List<Book> book);
     
     List<Book> findAll();
     
     List<Book> findAllMaxPrice();
     
     List<Book> findAllMinQuatity();
       
}
