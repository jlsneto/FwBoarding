package safra;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import embarque.CadastroEmbarqueController;
import embarque.TelaEmbarqueController;
import helpers.DialogCadastroSafra;
import helpers.DialogUsuarioSenha;
import helpers.Routes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.dao.SafraDAO;
import model.dao.UsuarioDAO;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import model.vo.SafraVO;
import model.vo.UsuarioVO;
import usuario.CadastroUsuarioController;
import view.ConstruirDialog;

public class SafraViewController implements Initializable {

	@FXML
	private AnchorPane TelaSafra;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private TableView<SafraVO> TableView;

	@FXML
	private TableColumn<SafraVO, String> TableColumnCodigo;

	@FXML
	private TableColumn<SafraVO, String> TableColumnAnoSafra;

	@FXML
	private TableColumn<SafraVO, Button> columnButton;

	@FXML
	private JFXButton buttonAdd;

	public static ObservableList<SafraVO> observableListSafra;

	private final SafraDAO safraDAO = new SafraDAO();

	public static ObservableList<SafraVO> itensEncontrados;

	@FXML
	void clickOnIncluir() throws IOException {
		
		CadastroSafraController.isAlterarSafra = false;
		new DialogCadastroSafra();
		clickOnPesquisar();
	}

	@FXML
	public void clickOnAlterar() throws IOException {
		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
		SafraVO safraAlterar = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroSafraController.isAlterarSafra = true;
			DialogCadastroSafra.safraAlterar = safraAlterar;
			new DialogCadastroSafra();
		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhuma safra selecionado", "Selecione uma safra!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnExcluir() throws Exception {
		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();

		SafraVO safra = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDaSafra(safra.getAnoSafra().toString())) {
				safraDAO.deletar(safra.getCodigoSafra());
				safraDAO.verificaSeUsuarioFoiExcluido(safra.getCodigoSafra());
				// TableColumnNavio.getItems().remove(selectedIndex);
				observableListSafra.remove(safra);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum usuário selecionado", "Selecione um usuário!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (SafraVO itens : observableListSafra) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			btnBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			btnBar.getStylesheets().add(getClass().getResource("/view/styles/styles.css").toExternalForm());
			
			Image excluirIcon = new Image(getClass().getResourceAsStream("/view/images/Icons/deletar.png"));
			//button excluir
			JFXButton buttonExcluir = new JFXButton();
			buttonExcluir.getStyleClass().add("buttonTable");
			buttonExcluir.setGraphic(new ImageView(excluirIcon));
			buttonExcluir.setOnAction(event -> {
				try {
					TableView.getSelectionModel().select(itens);
					clickOnExcluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Image editarIcon = new Image(getClass().getResourceAsStream("/view/images/Icons/editarIcon.png"));
			//button editar
			JFXButton buttonEdit = new JFXButton();
			buttonEdit.setGraphic(new ImageView(editarIcon));
			buttonEdit.setOnAction(event -> {
				try {
					TableView.getSelectionModel().select(itens);
					clickOnAlterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			//check box
			
			JFXButton buttonDefinir = new JFXButton();
			buttonDefinir.setText("Definir");
			buttonDefinir.setOnAction(event ->{
				TableView.getSelectionModel().select(itens);	
				SafraVO safra = TableView.getSelectionModel().getSelectedItem();
				if(confirmaSafraPadrao(safra.getAnoSafra())) {
					CadastroEmbarqueController.codigoSafra(safra);
				}
					
				
			});
			
			
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit, buttonDefinir);
			if (itens.getAnoSafra().contains(String.valueOf(textFieldPesquisar.getText()))) {
				itensEncontrados.add(itens);
			}
		}
		TableView.setItems(itensEncontrados);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoSafra"));
		TableColumnAnoSafra.setCellValueFactory(new PropertyValueFactory<>("anoSafra"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

		observableListSafra = FXCollections.observableArrayList(safraDAO.listar());

		TableView.setItems(observableListSafra);
		clickOnPesquisar();
	}

	public void setNode(Node node) {
		TelaSafra.getChildren().clear();
		TelaSafra.getChildren().add((Node) node);
	}
	
	public boolean confirmaSafraPadrao(String anoSafra) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Safra Padrão",
				"A safra " + anoSafra + " será definida como Safra Padrão para os cadastros de Embarque", "Deseja confirmar? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean confirmouExcluisaoDaSafra(String anoSafra) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"A safra " + anoSafra + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	@FXML
	private void onKeyPressed(KeyEvent event) throws Exception {
		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
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
	void onMouseClicked(MouseEvent mouseEvent) throws IOException {
		if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
			if (mouseEvent.getClickCount() == 2) {
				clickOnAlterar();
			}
		}
	}

}
