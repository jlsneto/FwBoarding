package model.database;

import java.sql.SQLException;
import java.util.Properties;

import view.ConstruirDialog;

public class DatabaseFactory {
	private static Properties props = DatabaseParams.getProp();
	private static String base = props.getProperty("database");

	public static Database getDatabase() {

		if (base.equals("oracle")) {
			try {
				return DatabaseOracle.getDatabase();
			} catch (SQLException e) {
				ConstruirDialog erro = new ConstruirDialog();
				erro.DialogError("Erro de Conexão", "A conexão com o banco de dados falhou!", e.getErrorCode(),
						e.getMessage(),
						"Possíveis Causas:\n" + "Endereço do Servidor\n" + "Serviços do Banco de Dados");
			}
		} else if (base.equals("postgresql")) {
			try {
				return DatabasePostgresql.getDatabase();
			} catch (SQLException e) {
				ConstruirDialog erro = new ConstruirDialog();
				erro.DialogError("Erro de Conexão", "A conexão com o banco de dados falhou!", e.getErrorCode(),
						e.getMessage(),
						"Possíveis Causas:\n" + "Endereço do Servidor\n" + "Serviços do Banco de Dados");
			}
		}
		ConstruirDialog erro = new ConstruirDialog();
		erro.DialogError("Erro ao Fabricar Base da Dados", "Sistema não possui implementação para essa base de dados",
				0, "A base: " + base + " Não existe!",
				"Contate o admnistrador da sua rede\nVerifique o arquivo de configuração");
		return null;
	}
}
