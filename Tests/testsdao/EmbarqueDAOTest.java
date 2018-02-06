package testsdao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.dao.EmbarqueDAO;
import model.vo.EmbarqueVO;

public class EmbarqueDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmbarqueDAO() {
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
	public void testListar() {
		EmbarqueDAO embarqueDAO = new EmbarqueDAO();
		
		for(EmbarqueVO i: embarqueDAO.listar()) {
			System.out.println("Código do Embarque: " + i.getCodigoEmbarque() + "\nCódigo do Usuário: " + i.getCodigoUsuario() + "\nCódigo do Navio: " + i.getCodigoNavio());
		}
	}

	@Test
	public void testRetornaCodigoEmbarque() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificarSeFoiEmbarqueExcluido() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerificaUltimoCodigo() {
		fail("Not yet implemented");
	}

}
