package navio;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import fwboarding.FwBoarding;
import fwboarding.MainViewController;
import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.dao.NavioDAO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import view.ConstruirDialog;

public class ConsultasNavioController implements Initializable {

	@FXML
	private TableView<NavioVO> TableColumnNavio;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioCodigo;

	@FXML
	private TableColumn<NavioVO, String> TableColumnNavioDescricao;

	@FXML
	private TableColumn<PaisVO, String> TableColumnNavioPais;

	@FXML
	private TableColumn<Button, String> columnButton;

	@FXML
	private JFXTextField textFieldPesquisar;

	@FXML
	private JFXButton buttonAdd;

	@FXML
	private JFXTabPane tabPane;

	@FXML
	private AnchorPane anchorPaneNavio;

	public static ObservableList<NavioVO> observableListNavio;

	private final NavioDAO navioDAO = new NavioDAO();

	static ObservableList<NavioVO> itensEncontrados;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setNode(tabPane);
		carregarTableViewNavio();

	}

	public void carregarTableViewNavio() {

		TableColumnNavioCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoNavio"));
		TableColumnNavioDescricao.setCellValueFactory(new PropertyValueFactory<>("descricaoNavio"));
		TableColumnNavioPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

		observableListNavio = FXCollections.observableArrayList(navioDAO.listar());
		TableColumnNavio.setItems(observableListNavio);

		clickOnPesquisar();

	}

	@FXML
	private void onKeyPressed(KeyEvent event) throws Exception {
		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		if (event.getCode().equals(KeyCode.ENTER) && selectedIndex >= 0) {
			clickOnAlterar();
		} else if (event.getCode().isLetterKey() || event.getCode().isWhitespaceKey()
				|| event.getCode().equals(KeyCode.BACK_SPACE)) {
			// System.out.println(event.getCode().getName());
			clickOnPesquisar();
		} else if (event.getCode().equals(KeyCode.DELETE) && selectedIndex >= 0) {
			clickOnExcluir();
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
		AnchorPane cadastroNavio = FXMLLoader.load(getClass().getResource(Routes.CADASTRONAVIOVIEW));
		setNode(cadastroNavio);
		clickOnPesquisar();
		/*
		 * FXMLLoader loader = new FXMLLoader();
		 * loader.setLocation(getClass().getResource(Routes.CADASTRONAVIOVIEW));
		 * AnchorPane cadastroNavio = loader.load();
		 * 
		 * Stage stageCadastroNavio = new Stage();
		 * stageCadastroNavio.setTitle("Cadastro de Navio");
		 * stageCadastroNavio.initModality(Modality.WINDOW_MODAL);
		 * stageCadastroNavio.initOwner(MainViewController.stage);
		 * //stageCadastroNavio.initStyle(StageStyle.UTILITY);
		 * stageCadastroNavio.setResizable(false);
		 * 
		 * Scene scene = new Scene(cadastroNavio);
		 * 
		 * stageCadastroNavio.setScene(scene); stageCadastroNavio.showAndWait(); //Tab
		 * tabCadastro = new Tab("Cadastro de Navio",cadastroNavioPane);
		 * //tabCadastro.setClosable(true); //tabPane.getTabs().add(tabCadastro);
		 * //tabPane.getSelectionModel().selectNext();
		 * 
		 * 
		 * //FwBoarding.carregarTelaCadastroNavio(); //Para Atualizar a ObservableList
		 * itensEncontrados
		 * 
		 */
	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableColumnNavio.getSelectionModel().getSelectedIndex();
		NavioVO navio = TableColumnNavio.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(Routes.CADASTRONAVIOVIEW));
			CadastroNavioController.isAlterarNavio = true;

			AnchorPane cadastroNavio = loader.load();
			CadastroNavioController controller = loader.getController();
			controller.setNavioAlterar(navio);
			setNode(cadastroNavio);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		// Para Atualizar a ObservableList itensEncontrados
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
				// TableColumnNavio.getItems().remove(selectedIndex);
				observableListNavio.remove(navio);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		// Para Atualizar a ObservableList itensEncontrados
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
		// adicionar button para cada navio
		for (NavioVO itens : observableListNavio) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			btnBar.getStylesheets().add(getClass().getResource("../view/styles/styles.css").toExternalForm());

			Image excluirIcon = new Image(getClass().getResourceAsStream("../view/images/Icons/excluirIcon.png"));
			// button excluir
			JFXButton buttonExcluir = new JFXButton();
			buttonExcluir.setGraphic(new ImageView(excluirIcon));

			buttonExcluir.setOnAction(event -> {
				try {
					TableColumnNavio.getSelectionModel().select(itens);
					clickOnExcluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Image editarIcon = new Image(getClass().getResourceAsStream("../view/images/Icons/editarIcon.png"));
			// button editar
			JFXButton buttonEdit = new JFXButton();
			buttonEdit.setGraphic(new ImageView(editarIcon));
			buttonEdit.setOnAction(event -> {
				try {
					TableColumnNavio.getSelectionModel().select(itens);
					clickOnAlterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit);
			if (itens.getDescricaoNavio().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableColumnNavio.setItems(itensEncontrados);
	}

	public void setNode(Node node) {
		anchorPaneNavio.getChildren().clear();
		anchorPaneNavio.getChildren().add((Node) node);
	}

}