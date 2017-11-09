package controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.dao.NavioDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.domain.Navio;

public class TelaConsultasController implements Initializable{

    @FXML
    private AnchorPane TelaConsultasAnchorPane;

    @FXML
    private BorderPane AnchorPaneBorderPane;

    @FXML
    private AnchorPane BorderPaneTopAnchorPane;

    @FXML
    private ButtonBar AnchorPaneButtonBar;

    @FXML
    private Button ButtonBarButtonExcluir;

    @FXML
    private Button ButtonBarButtonAlterar;

    @FXML
    private Button ButtonBarButtonIncluir;

    @FXML
    private TableView<Navio> TableColumnNavio;

    @FXML
    private TableColumn<Navio, String> TableColumnNavioCodigo;

    @FXML
    private TableColumn<Navio, String> TableColumnNavioDescrição;
    
    private List<Navio> listNavio;
    private ObservableList<Navio> observableListNavio;
    
    private final Database database = DatabaseFactory.getDatabase("oracle");
    private final Connection conn = database.conectar();
    private final NavioDAO navioDAO = new NavioDAO();
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Setar conexão no objeto navioDao que foi aberta
		navioDAO.setConnection(conn);
		
		carregarTableViewCliente();
		
	}
	
	public void carregarTableViewCliente() {
		
		TableColumnNavioCodigo.setCellValueFactory(new PropertyValueFactory<>("CODIGONAVIO"));
		TableColumnNavioDescrição.setCellValueFactory(new PropertyValueFactory<>("DESCRICAO"));
		
		listNavio = navioDAO.listar();
		
		observableListNavio = FXCollections.observableArrayList(listNavio);
		TableColumnNavio.setItems(observableListNavio);
	}
}