package controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.dao.GrupoUsuarioDAO;
import model.dao.UsuarioDAO;
import model.vo.GrupoUsuarioVO;
import model.vo.NavioVO;
import model.vo.PaisVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;

public class CadastroUsuarioController implements Initializable {


	@FXML
    private GridPane gridPane;

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private ComboBox<GrupoUsuarioVO> comboBoxGrupoUsuario;

    @FXML
    private Label labelCodigo;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonCadastrar;

	private UsuarioVO usuarioAlterar;

	private Stage dialogStage;

	private ObservableList<GrupoUsuarioVO> observableListGrupoUsuario;
    
    public static boolean isAlterarUsuario;
    
	private final UsuarioDAO usuarioDAO = new UsuarioDAO();
	private final GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelCodigo.setText(Integer.toString(usuarioDAO.verificaUltimoCodigo() + 1));
		
		observableListGrupoUsuario = FXCollections.observableArrayList(grupoUsuarioDAO.listar());
		
		comboBoxGrupoUsuario.setItems(observableListGrupoUsuario);
		
		
	}
	
	public void setUsuarioAlterar(UsuarioVO[] args) {
		this.usuarioAlterar = args[0];
		labelCodigo.setText(Long.toString(usuarioAlterar.getCodigoUsuario()));
		textFieldUsuario.setText(usuarioAlterar.getNomeUsuario());
		//comboBoxGrupoUsuario.getSelectionModel().select(usuarioAlterar.getGrupoUsuario());
		textFieldPassword.setDisable(true);
		buttonCadastrar.setText("Aplicar");
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		dialogStage.setOnCloseRequest(event -> {
			if (confirmouCancelamentoOuFehamento()) {
				// ... Usuário clicou ok
				dialogStage.close();
			} else {
				event.consume();
			}
		});

	}

	@FXML
	public void clickOnCancelar() {
		if (confirmouCancelamentoOuFehamento()) {
			dialogStage.close();

		}
	}

	@FXML
	public void clickOnCadastrar() {

		if (validarEntrada()) {
			if (isAlterarUsuario == false) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setCodigoUsuario(Integer.valueOf(labelCodigo.getText()));
				usuario.setNomeUsuario(textFieldUsuario.getText());
				usuario.setGrupoUsuario(comboBoxGrupoUsuario.getSelectionModel().getSelectedItem());
				usuario.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
				usuario.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));
				navioDAO.inserir(navio);
				// Atualiza Tela de Consulta
				if (navio.getDescricaoNavio().equals(navioDAO.retornaDescricaoNavio(navio.getDescricaoNavio()))) {
					ConsultasNavioController.observableListNavio.addAll(navio);
				}
				// fechar dialog
				dialogStage.close();
			} else {
				navioAlterar.setDescricaoNavio(textFieldDescricao.getText());
				navioAlterar.setPais(comboBoxPaisOrigem.getSelectionModel().getSelectedItem());
				navioAlterar.setQtdPorao(comboBoxQuantidadePorao.getSelectionModel().getSelectedItem());
				navioAlterar.setCapacidadePorao(Double.valueOf(textFieldCapacidadePorao.getText()));
				navioDAO.alterar(navioAlterar);
				ConsultasNavioController.itensEncontrados
						.set(ConsultasNavioController.itensEncontrados.indexOf(navioAlterar), navioAlterar);
				dialogStage.close();
			}

		}

	}
	
	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldDescricao.getText() == null || textFieldDescricao.getText().length() == 0) {
			
			errorMessage = "Descrição inválida ou nula!\n";
			textFieldDescricao.requestFocus();
		} else if (comboBoxPaisOrigem.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione o país!\n";
			comboBoxPaisOrigem.requestFocus();

		} else if (comboBoxQuantidadePorao.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione a quantidade de Porão!\n";
			comboBoxQuantidadePorao.requestFocus();

		} else if (textFieldCapacidadePorao.getText() == null || textFieldCapacidadePorao.getText().length() == 0
				|| Double.valueOf(textFieldCapacidadePorao.getText()) <= 0) {

			errorMessage = "Insira Capacidade !\n";
			textFieldCapacidadePorao.requestFocus();

		} else if (navioDAO.retornaDescricaoNavio(textFieldDescricao.getText()).equals(textFieldDescricao.getText())) {

			// Caso não tenha este if da erro!
			if (isAlterarNavio == true) {
				if (!textFieldDescricao.getText().equals(navioAlterar.getDescricaoNavio())) {
					errorMessage = "Navio já existe!";
				}
			} else {
				errorMessage = "Navio já existe!";
				textFieldDescricao.requestFocus();
			}

		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro do Navio", errorMessage, 0, "", errorMessage);
			return false;
		}

	}
	
	// Caso o usuário click em cancelar ou fechar
	public boolean confirmouCancelamentoOuFehamento() {
		ConstruirDialog confirmar = new ConstruirDialog();
		Optional<ButtonType> result = confirmar.DialogConfirm("Confirmar Cancelamento",
				"Atenção, se continuar seus dados serão perdidos!", "Deseja cancelar?");
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

}
