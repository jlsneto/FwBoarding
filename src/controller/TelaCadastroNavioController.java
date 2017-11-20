package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.NavioDAO;
import model.dao.PaisDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.vo.NavioObservableList;
import model.vo.Pais;

public class TelaCadastroNavioController implements Initializable {

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextField textFieldDescricao;
    
    @FXML
    private Label labelCodigo;
    
    @FXML
    private ComboBox<String> comboBoxPaisOrigem;
    
    private ObservableList<String> observableListPais;
    
    private final Database database = DatabaseFactory.getDatabase("oracle");
    private final Connection conn = database.conectar();
    private final PaisDAO paisDAO = new PaisDAO();
    private final NavioDAO navioDAO = new NavioDAO();
    
    private List<String> listaPais;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		paisDAO.setConnection(conn);
		navioDAO.setConnection(conn);
		
		listaPais = listarDescricao();
		
		labelCodigo.setText(Integer.toString(navioDAO.verificaUltimoCodigo()+1));
		observableListPais = FXCollections.observableArrayList(listaPais);
		comboBoxPaisOrigem.setValue("Selecionar Pais");
		comboBoxPaisOrigem.setItems(observableListPais);
		
	}
	
	public List<String> listarDescricao() {
		List<String> listPais = new ArrayList<>();
		for(Pais i:paisDAO.listarPais()) {
			listPais.add(i.getNome());
		}
		return listPais;
		
	}

}

