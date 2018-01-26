package tetsvo;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import model.vo.NavioVO;

public class NavioTest {

	@Test
	public void testSetCodigo() {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Digite o código do Navio: ");
		int codigo = entrada.nextInt();

		NavioVO test = new NavioVO();

		test.setCodigoNavio(codigo);

		assertEquals(test.getCodigoNavio(), codigo);

		entrada.close();

	}

}
