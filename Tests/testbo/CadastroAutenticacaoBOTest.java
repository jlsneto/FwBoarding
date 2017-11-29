package testbo;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import bo.CadastroAutenticacaoBO;

public class CadastroAutenticacaoBOTest {
	
	private byte[] plainText = "teste".getBytes(StandardCharsets.UTF_8);
	private byte[] cipherText;
	
	@Test
	public void testDeveCriptografarSenha() throws Exception {
		
		CadastroAutenticacaoBO criptografia = new CadastroAutenticacaoBO();

		this.cipherText = criptografia.encrypt(plainText);
		
		assertEquals("çX`ä×~=føo?,Jx",new String(cipherText));
	}
	
	@Test
	public void testX() throws Exception {
		
		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();
		

		byte[] cipherText = cadastroAutenticacao.encrypt(plainText);
		byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);

		System.out.println(new String(plainText));
		System.out.println(new String(cipherText));
		System.out.println(new String(decryptedCipherText));
	}

}
