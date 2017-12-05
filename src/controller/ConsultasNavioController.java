package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import model.vo.NavioVO;
import model.vo.NavioObservableListVO;
import model.vo.PaisVO;
import view.FwBoarding;

public class ConsultasNavioController implements Initializable {

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
	private TableView<NavioObservableListVO> TableColumnNavio;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<PaisVO, String> TableColumnNavioPais;

	public static ObservableList<NavioObservableListVO> observableListNavio;

	private final NavioDAO navioDAO = new NavioDAO();

	private List<NavioObservableListVO> listaNavio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

	private List<NavioObservableListVO> listarNavio() {
		List<NavioObservableListVO> listNavio = new ArrayList<>();

		for (NavioVO i : navioDAO.listar()) {

			NavioObservableListVO navio = new NavioObservableListVO(i.getCodigoNavio(), i.getDescricaoNavio(),
					i.getPais().getNome());

			listNavio.add(navio);

		}

		return listNavio;
	}

	@FXML
	public void clickOnIncluir() throws IOException {
		
		FwBoarding.carregarTelaCadastroNavio();

	}

	public static void clickOnAlterar() {
		// carregarTelaAlterarNavio();
	}

}