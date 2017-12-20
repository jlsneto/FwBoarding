package controller;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
	private TableView<NavioVO> TableColumnNavio;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<PaisVO, String> TableColumnNavioPais;

    @FXML
    private TextField textFieldPesquisar;

    @FXML
    private Button buttonPesquisar;

	public static ObservableList<NavioVO> observableListNavio;

	private final NavioDAO navioDAO = new NavioDAO();

	private ObservableList<NavioVO> itensEncontrados;

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
	public void onKeyPressed(KeyEvent event) throws IOException {
		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		if (event.getCode().equals(KeyCode.ENTER) && selectedIndex >= 0) {
			clickOnAlterar();
		}
		else if(event.getCode().isLetterKey() || event.getCode().isWhitespaceKey() || event.getCode().equals(KeyCode.BACK_SPACE) ) {
			//System.out.println(event.getCode().getName());
			clickOnPesquisar();
		}
	}

	@FXML
	public void onMouseClicked(MouseEvent mouseEvent) throws IOException {
		if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
			if (mouseEvent.getClickCount() == 2) {
				clickOnAlterar();
			}
		}
	}

	@FXML
	public void clickOnIncluir() throws IOException {

		CadastroNavioController.isAlterarNavio = false;
		FwBoarding.carregarTelaCadastroNavio();
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroNavioController.isAlterarNavio = true;
			FwBoarding.carregarTelaCadastroNavio(navio);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnExcluir() throws Exception {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoNavio(navio.getDescricaoNavio().toString())) {
				navioDAO.deletar(navio.getCodigoNavio());
				navioDAO.verificarSeFoiNavioExcluido(navio.getCodigoNavio());
				//TableColumnNavio.getItems().remove(selectedIndex);
				observableListNavio.remove(selectedIndex);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
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

	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (NavioVO itens: observableListNavio) {
			if (itens.getDescricaoNavio().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableColumnNavio.setItems(itensEncontrados);
	}
	
	

}