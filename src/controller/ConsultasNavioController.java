package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.dao.NavioDAO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;
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
	//private TableView<NavioVO> TableColumnNavio;
	private TableView<Object> TableColumnNavio;
	
	@FXML
	//private TableColumn<NavioVO, String> TableColumnNavioCodigo;
	private TableColumn<Object, String> TableColumnNavioCodigo;
	
	@FXML
	//private TableColumn<NavioVO, String> TableColumnNavioDescricao;
	private TableColumn<Object, String> TableColumnNavioDescricao;
	
	@FXML
	//private TableColumn<PaisVO, String> TableColumnNavioPais;
	private TableColumn<Object, String> TableColumnNavioPais;

	//public static ObservableList<NavioVO> observableListNavio;
	public static ObservableList<Object> observableListNavio;
	
	private final NavioDAO navioDAO = new NavioDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarTableViewNavio();

	}

	public void carregarTableViewNavio() {

		TableColumnNavioCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoNavio"));
		TableColumnNavioDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoNavio"));
		TableColumnNavioPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

		observableListNavio = FXCollections.observableArrayList(navioDAO.listar());

		TableColumnNavio.setItems(observableListNavio);

	}


	@FXML
	public void clickOnIncluir() throws IOException {
		
		CadastroNavioController.isAlterarNavio = false;
		FwBoarding.carregarTelaCadastroNavio();

	}

	public void clickOnAlterar() throws IOException {
		
		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = (NavioVO) TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
				CadastroNavioController.isAlterarNavio = true;
				FwBoarding.carregarTelaCadastroNavio(navio);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		
	}

	public void clickOnExcluir() throws Exception {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = (NavioVO) TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoNavio(navio.getDescricaoNavio().toString())) {
				navioDAO.deletar(navio.getCodigoNavio());
				navioDAO.verificarSeFoiNavioExcluido(navio.getCodigoNavio());
				TableColumnNavio.getItems().remove(selectedIndex);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
	}

	public boolean confirmouExcluisaoDoNavio(String descricaoNavio) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"O navio " + descricaoNavio + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	public void defineConsulta() {
		
	}

}