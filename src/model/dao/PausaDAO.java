package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.DatabaseFactory;
import model.database.DatabaseParams;
import model.vo.EmbarqueVO;
import model.vo.PaisVO;
import model.vo.PausaVO;
import model.vo.SafraVO;
import view.ConstruirDialog;

public class PausaDAO {

	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public PausaDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
		

	}

	public PausaVO retornaPausa(long codigoPausa) throws Exception {

		String sql = "SELECT * FROM EMBARQUE WHERE CODIGOPAUSA = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoPausa);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {

				PausaVO pausa = new PausaVO();

				pausa.setCodigoPausa(result.getLong("CODIGOPAUSA"));
				pausa.setDescricaoPausa(result.getString("DESCRICAO"));

				return pausa;
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(PausaDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

}
