package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.input.KeyEvent;
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
	private TableColumn<UsuarioVO, String> columnButton;
	
	private ObservableList<UsuarioVO> observableListUsuario;
	
	private final UsuarioDAO usuarioDAO = new UsuarioDAO();

	private ObservableList<UsuarioVO> itensEncontrados;

	@FXML
	public void clickOnIncluir() throws IOException {

		CadastroUsuarioController.isAlterarUsuario = false;
		FwBoarding.carregarTelaCadastroUsuario();
		//Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
		UsuarioVO usuario = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroNavioController.isAlterarNavio = true;
			FwBoarding.carregarTelaCadastroUsuario(usuario);

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

		int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
		UsuarioVO usuario = TableView.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoNavio(usuario.getNomeUsuario().toString())) {
				usuarioDAO.deletar(usuario.getCodigoUsuario());
				usuarioDAO.verificaSeUsuarioFoiExcluido(usuario.getCodigoUsuario());
				//TableColumnNavio.getItems().remove(selectedIndex);
				observableListUsuario.remove(selectedIndex);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum usuário selecionado", "Selecione um usuário!");
		}
		//Para Atualizar a ObservableList itensEncontrados
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
	void onKeyPressed(KeyEvent event) {

	}

	@FXML
	void onMouseClicked(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoNavio"));
		TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("descricaoNavio"));
		TableColumnGrupo.setCellValueFactory(new PropertyValueFactory<>("pais"));

		observableListUsuario= FXCollections.observableArrayList(usuarioDAO.listar());
		TableView.setItems(observableListUsuario);
		clickOnPesquisar();
	}

	@FXML
	private void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (UsuarioVO itens: observableListUsuario) {
			if (itens.getNomeUsuario().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableView.setItems(itensEncontrados);
	}
	

}
