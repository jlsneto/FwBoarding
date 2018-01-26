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

import javafx.scene.control.TextField;
import model.database.DatabaseFactory;
import model.database.DatabaseParams;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;

public class UsuarioDAO {

	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public UsuarioDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
		

	}

	public UsuarioVO retornarUsuario(String descricaoUsuario) {
		String sql = "SELECT * FROM CADUSUARIO INNER JOIN GRUPOUSUARIO"
				+ " ON CADUSUARIO.CODIGOGRUPO = GRUPOUSUARIO.CODIGOGRUPO WHERE CADUSUARIO.LOGIN = ?";
		UsuarioVO usuario = new UsuarioVO();
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, descricaoUsuario);
			
			ResultSet resultado = stmt.executeQuery();
			
			while(resultado.next()) {
				
				usuario.setCodigoUsuario(resultado.getLong("CODIGOUSUARIO"));
				usuario.setNomeUsuario(resultado.getString("LOGIN"));
				usuario.setSenha(resultado.getString("SENHA"));
				usuario.setAlteraSenha(resultado.getString("ALTERASENHA"));
				
				GrupoUsuarioVO grupoUsuario = new GrupoUsuarioVO();

				grupoUsuario.setCodigoGrupo(resultado.getLong("CODIGOGRUPO"));
				grupoUsuario.setDescricaoGrupo(resultado.getString("DESCRICAO"));
				grupoUsuario.setPermissaoInsertNavio(resultado.getString("PERMISSAO_INSERT_NAVIO"));
				grupoUsuario.setPermissaoAlterNavio(resultado.getString("PERMISSAO_ALTER_NAVIO"));
				grupoUsuario.setPermissaoConsulNavio(resultado.getString("PERMISSAO_CONSUL_NAVIO"));
				grupoUsuario.setPermissaoDeletNavio(resultado.getString("PERMISSAO_DELET_NAVIO"));
				grupoUsuario.setPermissaoInsertUser(resultado.getString("PERMISSAO_INSERT_USER"));
				grupoUsuario.setPermissaoAlterUser(resultado.getString("PERMISSAO_ALTER_USER"));
				grupoUsuario.setPermissaoConsulUser(resultado.getString("PERMISSAO_CONSUL_USER"));
				grupoUsuario.setPermissaoDeletUser(resultado.getString("PERMISSAO_DELET_USER"));
				grupoUsuario.setPermissaoInsertMovimento(resultado.getString("PERMISSAO_INSERT_MOVIMENTO"));
				grupoUsuario.setPermissaoAlterMovimento(resultado.getString("PERMISSAO_ALTER_MOVIMENTO"));
				grupoUsuario.setPermissaoConsulMovimento(resultado.getString("PERMISSAO_CONSUL_MOVIMENTO"));
				grupoUsuario.setPermissaoDeletMovimento(resultado.getString("PERMISSAO_DELET_MOVIMENTO"));
				grupoUsuario.setPermissaoInsertEmbarque(resultado.getString("PERMISSAO_INSERT_EMBARQUE"));
				grupoUsuario.setPermissaoAlterEmbarque(resultado.getString("PERMISSAO_ALTER_EMBARQUE"));
				grupoUsuario.setPermissaoConsulEmbarque(resultado.getString("PERMISSAO_CONSUL_EMBARQUE"));
				grupoUsuario.setPermissaoDeletEmbarque(resultado.getString("PERMISSAO_DELET_EMBARQUE"));

				
				usuario.setGrupoUsuario(grupoUsuario);
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
		return usuario;
	}

	public void inserir(UsuarioVO usuario) {
		String sql = "INSERT INTO CADUSUARIO(LOGIN, SENHA, CODIGOGRUPO, ALTERASENHA) VALUES(?,?,?,?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, usuario.getNomeUsuario());
			stmt.setString(2, usuario.getSenha());
			stmt.setLong(3, usuario.getGrupoUsuario().getCodigoGrupo());
			stmt.setString(4, usuario.getAlteraSenha());
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);
		}
	}

	public String retornaDescricaoUsuario(String nomeUsuario) {
		String sql = "SELECT LOGIN FROM CADUSUARIO " + "WHERE LOGIN = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nomeUsuario);
			ResultSet listaResultado = stmt.executeQuery();

			if (listaResultado.next()) {
				return listaResultado.getString("LOGIN");
			} else {
				return "";
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro de Consulta", "Erro ao consultar dados no banco de dados", e.getErrorCode(),
					e.getMessage(), sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
			return "";
		}
	}

	public void deletar(long codigoUsuario) {
		String sql = "DELETE FROM CADUSUARIO" + " WHERE CODIGOUSUARIO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoUsuario);
			stmt.executeUpdate();
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
	}

	public void verificaSeUsuarioFoiExcluido(long codigoUsuario) throws Exception {

		String sql = "SELECT CODIGOUSUARIO FROM CADUSUARIO WHERE CODIGOUSUARIO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoUsuario);
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

	public List<UsuarioVO> listar() {

		String sql = "SELECT * FROM CADUSUARIO " + "INNER JOIN GRUPOUSUARIO "
				+ "ON CADUSUARIO.CODIGOGRUPO = GRUPOUSUARIO.CODIGOGRUPO ORDER BY CADUSUARIO.CODIGOUSUARIO";

		List<UsuarioVO> listaUsuario = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				UsuarioVO usuario = new UsuarioVO();

				usuario.setCodigoUsuario(listaResultado.getLong("CODIGOUSUARIO"));
				usuario.setNomeUsuario(listaResultado.getString("LOGIN"));

				GrupoUsuarioVO grupoUsuario = new GrupoUsuarioVO();

				grupoUsuario.setCodigoGrupo(listaResultado.getInt("CODIGOGRUPO"));
				grupoUsuario.setDescricaoGrupo(listaResultado.getString("DESCRICAO"));

				usuario.setGrupoUsuario(grupoUsuario);

				listaUsuario.add(usuario);
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
		}
		return listaUsuario;
	}

	public int verificaUltimoCodigo() {

		String sql;

		if (this.base.equals("oracle")) {
			sql = "SELECT (LAST_NUMBER-1) as LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_CODIGO_CADUSUARIO'";
		} else {
			sql = "SELECT LAST_VALUE AS LAST_NUMBER FROM CADUSUARIO_CODIGOUSUARIO_SEQ";
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

	public void alterar(UsuarioVO usuarioAlterar) {
		String sql = "UPDATE CADUSUARIO SET LOGIN = ?, CODIGOGRUPO = ?, SENHA = ?, ALTERASENHA = ? WHERE CODIGOUSUARIO = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, usuarioAlterar.getNomeUsuario());
			stmt.setLong(2, usuarioAlterar.getGrupoUsuario().getCodigoGrupo());
			stmt.setString(3, usuarioAlterar.getSenha());
			stmt.setString(4, usuarioAlterar.getAlteraSenha());
			stmt.setLong(5, usuarioAlterar.getCodigoUsuario());

			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar Navio",
					"Ocorreu um erro no banco de dados ao tentar alterar o Navio:" + usuarioAlterar.getNomeUsuario(),
					e.getErrorCode(), e.getMessage(), sql);

		}
	}

	public String retornaSenhaUsuario(String nomeUsuario) {
		String sql = "SELECT SENHA FROM CADUSUARIO WHERE LOGIN = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nomeUsuario);
			ResultSet senhaUsuario = stmt.executeQuery();

			if (senhaUsuario.next()) {
				return senhaUsuario.getString("SENHA");
			} else {
				return "";
			}

		} catch (SQLException e) {
			ConstruirDialog aviso = new ConstruirDialog();
			aviso.dialogAlert("Erro de Autenticação", "Usuário ou Senha Inválidos!", e.getMessage());
		}
		return "";
	}

}
