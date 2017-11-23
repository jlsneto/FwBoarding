package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.vo.PaisVO;
import view.ConstruirDialog;

public class PaisDAO {
	private Connection conn;

	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public List<PaisVO> listarPais() {

		String sql = "SELECT * FROM CADPAIS";

		List<PaisVO> listaPais = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				PaisVO pais = new PaisVO();

				pais.setCodigoPais(listaResultado.getLong("CODIGOPAIS"));
				pais.setDdi(listaResultado.getString("DDI"));
				pais.setIso(listaResultado.getString("ISO"));
				pais.setIso3(listaResultado.getString("ISO3"));
				pais.setNome(listaResultado.getString("NOME"));
				pais.setNomeFormal(listaResultado.getString("NOMEFORMAL"));

				listaPais.add(pais);
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao Consultar Paises", e.getErrorCode(), e.getMessage(), sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return listaPais;
	}
}
