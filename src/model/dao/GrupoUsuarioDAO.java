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
			// stmt.setLong(1, grupoUsuario.getCodigoGrupo());
			stmt.setString(1, grupoUsuario.getDescricaoGrupo());
			stmt.setString(2, grupoUsuario.getPermissaoInsertNavio());
			stmt.setString(3, grupoUsuario.getPermissaoAlterNavio());
			stmt.setString(4, grupoUsuario.getPermissaoConsulNavio());
			stmt.setString(5, grupoUsuario.getPermissaoDeletNavio());
			stmt.setString(6, grupoUsuario.getPermissaoInsertUser());
			stmt.setString(7, grupoUsuario.getPermissaoAlterUser());
			stmt.setString(8, grupoUsuario.getPermissaoConsulUser());
			stmt.setString(9, grupoUsuario.getPermissaoDeletUser());
			stmt.setString(10, grupoUsuario.getPermissaoInsertMovimento());
			stmt.setString(11, grupoUsuario.getPermissaoAlterMovimento());
			stmt.setString(12, grupoUsuario.getPermissaoConsulMovimento());
			stmt.setString(13, grupoUsuario.getPermissaoDeletMovimento());
			stmt.setString(14, grupoUsuario.getPermissaoInsertEmbarque());
			stmt.setString(15, grupoUsuario.getPermissaoAlterEmbarque());
			stmt.setString(16, grupoUsuario.getPermissaoConsulEmbarque());
			stmt.setString(17, grupoUsuario.getPermissaoDeletEmbarque());
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

	public void alterar(GrupoUsuarioVO grupoUsuarioAlterar) {
		String sql = "UPDATE GRUPOUSUARIO SET DESCRICAO = ?, PERMISSAO_INSERT_NAVIO = ?, PERMISSAO_ALTER_NAVIO = ?,PERMISSAO_CONSUL_NAVIO = ?, PERMISSAO_DELET_NAVIO = ?, PERMISSAO_INSERT_USER = ?,PERMISSAO_ALTER_USER = ?,PERMISSAO_CONSUL_USER = ?,PERMISSAO_DELET_USER = ?,PERMISSAO_INSERT_MOVIMENTO = ?, PERMISSAO_ALTER_MOVIMENTO = ?,PERMISSAO_CONSUL_MOVIMENTO = ?,PERMISSAO_DELET_MOVIMENTO = ?,PERMISSAO_INSERT_EMBARQUE = ?,PERMISSAO_ALTER_EMBARQUE = ?,PERMISSAO_CONSUL_EMBARQUE = ?, PERMISSAO_DELET_EMBARQUE = ? WHERE CODIGOGRUPO = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			// stmt.setLong(1, grupoUsuarioAlterar.getCodigoGrupo());
			stmt.setString(1, grupoUsuarioAlterar.getDescricaoGrupo());
			stmt.setString(2, grupoUsuarioAlterar.getPermissaoInsertNavio());
			stmt.setString(3, grupoUsuarioAlterar.getPermissaoAlterNavio());
			stmt.setString(4, grupoUsuarioAlterar.getPermissaoConsulNavio());
			stmt.setString(5, grupoUsuarioAlterar.getPermissaoDeletNavio());
			stmt.setString(6, grupoUsuarioAlterar.getPermissaoInsertUser());
			stmt.setString(7, grupoUsuarioAlterar.getPermissaoAlterUser());
			stmt.setString(8, grupoUsuarioAlterar.getPermissaoConsulUser());
			stmt.setString(9, grupoUsuarioAlterar.getPermissaoDeletUser());
			stmt.setString(10, grupoUsuarioAlterar.getPermissaoInsertMovimento());
			stmt.setString(11, grupoUsuarioAlterar.getPermissaoAlterMovimento());
			stmt.setString(12, grupoUsuarioAlterar.getPermissaoConsulMovimento());
			stmt.setString(13, grupoUsuarioAlterar.getPermissaoDeletMovimento());
			stmt.setString(14, grupoUsuarioAlterar.getPermissaoInsertEmbarque());
			stmt.setString(15, grupoUsuarioAlterar.getPermissaoAlterEmbarque());
			stmt.setString(16, grupoUsuarioAlterar.getPermissaoConsulEmbarque());
			stmt.setString(17, grupoUsuarioAlterar.getPermissaoDeletEmbarque());
			stmt.setLong(18, grupoUsuarioAlterar.getCodigoGrupo());
			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar o grupo de usuário",
					"Ocorreu um erro no banco de dados ao tentar alterar o grupo de usuário:"
							+ grupoUsuarioAlterar.getDescricaoGrupo(),
					e.getErrorCode(), e.getMessage(), sql);

		}
	}

	public void deletar(long codigoGrupo) {
		String sql = "DELETE FROM GRUPOUSUARIO" + " WHERE CODIGOGRUPO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoGrupo);
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public int verificaUltimoCodigo() {

		String sql;

		if (this.base.equals("oracle")) {
			sql = "SELECT (LAST_NUMBER-1) as LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_CODIGO_GRUPOUSUARIO'";
		} else {
			sql = "SELECT LAST_VALUE AS LAST_NUMBER FROM GRUPOUSUARIO_CODIGOGRUPO_SEQ";
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

	public void verificarSeGrupoFoiExcluido(long codigoGrupo) throws Exception {

		String sql = "SELECT CODIGOGRUPO FROM GRUPOUSUARIO WHERE CODIGOGRUPO = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoGrupo);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				throw new Exception();
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<GrupoUsuarioVO> listar() {

		String sql = "SELECT * FROM GRUPOUSUARIO ORDER BY GRUPOUSUARIO.CODIGOGRUPO";

		List<GrupoUsuarioVO> listaGrupo = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				GrupoUsuarioVO grupoUsuario = new GrupoUsuarioVO();

				grupoUsuario.setCodigoGrupo(listaResultado.getLong("CODIGOGRUPO"));
				grupoUsuario.setDescricaoGrupo(listaResultado.getString("DESCRICAO"));
				grupoUsuario.setPermissaoInsertNavio(listaResultado.getString("PERMISSAO_INSERT_NAVIO"));
				grupoUsuario.setPermissaoAlterNavio(listaResultado.getString("PERMISSAO_ALTER_NAVIO"));
				grupoUsuario.setPermissaoConsulNavio(listaResultado.getString("PERMISSAO_CONSUL_NAVIO"));
				grupoUsuario.setPermissaoDeletNavio(listaResultado.getString("PERMISSAO_DELET_NAVIO"));
				grupoUsuario.setPermissaoInsertUser(listaResultado.getString("PERMISSAO_INSERT_USER"));
				grupoUsuario.setPermissaoAlterUser(listaResultado.getString("PERMISSAO_ALTER_USER"));
				grupoUsuario.setPermissaoConsulUser(listaResultado.getString("PERMISSAO_CONSUL_USER"));
				grupoUsuario.setPermissaoDeletUser(listaResultado.getString("PERMISSAO_DELET_USER"));
				grupoUsuario.setPermissaoInsertMovimento(listaResultado.getString("PERMISSAO_INSERT_MOVIMENTO"));
				grupoUsuario.setPermissaoAlterMovimento(listaResultado.getString("PERMISSAO_ALTER_MOVIMENTO"));
				grupoUsuario.setPermissaoConsulMovimento(listaResultado.getString("PERMISSAO_CONSUL_MOVIMENTO"));
				grupoUsuario.setPermissaoDeletMovimento(listaResultado.getString("PERMISSAO_DELET_MOVIMENTO"));
				grupoUsuario.setPermissaoInsertEmbarque(listaResultado.getString("PERMISSAO_INSERT_EMBARQUE"));
				grupoUsuario.setPermissaoAlterEmbarque(listaResultado.getString("PERMISSAO_ALTER_EMBARQUE"));
				grupoUsuario.setPermissaoConsulEmbarque(listaResultado.getString("PERMISSAO_CONSUL_EMBARQUE"));
				grupoUsuario.setPermissaoDeletEmbarque(listaResultado.getString("PERMISSAO_DELET_EMBARQUE"));

				listaGrupo.add(grupoUsuario);
			}

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		return listaGrupo;
	}

}
