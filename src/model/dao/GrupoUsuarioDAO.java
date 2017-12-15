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
import model.vo.GrupoUsuarioVO;
import view.ConstruirDialog;

public class GrupoUsuarioDAO {
	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public GrupoUsuarioDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
	}

	public void Inserir(GrupoUsuarioVO grupoUsuario) {
		String sql = "INSERT INTO GRUPOUSUARIO (codigogrupo,DESCRICAO, PERMISSAO_INSERT_NAVIO, PERMISSAO_ALTER_NAVIO,PERMISSAO_CONSUL_NAVIO, PERMISSAO_DELET_NAVIO, PERMISSAO_INSERT_USER,PERMISSAO_ALTER_USER,PERMISSAO_CONSUL_USER,PERMISSAO_DELET_USER,PERMISSAO_INSERT_MOVIMENTO, PERMISSAO_ALTER_MOVIMENTO,PERMISSAO_CONSUL_MOVIMENTO,PERMISSAO_DELET_MOVIMENTO,PERMISSAO_INSERT_EMBARQUE,PERMISSAO_ALTER_EMBARQUE,PERMISSAO_CONSUL_EMBARQUE, PERMISSAO_DELET_EMBARQUE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, grupoUsuario.getCodigoGrupo());
			stmt.setString(2, grupoUsuario.getDescricaoGrupo());
			stmt.setString(3, grupoUsuario.getPermissaoInsertNavio());
			stmt.setString(4, grupoUsuario.getPermissaoAlterNavio());
			stmt.setString(5, grupoUsuario.getPermissaoConsulNavio());
			stmt.setString(6, grupoUsuario.getPermissaoDeletNavio());
			stmt.setString(7, grupoUsuario.getPermissaoInsertUser());
			stmt.setString(8, grupoUsuario.getPermissaoAlterUser());
			stmt.setString(9, grupoUsuario.getPermissaoConsulUser());
			stmt.setString(10, grupoUsuario.getPermissaoDeletUser());
			stmt.setString(11, grupoUsuario.getPermissaoInsertMovimento());
			stmt.setString(12, grupoUsuario.getPermissaoAlterMovimento());
			stmt.setString(13, grupoUsuario.getPermissaoConsulMovimento());
			stmt.setString(14, grupoUsuario.getPermissaoDeletMovimento());
			stmt.setString(15, grupoUsuario.getPermissaoInsertEmbarque());
			stmt.setString(16, grupoUsuario.getPermissaoAlterEmbarque());
			stmt.setString(17, grupoUsuario.getPermissaoConsulEmbarque());
			stmt.setString(18, grupoUsuario.getPermissaoDeletEmbarque());
			stmt.executeUpdate();
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);

		}

	}
	
	public String retornaDescricaoGrupoUsuario(String descricao) {
		String sql = "SELECT DESCRICAO FROM GRUPOUSUARIO " + "WHERE DESCRICAO = ?";

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
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
			return "";
		}
	}
}
