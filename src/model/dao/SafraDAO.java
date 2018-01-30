package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.database.DatabaseFactory;
import model.database.DatabaseParams;
import model.vo.GrupoUsuarioVO;
import model.vo.SafraVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;

public class SafraDAO {
	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public SafraDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
	}
	
	public void inserir(SafraVO safra) {
		String sql = "INSERT INTO CADSAFRA(PERIODOSAFRA) VALUES(?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, safra.getAnoSafra());
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);
		}
	}
	
	public void alterar(SafraVO safraAlterar) {
		String sql = "UPDATE CADSAFRA SET PERIODOSAFRA = ? WHERE CODIGOSAFRA = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, safraAlterar.getAnoSafra());
			stmt.setLong(2, safraAlterar.getCodigoSafra());

			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar Safra",
					"Ocorreu um erro no banco de dados ao tentar alterar o Safra:" + safraAlterar.getAnoSafra(),
					e.getErrorCode(), e.getMessage(), sql);
			}
	}
	
	public void deletar(long codigoSafra) {
		String sql = "DELETE FROM CADSAFRA" + " WHERE CODIGOSAFRA = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoSafra);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
	}
	
	public void verificaSeUsuarioFoiExcluido(long codigoSafra) throws Exception {

		String sql = "SELECT CODIGOSAFRA FROM CADSAFRA WHERE CODIGOSAFRA = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoSafra);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				throw new Exception();
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
	}
	
	public List<SafraVO> listar() {

		String sql = "SELECT * FROM CADSAFRA ORDER BY CODIGOSAFRA";

		List<SafraVO> listaUsuario = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				SafraVO safra = new SafraVO();

				safra.setCodigoSafra(listaResultado.getLong("CODIGOSAFRA"));
				safra.setAnoSafra(listaResultado.getString("PERIODOSAFRA"));
				listaUsuario.add(safra);
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
		return listaUsuario;
	}
	
	public String retornaAnoSafra(String anoSafra) {
		String sql = "SELECT PERIODOSAFRA FROM CADSAFRA WHERE PERIODOSAFRA = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, anoSafra);
			ResultSet listaResultado = stmt.executeQuery();

			if (listaResultado.next()) {
				return listaResultado.getString("PERIODOSAFRA");
			} else {
				return "";
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro de Consulta", "Erro ao consultar dados no banco de dados", e.getErrorCode(),
					e.getMessage(), sql);
			Logger.getLogger(SafraDAO.class.getName()).log(Level.SEVERE, null, e);
			return "";
		}
	}
	
	public int verificaUltimoCodigo() {

		String sql;

		if (this.base.equals("oracle")) {
			sql = "SELECT (LAST_NUMBER-1) as LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_CODIGO_SAFRA'";
		} else {
			sql = "SELECT LAST_VALUE AS LAST_NUMBER FROM CADSAFRA_CODIGOGRUPO_SEQ";
		}

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				return resultado.getInt("LAST_NUMBER");
			} else {
				return 0;
			}

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);

		}
		return 0;

	}

}
