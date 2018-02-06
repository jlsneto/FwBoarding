package testsdao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioDAOTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUsuarioDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetornarUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserir() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetornaDescricaoUsuario() {
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
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		for(UsuarioVO i: usuarioDAO.listar()) {
			System.out.println("Código: " + i.getCodigoUsuario() + "\nLogin: " + i.getNomeUsuario() + "\nSenha: " + i.getSenha());
		}
	}

	@Test
	public void testVerificaUltimoCodigo() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetornaSenhaUsuario() {
		fail("Not yet implemented");
	}

}
