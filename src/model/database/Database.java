package model.database;

import java.sql.Connection;

public interface Database {
	
	//Criando contrato com as databases
	public Connection conectar();
	public void desconectar(Connection conn);
}
