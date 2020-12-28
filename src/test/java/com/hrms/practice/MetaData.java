package com.hrms.practice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaData {

	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	//jdbc:mysql://hostname:part/db name  //jdbcis a type of our connection or our API to connect to our database
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";
	
	@Test 
	public void dbMetaData () throws SQLException {
		Connection conn = DriverManager.getConnection (dbUrl, dbUsername, dbPassword);
		DatabaseMetaData dbMetaData =  conn.getMetaData();
		String driverName = dbMetaData.getDriverName();
		String version = dbMetaData.getDatabaseProductVersion();
		
		System.out.println(driverName);
		System.out.println(version);
	}
	
	@Test
	public void resultSetMetaData() throws SQLException {
		Connection conn = DriverManager.getConnection (dbUrl, dbUsername, dbPassword);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from hs_hr_employees where employee_id = 8392");
		ResultSetMetaData rsMetadata = rs.getMetaData();
		int columnCount = rsMetadata.getColumnCount();
		System.out.println(columnCount);
		String firstColName = rsMetadata.getColumnName(1);
		System.out.println(firstColName);
		
		for (int i = 1; i<= columnCount; i++) {
			String colNames = rsMetadata.getColumnName(i);
			System.out.println(colNames);
			
		}
		
		// HW get the resultset metadata store it in arraylist, retrieve that information from arraylist and print 
		// in console also get column type name and get table names
		
		/*
		 * 
		 */
	}
		
		
	}

