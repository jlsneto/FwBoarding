package testbo;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import junit.framework.TestCase;
import usuario.CadastroAutenticacaoBO;

public class CadastroAutenticacaoBOTest extends TestCase {
	
	private byte[] plainText = "teste".getBytes(StandardCharsets.UTF_8);
	private byte[] cipherText;
	
	@Test
	public void testDeveCriptografarDescriptografarSenha() throws Exception {
		
		CadastroAutenticacaoBO criptografia = new CadastroAutenticacaoBO();

		this.cipherText = criptografia.encrypt(plainText);
		
		assertEquals("çX`ä×~=føo?,Jx",new String(cipherText));
	}

}
