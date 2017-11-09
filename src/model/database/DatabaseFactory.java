package model.database;

public class DatabaseFactory {
	public static Database getDatabase(String nome) {
		if(nome.equals("oracle")) {
			return new DatabaseOracle();
		}
		return null;
	}
}
