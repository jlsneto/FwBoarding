package testsdao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.dao.PaisDAO;
import model.database.DatabaseFactory;
import model.vo.PaisVO;

public class PaisDAOTest {
	PaisVO paisVO;
	Connection conn;
	
	@Before
	public void setUp() throws Exception {
		this.paisVO = new PaisVO();
		paisVO.setCodigoPais(14);
		paisVO.setDdi("13");
		paisVO.setIso("AL");
		paisVO.setIso3("BRA");
		paisVO.setNome("ARTHUR");
		paisVO.setNomeFormal("ARTHUR FALCAO");
		
		this.conn = DatabaseFactory.getDatabase().getConection();
		this.conn.setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		this.conn.close();
	}

	@Test
	public void testDeveriaListarNomePais() {
		PaisDAO paisDAO = new PaisDAO();
		
		for(PaisVO paisVO: paisDAO.listarPais()) {
			System.out.println("Nome: "+paisVO.getNome());
		}
	}

}
