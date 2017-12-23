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
    private ComboBox<GrupoUsuarioDAO> comboBoxGrupoUsuario;

    @FXML
    private Label labelCodigo;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonCadastrar;

	private UsuarioVO usuarioAlterar;

	private Stage dialogStage;

	private Object observableListGrupoUsuario;
    
    public static boolean isAlterarUsuario;
    
	private final UsuarioDAO usuarioDAO = new UsuarioDAO();
	private final GrupoUsuarioDAO grupoUsuarioDAO = new GrupoUsuarioDAO();

    @FXML
    void KeyPressedComboBox(KeyEvent event) {

    }

    @FXML
    void clickOnCadastrar(ActionEvent event) {

    }

    @FXML
    void clickOnCancelar(ActionEvent event) {

    }

    @FXML
    void onKeyPressed(KeyEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelCodigo.setText(Integer.toString(usuarioDAO.verificaUltimoCodigo() + 1));
		observableListGrupoUsuario = FXCollections.observableArrayList(grupoUsuarioDAO.listar());

		comboBoxGrupoUsuario.setItems((ObservableList<GrupoUsuarioDAO>) observableListGrupoUsuario);
		
		
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
