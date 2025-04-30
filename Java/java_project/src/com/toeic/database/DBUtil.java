package com.toeic.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DBConstant.URL, DBConstant.ID, DBConstant.PASSWORD);
		return connection;
	}
}