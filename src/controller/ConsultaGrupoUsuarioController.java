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
	private TableView<?> TableGrupoUsuario;

	@FXML
	private TableColumn<?, ?> TableColumnCodigoGrupo;

	@FXML
	private TableColumn<?, ?> TableColumnDescricaoGrupo;

	@FXML
	private TextField textFieldPesquisar;

	@FXML
	private Button ButtonPesquisar;

	public static ObservableList<GrupoUsuarioVO> observableListGrupo;
	private final GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();
	private ObservableList<GrupoUsuarioVO> itensEncontrados;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carregarTableViewGrupo();

	}

	public void carregarTableViewGrupo() {
		TableColumnCodigoGrupo.setCellValueFactory(new PropertyValueFactory<>("codigoGrupo"));
		TableColumnDescricaoGrupo.setCellValueFactory(new PropertyValueFactory<>("descricaoGrupo"));

		observableListGrupo = FXCollections.observableArrayList(grupoUsuarioDAO.listar());
		TableGrupoUsuario.setItems(observableListGrupo);
	}

	@FXML
	void clickOnAlterar(ActionEvent event) throws IOException {

		int selectedIndex = TableGrupoUsuario.getSelectionModel().getSelectedIndex();
		GrupoUsuarioVO grupoUsuario = (GrupoUsuarioVO) TableGrupoUsuario.getSelectionModel().getSelectedItem();

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
	void clickOnExcluir(ActionEvent event) throws Exception {

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
	void clickOnIncluir(ActionEvent event) throws IOException {

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
}
