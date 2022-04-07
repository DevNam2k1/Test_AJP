/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author laptop88
 */
public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/java_project";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	// private static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("connector:");
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		Connection conn = Connect.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from bs");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("name") + "-" + rs.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
