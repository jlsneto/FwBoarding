package model.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.ConstruirDialog;

public class DatabaseOracle implements Database {

	private Connection conn;

	@Override
	public Connection conectar() {

		// Param de conexão
		String server = "localhost";
		String port = "1521";
		String database = "XE";

		// Param de auth
		String user = "empat";
		String passwd = "teste123";
		String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;
		try {
			// Abrir conexão com DB
			this.conn = DriverManager.getConnection(url, user, passwd);
			return this.conn;
		} catch (SQLException e) {
			
			ConstruirDialog erro = new ConstruirDialog();		
			erro.DialogError("SQLException", "Erro ao conectar no banco de Dados",e.getErrorCode(),e.getMessage(),"Verificar Conexão com o banco de dados!");
			Logger.getLogger(DatabaseOracle.class.getName()).log(Level.SEVERE, null, e);
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
