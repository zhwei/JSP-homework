package Dao;

import java.sql.*;

public class DBUtils {
	
	public static Connection getConnection() throws SQLException{
	
		String dbUrl = "jdbc:sqlite:d:/db.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		conn = DriverManager.getConnection(dbUrl);
		
		return conn;
	}
}
