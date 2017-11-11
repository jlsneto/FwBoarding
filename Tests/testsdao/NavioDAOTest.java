package testsdao;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Test;

import model.dao.NavioDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.domain.Navio;
import model.domain.Pais;

public class NavioDAOTest {

	private final Database database = DatabaseFactory.getDatabase("oracle");
	private final Connection conn = database.conectar();
	private final NavioDAO dao = new NavioDAO();
	
	
	
	@Test
	public void testeInserir() {
		
		dao.setConnection(conn);
		
		Boolean inserido;
		
		Pais pais = new Pais();
		
		pais.setCodigoPais(76);
		
		Navio navio = new Navio(35,3,5000,"Vinte Carneirinhos",pais);
		
		assertTrue(inserido = dao.inserir(navio));
		
	}
	
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

}
