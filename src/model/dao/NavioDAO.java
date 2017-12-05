package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.DatabaseFactory;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class NavioDAO {

	private Connection conn;

	public NavioDAO() {
		conn = DatabaseFactory.getDatabase().getConection();

	}

	public void inserir(NavioVO navio) throws SQLException {
		String sql = "INSERT INTO CADNAVIO(DESCRICAO, QTDPORAO, CAPACIDADEPORAO, CODIGOPAISORIGEM) VALUES(?,?,?,?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, navio.getDescricaoNavio());
			stmt.setLong(2, navio.getQtdPorao());
			stmt.setDouble(3, navio.getCapacidadePorao());
			stmt.setLong(4, navio.getPais().getCodigoPais());
			stmt.execute();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);
			throw new SQLException();
		}
	}

	public String retornaDescricaoNavio(String descricao) {
		String sql = "SELECT DESCRICAO FROM CADNAVIO " + "WHERE DESCRICAO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, descricao);
			ResultSet listaResultado = stmt.executeQuery();

			if (listaResultado.next()) {
				return listaResultado.getString("DESCRICAO");
			} else {
				return "";
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro de Consulta", "Erro ao consultar o navio no banco de dados", e.getErrorCode(),
					e.getMessage(), sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
			return "";
		}
	}

	public void deletar(long codigoNavio) {
		String sql = "DELETE FROM CADNAVIO" + " WHERE CODIGONAVIO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoNavio);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void verificarSeFoiNavioExcluido(long codigoNavio) throws Exception {

		String sql = "SELECT CODIGONAVIO FROM CADNAVIO WHERE CODIGONAVIO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoNavio);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				throw new Exception();
			}
			
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<NavioVO> listar() {

		String sql = "SELECT * FROM CADNAVIO " + "INNER JOIN CADPAIS "
				+ "ON CADNAVIO.CODIGOPAISORIGEM = CADPAIS.CODIGOPAIS ORDER BY CADNAVIO.CODIGONAVIO";

		List<NavioVO> listaNavio = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				NavioVO navio = new NavioVO();

				navio.setCodigoNavio(listaResultado.getLong("CODIGONAVIO"));
				navio.setDescricaoNavio(listaResultado.getString("DESCRICAO"));
				navio.setQtdPorao(listaResultado.getInt("QTDPORAO"));
				navio.setCapacidadePorao(listaResultado.getDouble("CAPACIDADEPORAO"));

				PaisVO pais = new PaisVO();

				pais.setCodigoPais(listaResultado.getInt("CODIGOPAIS"));
				pais.setNome(listaResultado.getString("NOME"));
				pais.setNomeFormal(listaResultado.getString("NOMEFORMAL"));
				pais.setDdi(listaResultado.getString("DDI"));
				pais.setIso(listaResultado.getString("ISO"));
				pais.setIso3(listaResultado.getString("ISO3"));

				navio.setPais(pais);

				listaNavio.add(navio);
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return listaNavio;
	}

	public int verificaUltimoCodigo() {
		String sql = "SELECT LAST_VALUE FROM CADNAVIO_CODIGONAVIO_SEQ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				return resultado.getInt("LAST_VALUE");
			} else {
				return 0;
			}

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;

	}
}
