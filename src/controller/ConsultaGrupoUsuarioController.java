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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import view.ConstruirDialog;
import view.FwBoarding;
import model.dao.GrupoUsuarioDAO;

public class ConsultaGrupoUsuarioController implements Initializable {

	@FXML
	private Button ButtonIncluir;

	@FXML
	private Button ButtonAlterar;

	@FXML
	private Button ButtonExcluir;

	@FXML
	private TableView<GrupoUsuarioVO> TableGrupoUsuario;

	@FXML
	private TableColumn<GrupoUsuarioVO, String> TableColumnCodigoGrupo;

	@FXML
	private TableColumn<GrupoUsuarioVO, String> TableColumnDescricaoGrupo;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private Button ButtonPesquisar;

	public static ObservableList<GrupoUsuarioVO> observableListGrupo;
	private final GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();
	static ObservableList<GrupoUsuarioVO> itensEncontrados;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarTableViewGrupo();

	}

	public void carregarTableViewGrupo() {
		TableColumnCodigoGrupo.setCellValueFactory(new PropertyValueFactory<>("codigoGrupo"));
		TableColumnDescricaoGrupo.setCellValueFactory(new PropertyValueFactory<>("descricaoGrupo"));

		observableListGrupo = FXCollections.observableArrayList(grupoUsuarioDAO.listar());
		TableGrupoUsuario.setItems(observableListGrupo);
		clickOnPesquisar();

	}

	@FXML
	public void clickOnAlterar() throws IOException {

		int selectedIndex = TableGrupoUsuario.getSelectionModel().getSelectedIndex();
		GrupoUsuarioVO grupoUsuario = TableGrupoUsuario.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			CadastroGrupoController.isAlterarGrupo = true;
			FwBoarding.carregarTelaCadastroGrupoUsuario(grupoUsuario);

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum Grupo selecionado", "Selecione um Grupo!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	public void clickOnExcluir() throws Exception {

		int selectedIndex = TableGrupoUsuario.getSelectionModel().getSelectedIndex();
		GrupoUsuarioVO grupoUsuario = (GrupoUsuarioVO) TableGrupoUsuario.getSelectionModel().getSelectedItem();

		if (selectedIndex >= 0) {
			if (confirmouExcluisaoDoGrupo(grupoUsuario.getDescricaoGrupo().toString())) {
				grupoUsuarioDAO.deletar(grupoUsuario.getCodigoGrupo());
				grupoUsuarioDAO.verificarSeGrupoFoiExcluido(grupoUsuario.getCodigoGrupo());
				// TableColumnNavio.getItems().remove(selectedIndex);
				observableListGrupo.remove(selectedIndex);
			}

		} else {
			// Nada selecionado.
			ConstruirDialog alerta = new ConstruirDialog();
			alerta.dialogAlert("Não há seleção", "Nenhum navio selecionado", "Selecione um navio!");
		}
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	void clickOnIncluir() throws IOException {

		CadastroGrupoController.isAlterarGrupo = false;
		FwBoarding.carregarTelaCadastroGrupoUsuario();
		// Para Atualizar a ObservableList itensEncontrados
		clickOnPesquisar();
	}

	@FXML
	void clickOnPesquisar() {
		itensEncontrados = FXCollections.observableArrayList();
		for (GrupoUsuarioVO itens : observableListGrupo) {
			if (itens.getDescricaoGrupo().toLowerCase().contains(textFieldPesquisar.getText().toLowerCase())) {
				itensEncontrados.add(itens);
			}
		}
		TableGrupoUsuario.setItems(itensEncontrados);
	}
	
	public boolean confirmouExcluisaoDoGrupo(String descricaoGrupo) {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Exclusão",
				"O Grupo " + descricaoGrupo + " será EXCLUÍDO", "Confirma a Exclusão? Pressione OK para concluir!");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	
	@FXML
	void onKeyPressed(KeyEvent event) throws Exception {
		
		int selectedIndex = TableGrupoUsuario.getSelectionModel().getSelectedIndex();
		if (event.getCode().equals(KeyCode.ENTER) && selectedIndex >= 0) {
			clickOnAlterar();
		}
		else if(event.getCode().isLetterKey() || event.getCode().isWhitespaceKey() || event.getCode().equals(KeyCode.BACK_SPACE) ) {
			
			clickOnPesquisar();
		}
		if (event.getCode().equals(KeyCode.DELETE) && selectedIndex >= 0) {
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
