package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.dao.UsuarioDAO;
import model.vo.NavioVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;
import view.FwBoarding;

public class ConsultaUsuario implements Initializable {

	@FXML
	private AnchorPane TelaConsultasAnchorPane;

	@FXML
	private BorderPane AnchorPaneBorderPane;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private ButtonBar AnchorPaneButtonBar;

	@FXML
	private Button ButtonBarButtonExcluir;

	@FXML
	private Button ButtonBarButtonAlterar;

	@FXML
	private Button ButtonBarButtonIncluir;

	@FXML
	private TableView<UsuarioVO> TableView;

	@FXML
	private TableColumn<UsuarioVO, String> TableColumnCodigo;

	@FXML
	private TableColumn<UsuarioVO, String> TableColumnNome;

	@FXML
	private TableColumn<UsuarioVO, String> TableColumnGrupo;

	@FXML
	private TableColumn<UsuarioVO, Button> columnButton;

	public static ObservableList<UsuarioVO> observableListUsuario;

	private final UsuarioDAO usuarioDAO = new UsuarioDAO();

	public static ObservableList<UsuarioVO> itensEncontrados;

	private Button btn;

	@FXML
	public void clickOnIncluir() throws IOException {

		CadastroUsuarioController.isAlterarUsuario = false;
		FwBoarding.carregarTelaCadastroUsuario();
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
		UsuarioVO usuario = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroUsuarioController.isAlterarUsuario = true;
			FwBoarding.carregarTelaCadastroUsuario(usuario);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum usuário selecionado", "Selecione um usuário!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnExcluir() throws Exception {
		
		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();

		UsuarioVO usuario = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoNavio(usuario.getNomeUsuario().toString())) {
				usuarioDAO.deletar(usuario.getCodigoUsuario());
				usuarioDAO.verificaSeUsuarioFoiExcluido(usuario.getCodigoUsuario());
				// TableColumnNavio.getItems().remove(selectedIndex);
				observableListUsuario.remove(usuario);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum usuário selecionado", "Selecione um usuário!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	public boolean confirmouExcluisaoDoNavio(String descricaoUsuario) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"O usuario " + descricaoUsuario + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoUsuario"));
		TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		TableColumnGrupo.setCellValueFactory(new PropertyValueFactory<>("grupoUsuario"));
		columnButton.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

		observableListUsuario = FXCollections.observableArrayList(usuarioDAO.listar());

		TableView.setItems(observableListUsuario);
		clickOnPesquisar();
	}

	@FXML
	private void clickOnPesquisar() {
		
		itensEncontrados = FXCollections.observableArrayList();
		for (UsuarioVO itens : observableListUsuario) {
			itens.setButtonBar(new ButtonBar());
			ButtonBar btnBar = itens.getButtonBar();
			Button buttonExcluir = new Button("Excluir");
			buttonExcluir.setOnAction(event -> {
				try {
					TableView.getSelectionModel().select(itens);
					clickOnExcluir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Button buttonEdit = new Button("Editar");
			buttonEdit.setOnAction(event -> {
				try {
					TableView.getSelectionModel().select(itens);
					clickOnAlterar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			btnBar.getButtons().addAll(buttonExcluir, buttonEdit);
			if (itens.getNomeUsuario().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableView.setItems(itensEncontrados);
	}

}
