package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JDBCDemo {
	
	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	//jdbc:mysql://hostname:part/db name  //jdbcis a type of our connection or our API to connect to our database
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";
	
	@Test
	public void newConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		System.out.println("DB Connection Successful");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from hs_hr_employees");
		rs.next();
		String firstname = rs.getString("emp_firstname");
		System.out.println(firstname);
		rs.next();
		String firstname2 = rs.getObject("emp_firstname").toString();
		System.out.println(firstname2);
		while(rs.next()) {
			String allData = rs.getObject("emp_firstname").toString();
			System.out.println(allData);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
		@Test
		public void listTask() throws SQLException {
			
			Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			System.out.println("DB Connection Successful");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from ohrm_nationality");
			List<String> nationalities = new ArrayList<>();
			while (rs.next()) {
				String nationality = rs.getObject("name").toString();
				nationalities.add(nationality);
			}
			
			System.out.println(nationalities);
			for (String nat: nationalities) {
				System.out.println(nat);
			}
		
		
	}

}
