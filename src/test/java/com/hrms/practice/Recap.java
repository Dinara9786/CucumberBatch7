package com.hrms.practice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class Recap {
			
		String dbUsername = "syntax_hrm";
		String dbPassword = "syntaxhrm123";
		//jdbc:mysql://hostname:part/db name  //jdbcis a type of our connection or our API to connect to our database
		String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";
		
	@Test
	public void recap() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		Statement st = conn.createStatement (); 
		ResultSet rs = st.executeQuery("select * from ohrm_skill");
		DatabaseMetaData dbMetadata = conn.getMetaData();
		String dbName = dbMetadata.getDatabaseProductName();
		System.out.println(dbName);
		
		ResultSetMetaData rsMetadata = rs.getMetaData();
		int colCount = rsMetadata.getColumnCount();
		System.out.println(colCount);
		
		for (int i=1; i<=colCount; i++) {
			String colName = rsMetadata.getColumnName(i);
			System.out.println("Column Name: " + i + " " + colName);
		}
		
		while (rs.next()) {
			String id = rs.getObject("id").toString();
			String name = rs.getObject("name").toString();
			System.out.println(id + " " + name);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	}

}
