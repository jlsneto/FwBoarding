package testsdomain;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import model.domain.Navio;
import model.domain.Pais;

public class NavioTest {

	@Test
	public void testConstructor() {

		Pais pais = new Pais();

		Navio test = new Navio(1, 3, 4, "Teste", pais);
	}

	@Test
	public void testSetCodigo() {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite o código do Navio: ");
		int codigo = entrada.nextInt();

		Navio test = new Navio();

		test.setCodigoNavio(codigo);

		assertEquals(test.getCodigoNavio(), codigo);

		entrada.close();

	}

}
