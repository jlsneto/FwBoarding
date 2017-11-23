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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.NavioDAO;
import model.database.Database;
import model.database.DatabaseFactory;
import model.vo.NavioVO;
import model.vo.NavioObservableListVO;
import model.vo.PaisVO;

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
	private TableView<NavioObservableListVO> TableColumnNavio;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<PaisVO, String> TableColumnNavioPais;

	public static ObservableList<NavioObservableListVO> observableListNavio;

	private final Database database = DatabaseFactory.getDatabase("oracle");
	private final Connection conn = database.conectar();
	private final NavioDAO navioDAO = new NavioDAO();

	private List<NavioObservableListVO> listaNavio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Setar conexão no objeto navioDao que foi aberta
		navioDAO.setConnection(conn);
		listaNavio = listarNavio();
		carregarTableViewNavio();
		database.desconectar(conn);

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
		carregarTelaCadastroNavio();
	}
	
	public static void clickOnAlterar() {
		//carregarTelaAlterarNavio();
	}
	
	public static void carregarTelaCadastroNavio() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClassePrincipal.class.getResource("/view/CadastroNavio.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
	
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Navios");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(ClassePrincipal.stage);
		dialogStage.setResizable(false);
		
		//Define Palco deste Dialog
		TelaCadastroNavioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		if(dialogStage.isShowing() == false) {
			//VERIFICAR VIABILIDADE DE HERDAR CONEXÃO DO BANCO
			controller.desconectarBanco();
		}
	}
}