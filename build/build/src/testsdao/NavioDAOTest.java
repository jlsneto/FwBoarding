package testsdao;

import org.junit.Test;

import model.dao.NavioDAO;
import model.vo.NavioVO;

public class NavioDAOTest {


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


		for(NavioVO i: dao.listar()){
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

		System.out.println(dao.verificaUltimoCodigo());
	}

}
