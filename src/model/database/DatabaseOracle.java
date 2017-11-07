package model.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseOracle implements Database {

	private Connection conn;

	@Override
	public Connection conectar() {

		// Param de conexão
		String server = "255.255.255.255";
		String port = "1521";
		String database = "XE";

		// Param de auth
		String user = "empat";
		String passwd = "teste123";

		try {

			String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;

			// Abrir conexão com DB
			this.conn = DriverManager.getConnection(url, user, passwd);
			return this.conn;
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseOracle.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}

	@Override
	public void desconectar(Connection conn) {
		try {
			//encerra conexão
			this.conn.close();
		}catch (SQLException ex) {
			Logger.getLogger(DatabaseOracle.class.getName()).log(Level.SEVERE, null, ex);
		}
		

	}

}
