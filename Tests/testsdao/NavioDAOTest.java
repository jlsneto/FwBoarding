package testsdao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Scanner;

import org.junit.Test;

import model.dao.NavioDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.vo.Navio;
import model.vo.NavioObservableList;
import model.vo.Pais;

public class NavioDAOTest {

	private final Database database = DatabaseFactory.getDatabase("oracle");
	private final Connection conn = database.conectar();
	private final NavioDAO dao = new NavioDAO();
	
	
	/*
	@Test
	public void testeInserir() {
		
		String descricaoNavio;
		dao.setConnection(conn);
		
		Pais pais = new Pais();
		
		pais.setCodigoPais(76);
		boolean inserido = false;
		Scanner entrada = new Scanner(System.in);
		
		do {
			System.out.println("Descricao do Navio: ");
			descricaoNavio = entrada.nextLine();
			descricaoNavio = descricaoNavio.toUpperCase();
			Navio navio = new Navio(35,3,5000,descricaoNavio,pais);
			
			if(dao.validaDescricao(descricaoNavio)) {
				assertTrue(dao.inserir(navio));
				inserido = true;
			}else
			{
				System.out.println("Navio Existente! Entre com outro nome");
			}
		}while(inserido == false);
		
		entrada.close();
	}
	*/
	@Test
	public void testListar() {

		dao.setConnection(conn);

		for(Navio i: dao.listar()){
			System.out.println("Codigo do Navio: "+ 
								i.getCodigoNavio()+
								" Descrição: "+
								i.getDescricaoNavio()+
								" Pais de Origem: "+
								i.getPais().getNome());
		}
	}
	
	@Test
	public void testVerificaUltimoCodigo() {
		dao.setConnection(conn);
		System.out.println(dao.verificaUltimoCodigo());
	}

}
