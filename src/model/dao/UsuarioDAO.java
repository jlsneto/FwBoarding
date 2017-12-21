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

	public void inserir(NavioVO navio) {
		String sql = "INSERT INTO CADNAVIO(DESCRICAO, QTDPORAO, CAPACIDADEPORAO, CODIGOPAISORIGEM) VALUES(?,?,?,?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, navio.getDescricaoNavio());
			stmt.setLong(2, navio.getQtdPorao());
			stmt.setDouble(3, navio.getCapacidadePorao());
			stmt.setLong(4, navio.getPais().getCodigoPais());
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);
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
			erro.DialogError("Erro de Consulta", "Erro ao consultar o grupo de usuário no banco de dados", e.getErrorCode(),
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

			if (result.next()) {
				throw new Exception();
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
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
				usuario.setNomeUsuario(listaResultado.getString("NOMEUSUARIO"));

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
			sql = "SELECT (LAST_NUMBER-1) as LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_CODIGO_CADNAVIO'";
		} else {
			sql = "SELECT LAST_VALUE AS LAST_NUMBER FROM CADNAVIO_CODIGONAVIO_SEQ";
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
		String sql = "UPDATE CADUSUARIO SET NOMEUSUARIO = ?, CODIGOGRUPOUSUARIO = ? WHERE CODIGOUSUARIO = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usuarioAlterar.getNomeUsuario());
			stmt.setLong(2, usuarioAlterar.getGrupoUsuario().getCodigoGrupo());
			stmt.setLong(3, usuarioAlterar.getCodigoUsuario());
			
			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar Navio",
					"Ocorreu um erro no banco de dados ao tentar alterar o Navio:" + usuarioAlterar.getNomeUsuario(),
					e.getErrorCode(), e.getMessage(), sql);

		}
	}

}
