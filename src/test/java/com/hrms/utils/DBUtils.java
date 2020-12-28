package com.hrms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
	
	public static Connection conn;
	public static Statement st;
	public static ResultSet rs;
	public static List<Map<String, String>> listData;
	
	/**
	 * this method will get connected to database
	 */
	
	public static void getConnected() {
		try {
		Connection conn = DriverManager.getConnection(ConfigsReader.getPropValue("dbUrl"), 
				ConfigsReader.getPropValue("dbUsername"), ConfigsReader.getPropValue("dbPassword"));
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	/**
	 * this method will execute the query adn store the data inside the list of maps
	 * @param sqlQuery
	 * @return <-- List<Map<String, String>>	
	 */
	
	public static List<Map<String, String>> storeDataFromDB(String sqlQuery) {
		getConnected();
		try {
		st = conn.createStatement();
		rs = st.executeQuery(sqlQuery);
		ResultSetMetaData rsMetadata = rs.getMetaData();
		listData = new ArrayList<>();
		
		while (rs.next()) {
			Map<String, String> mapData = new LinkedHashMap<>();
			for (int i =1; i<=rsMetadata.getColumnCount(); i++) {
				mapData.put(rsMetadata.getColumnName(i), rs.getObject(i).toString());
			}
			listData.add(mapData);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return listData;
	}
	
	/**
	 * this method will close the whole DB connection 
	 */
	public static void closeConnection() {
		try {
			if (rs!=null) {
				rs.close();
			}
			if (st !=null) {
				st.close();
			}
			if (conn !=null) {
				conn.close();
			}
		
	}catch (SQLException e) {
		e.printStackTrace();
		
	}
}
}
