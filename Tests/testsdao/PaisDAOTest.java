package testsdao;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.dao.PaisDAO;
import model.vo.PaisVO;

public class PaisDAOTest {
	PaisVO paisVO;
	Connection conn;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test
	public void testDeveriaListarNomePais() {
		PaisDAO paisDAO = new PaisDAO();
		
		for(PaisVO paisVO: paisDAO.listarPais()) {
			System.out.println("Nome: "+paisVO.getNome());
		}
	}

}
