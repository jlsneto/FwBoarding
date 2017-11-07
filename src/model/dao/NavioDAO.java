package model.dao;

import java.sql.Connection;

public class NavioDAO {
	
	private Connection conn;
	
	public Connection getConnection() {
		return conn;
		
	}
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
