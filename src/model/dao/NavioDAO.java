package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import model.domain.Navio;
import model.domain.Pais;


public class NavioDAO {

	private Connection conn;

	public Connection getConnection() {
		return conn;

	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	public boolean inserir(Navio navio) {
		String sql = "INSERT INTO CADNAVIO(CODIGONAVIO, DESCRICAO, QTDPORAO, CAPACIDADEPORAO, CODIGOPAIS) VALUES(SEQ_CODIGO_CADNAVIO.NEXTVAL,?,?,?,?)";

		try {

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Verificar Questão do autoincremento do oracle
			
			stmt.setString(1, navio.getDescricaoNavio());
			stmt.setLong(2, navio.getQtdPorao());
			stmt.setDouble(3, navio.getCapacidadePorao());
			stmt.setLong(4, navio.getPais().getCodigoPais());
			stmt.execute();
			return true;

		} catch (SQLException e) {
			String msg = "Erro ao inserir Navio";
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, msg, e);
			return false;
		}
	}

	public List<Navio> listar() {

		String sql = "SELECT * FROM CADNAVIO "
				   + "INNER JOIN CADPAIS "
				   + "ON CADNAVIO.CODIGOPAISORIGEM = CADPAIS.CODIGOPAIS";

		List<Navio> lista = new ArrayList<>();

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet listaResultado = stmt.executeQuery();

			while (listaResultado.next()) {

				Navio navio = new Navio();
				Pais pais = new Pais();
				
				navio.setCodigoNavio(listaResultado.getLong("CODIGONAVIO"));
				navio.setDescricaoNavio(listaResultado.getString("DESCRICAO"));
				navio.setQtdPorao(listaResultado.getInt("QTDPORAO"));
				navio.setCapacidadePorao(listaResultado.getDouble("CAPACIDADEPORAO"));
				pais.setCodigoPais(listaResultado.getInt("CODIGOPAIS"));
				pais.setNome(listaResultado.getString("NOME"));
				navio.setPais(pais);
				
				lista.add(navio);
			}

		} catch (SQLException e) {
			String msg = "Erro ao Listar Navios";
			Logger.getLogger(NavioDAO.class.getName()).log(Level.SEVERE, msg, e);
		}
		return lista;
	}
}
