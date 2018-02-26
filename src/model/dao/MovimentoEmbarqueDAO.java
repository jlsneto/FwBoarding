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

import login.UsuarioSessao;
import model.database.DatabaseFactory;
import model.database.DatabaseParams;
import model.vo.EmbarqueVO;
import model.vo.MovimentoEmbarqueVO;
import model.vo.PaisVO;
import model.vo.PausaVO;
import model.vo.SafraVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;

public class MovimentoEmbarqueDAO {
	
	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public MovimentoEmbarqueDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
	}

	public void Inserir(MovimentoEmbarqueVO movimentoEmbarque) {

		String sql = "INSERT INTO MOVIMENTOEMBARQUE(CODIGOEMBARQUE, CODIGOUSUARIO, COMENTARIOPAUSA, TIPOMOVIMENTO)" + "VALUES (?,?,?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, movimentoEmbarque.getEmbarque().getCodigoEmbarque());
			stmt.setLong(2, movimentoEmbarque.getUsuario().getCodigoUsuario());
			stmt.setString(3, movimentoEmbarque.getComentarioPausa());
			stmt.setString(4, movimentoEmbarque.getTipoMovimento());
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);

		}
	}

	public void alterar(EmbarqueVO embarqueAlterar) {
		String sql = "UPDATE EMBARQUE SET CODIGONAVIO = ?, CODIGOPAISDESTINO = ?, QTDAEMBARCAR = ? WHERE CODIGOEMBARQUE = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, embarqueAlterar.getCodigoNavio());
			stmt.setLong(2, embarqueAlterar.getPaisDestino().getCodigoPais());
			stmt.setFloat(3, embarqueAlterar.getQuantidadeDeAcucar());
			stmt.setLong(4, embarqueAlterar.getCodigoEmbarque());
			stmt.executeUpdate();

		} catch (SQLException e) {

			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro ao Atualizar o grupo de usuário",
					"Ocorreu um erro no banco de dados ao tentar alterar o grupo de usuário:"
							+ embarqueAlterar.getCodigoEmbarque(),
					e.getErrorCode(), e.getMessage(), sql);
		}
	}

	public void deletar(long codigoEmbarque) {
		String sql = "DELETE FROM EMBARQUE" + " WHERE CODIGOEMBARQUE = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoEmbarque);
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(EmbarqueDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<MovimentoEmbarqueVO> listar() throws Exception {

		String sql = "SELECT "
				+ "MOVIMENTOEMBARQUE.CODIGOMOVIMENTOEMBARQUE,"
				+ "MOVIMENTOEMBARQUE.CODIGOEMBARQUE,"
				+ "CADUSUARIO.LOGIN,"
				+ "MOVIMENTOEMBARQUE.CODIGOPAUSA,"
				+ "MOVIMENTOEMBARQUE.CANCELADO,"
				+ "MOVIMENTOEMBARQUE.DATAMOVIMENTO,"
				+ "MOVIMENTOEMBARQUE.TIPOMOVIMENTO,"
				+ "MOVIMENTOEMBARQUE.COMENTARIOPAUSA"
				+ " FROM MOVIMENTOEMBARQUE "
				+"INNER JOIN EMBARQUE "
				+"ON MOVIMENTOEMBARQUE.CODIGOEMBARQUE = EMBARQUE.CODIGOEMBARQUE "
				+"INNER JOIN CADUSUARIO "
				+"ON MOVIMENTOEMBARQUE.CODIGOUSUARIO = CADUSUARIO.CODIGOUSUARIO "
				+"LEFT OUTER JOIN CADPAUSA "
				+"ON MOVIMENTOEMBARQUE.CODIGOPAUSA = CADPAUSA.CODIGOPAUSA "
				+"WHERE MOVIMENTOEMBARQUE.CANCELADO <> 'T'";
		List<MovimentoEmbarqueVO> listaMovimentoEmbarque = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {
				
				MovimentoEmbarqueVO movimentoEmbarque = new MovimentoEmbarqueVO();
				movimentoEmbarque.setCodigoMovimentoEmbarque(listaResultado.getLong("CODIGOMOVIMENTOEMBARQUE"));
				
				EmbarqueDAO embarqueDAO = new EmbarqueDAO();
				EmbarqueVO embarque = embarqueDAO.retornaEmbarque(listaResultado.getLong("CODIGOEMBARQUE"));
				movimentoEmbarque.setEmbarque(embarque);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				UsuarioVO usuario = usuarioDAO.retornarUsuario(listaResultado.getString("LOGIN"));				
				movimentoEmbarque.setUsuario(usuario);
				
				
				movimentoEmbarque.setCancelado(listaResultado.getString("CANCELADO"));
				movimentoEmbarque.setDataMovimento(listaResultado.getTimestamp("DATAMOVIMENTO").toString());
				movimentoEmbarque.setTipoMovimento(listaResultado.getString("TIPOMOVIMENTO"));
				movimentoEmbarque.setComentarioPausa(listaResultado.getString("COMENTARIOPAUSA"));
				
				listaMovimentoEmbarque.add(movimentoEmbarque);
							
				
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		return listaMovimentoEmbarque;

	}

	public String retornaUltimoMovimento(long codigoEmbarque) {
		String sql = "SELECT TIPOMOVIMENTO FROM MOVIMENTOEMBARQUE where rownum=1 AND CODIGOEMBARQUE = ? ORDER BY CODIGOMOVIMENTOEMBARQUE DESC";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoEmbarque);
			ResultSet listaResultado = stmt.executeQuery();

			if (listaResultado.next()) {
				return listaResultado.getString("TIPOMOVIMENTO");
			} else {
				return null;
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro de Consulta", "Erro ao consultar o grupo de usuário no banco de dados",
					e.getErrorCode(), e.getMessage(), sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	public void verificarSeFoiEmbarqueExcluido(long codigoEmbarque) throws Exception {

		String sql = "SELECT CODIGOEMBARQUE FROM EMBARQUE WHERE CODIGOEMBARQUE = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoEmbarque);
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

	public int verificaUltimoCodigo() {

		String sql;

		if (this.base.equals("oracle")) {
			sql = "SELECT (LAST_NUMBER-1) as LAST_NUMBER FROM USER_SEQUENCES WHERE SEQUENCE_NAME = 'SEQ_CODIGO_EMBARQUE'";
		} else {
			sql = "SELECT LAST_VALUE AS LAST_NUMBER FROM EMBARQUE_CODIGOEMBARQUE_SEQ";
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
