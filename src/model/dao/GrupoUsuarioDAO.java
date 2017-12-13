package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
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
		String sql = "INSERT INTO GRUPOUSUARIO (DESCRICAO, PERMISSAO_INSERT_NAVIO, PERMISSAO_ALTER_NAVIO,PERMISSAO_CONSUL_NAVIO, PERMISSAO_DELET_NAVIO, PERMISSAO_INSERT_USER,PERMISSAO_ALTER_USER,PERMISSAO_CONSUL_USER,PERMISSAO_DELET_USER,PERMISSAO_INSERT_MOVIMENTO, PERMISSAO_ALTER_MOVIMENTO,PERMISSAO_CONSUL_MOVIMENTO,PERMISSAO_DELET_MOVIMENTO,PERMISSAO_INSERT_EMBARQUE,PERMISSAO_ALTER_EMBARQUE,PERMISSAO_CONSUL_EMBARQUE, PERMISSAO_DELET_EMBARQUE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, grupoUsuario.getDescricaoGrupo());
			stmt.setLong(2, grupoUsuario.getPermissaoInsertNavio());
			stmt.setLong(3, grupoUsuario.getPermissaoAlterNavio());
			stmt.setLong(4, grupoUsuario.getPermissaoConsulNavio());
			stmt.setLong(5, grupoUsuario.getPermissaoDeletNavio());
			stmt.setLong(6, grupoUsuario.getPermissaoInsertUser());
			stmt.setLong(7, grupoUsuario.getPermissaoAlterUser());
			stmt.setLong(8, grupoUsuario.getPermissaoConsulUser());
			stmt.setLong(9, grupoUsuario.getPermissaoDeletUser());
			stmt.setLong(10, grupoUsuario.getPermissaoInsertMovimento());
			stmt.setLong(11, grupoUsuario.getPermissaoAlterMovimento());
			stmt.setLong(12, grupoUsuario.getPermissaoConsulMovimento());
			stmt.setLong(13, grupoUsuario.getPermissaoDeletMovimento());
			stmt.setLong(14, grupoUsuario.getPermissaoInsertEmbarque());
			stmt.setLong(15, grupoUsuario.getPermissaoAlterEmbarque());
			stmt.setLong(16, grupoUsuario.getPermissaoConsulEmbarque());
			stmt.setLong(17, grupoUsuario.getPermissaoDeletEmbarque());

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);
		}

	}
}
