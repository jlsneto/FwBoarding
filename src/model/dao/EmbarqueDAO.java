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
import model.vo.GrupoUsuarioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class EmbarqueDAO {

	private Properties props = DatabaseParams.getProp();
	private String base = props.getProperty("database");
	private Connection conn;

	public EmbarqueDAO() {
		conn = DatabaseFactory.getDatabase().getConection();
	}

	public void Inserir(EmbarqueVO embarque) {

		String sql = "INSERT INTO EMBARQUE(CODIGONAVIO, CODIGOPAISDESTINO, QTDAEMBARCAR, CODIGOUSUARIO, PERIODOSAFRA)" + "VALUES (?,?,?,?,?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, embarque.getCodigoNavio());
			stmt.setLong(2, embarque.getPaisDestino().getCodigoPais());
			stmt.setFloat(3, embarque.getQuantidadeDeAcucar());
			stmt.setLong(4, UsuarioSessao.getUsuarioAtivo().getCodigoUsuario());
			stmt.setString(5,embarque.getAnoSafra());
			stmt.executeUpdate();

		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Cadastro Erro", "Erro ao tentar inserir os dados", e.getErrorCode(), e.getMessage(), sql);

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

	public List<EmbarqueVO> listar() {

		String sql = "SELECT * FROM EMBARQUE  INNER JOIN CADPAIS ON EMBARQUE.CODIGOPAISDESTINO = CADPAIS.CODIGOPAIS  ORDER BY EMBARQUE.CODIGOEMBARQUE";
		List<EmbarqueVO> listaEmbarque = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				EmbarqueVO embarque = new EmbarqueVO();

				embarque.setCodigoEmbarque(listaResultado.getLong("CODIGOEMBARQUE"));
				embarque.setCodigoUsuario(listaResultado.getLong("CODIGOUSUARIO"));
				embarque.setCodigoNavio(listaResultado.getLong("CODIGONAVIO"));
				embarque.setStatus(listaResultado.getString("STATUS"));
				embarque.setQuantidadeDeAcucar(listaResultado.getFloat("QTDAEMBARCAR"));
				embarque.setAnoSafra(listaResultado.getString("PERIODOSAFRA"));

				PaisVO pais = new PaisVO();

				pais.setCodigoPais(listaResultado.getInt("CODIGOPAIS"));
				pais.setNome(listaResultado.getString("NOME"));
				pais.setNomeFormal(listaResultado.getString("NOMEFORMAL"));
				pais.setDdi(listaResultado.getString("DDI"));
				pais.setIso(listaResultado.getString("ISO"));
				pais.setIso3(listaResultado.getString("ISO3"));

				embarque.setPaisDestino(pais);
				
				listaEmbarque.add(embarque);
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("SQLException", "Erro ao consultar o banco de dados", e.getErrorCode(), e.getMessage(),
					sql);
			Logger.getLogger(GrupoUsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		return listaEmbarque;

	}

	public long retornaCodigoEmbarque(long codigoEmbarque) {
		String sql = "SELECT CODIGOEMBARQUE FROM EMBARQUE " + "WHERE CODIGOEMBARQUE = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, codigoEmbarque);
			ResultSet listaResultado = stmt.executeQuery();

			if (listaResultado.next()) {
				return listaResultado.getLong("CODIGOEMBARQUE");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			ConstruirDialog erro = new ConstruirDialog();
			erro.DialogError("Erro de Consulta", "Erro ao consultar o grupo de usuário no banco de dados",
					e.getErrorCode(), e.getMessage(), sql);
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, null, e);
			return 0;
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
