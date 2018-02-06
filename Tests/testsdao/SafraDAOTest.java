package testsdao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.dao.SafraDAO;
import model.vo.SafraVO;

public class SafraDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSafraDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserir() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletar() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaSeUsuarioFoiExcluido() {
		fail("Not yet implemented");
	}

	@Test
	public void testListar() {
		SafraDAO safraDAO = new SafraDAO();
		
		for(SafraVO i: safraDAO.listar()) {
			System.out.println("Código da Safra: " + i.getCodigoSafra() + "\nAno da Safra: " + i.getAnoSafra());
		}
	}

	@Test
	public void testRetornaAnoSafra() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaUltimoCodigo() {
		fail("Not yet implemented");
	}

}
