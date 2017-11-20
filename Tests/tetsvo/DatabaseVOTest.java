package tetsvo;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import model.vo.DatabaseSessionVO;

public class DatabaseVOTest {

	@Test
	public void testGetDatabaseName() {
		DatabaseSessionVO databaseVO =  new DatabaseSessionVO();
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Digite DatabaseName: ");
		databaseVO.setDatabaseName(entrada.nextLine());
		
		System.out.println("Digite Database: ");
	}

}
