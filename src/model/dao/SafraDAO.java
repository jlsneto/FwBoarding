package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
		String sql = "INSERT INTO SAFRA(ANOSAFRA) VALUES(?)";

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
		String sql = "UPDATE CADUSUARIO SET ANOSAFRA = ? WHERE CODIGOSAFRA = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, safraAlterar.getAnoSafra());
			stmt.setLong(2, safraAlterar.getCodigoSafra());

			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar Navio",
					"Ocorreu um erro no banco de dados ao tentar alterar o Navio:" + safraAlterar.getAnoSafra(),
					e.getErrorCode(), e.getMessage(), sql);
			}
	}
	
	public void deletar(long codigoSafra) {
		String sql = "DELETE FROM SAFRA" + " WHERE CODIGOSAFRA = ?";

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

		String sql = "SELECT CODIGOSAFRA FROM SAFRA WHERE CODIGOSAFRA = ?";

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

		String sql = "SELECT * FROM SAFRA ORDER BY SAFRA.CODIGOSAFRA";

		List<SafraVO> listaUsuario = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				SafraVO safra = new SafraVO();

				safra.setCodigoSafra(listaResultado.getLong("CODIGOSAFRA"));
				safra.setAnoSafra(listaResultado.getString("ANOSAFRA"));
				listaUsuario.add(safra);
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
		return listaUsuario;
	}

}
