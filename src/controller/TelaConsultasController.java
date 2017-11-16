package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fwboarding.ClassePrincipal;
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
import model.vo.Navio;
import model.vo.NavioObservableList;
import model.vo.Pais;

public class TelaConsultasController implements Initializable {

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
	private TableView<NavioObservableList> TableColumnNavio;

	@FXML
	private TableColumn<Navio, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<Navio, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<Pais, String> TableColumnNavioPais;

	private ObservableList<NavioObservableList> observableListNavio;

	private final Database database = DatabaseFactory.getDatabase("oracle");
	private final Connection conn = database.conectar();
	private final NavioDAO navioDAO = new NavioDAO();

	private List<NavioObservableList> listaNavio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Setar conexão no objeto navioDao que foi aberta
		navioDAO.setConnection(conn);
		listaNavio = listarNavio();
		carregarTableViewNavio();

	}

	public void carregarTableViewNavio() {

		TableColumnNavioCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoNavio"));
		TableColumnNavioDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoNavio"));
		TableColumnNavioPais.setCellValueFactory(new PropertyValueFactory<>("paisDescricao"));

		observableListNavio = FXCollections.observableArrayList(listaNavio);

		TableColumnNavio.setItems(observableListNavio);

	}

	private List<NavioObservableList> listarNavio() {
		List<NavioObservableList> listNavio = new ArrayList<>();

		for (Navio i : navioDAO.listar()) {

			NavioObservableList navio = new NavioObservableList(i.getCodigoNavio(), i.getDescricaoNavio(),
					i.getPais().getNome());

			listNavio.add(navio);

		}

		return listNavio;
	}

	@FXML
	public void clickOnIncluir() throws IOException {
		ClassePrincipal.carregarTelaCadastroNavio();
	}
}