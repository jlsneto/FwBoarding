package controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.dao.GrupoUsuarioDAO;
import model.dao.UsuarioDAO;
import model.vo.GrupoUsuarioVO;
import model.vo.UsuarioVO;
import view.ConstruirDialog;
import view.FwBoarding;

public class CadastroUsuarioController implements Initializable {

	@FXML
	private GridPane gridPane;

	@FXML
	private TextField textFieldUsuario;

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

	@FXML
	private void KeyPressedComboBox() {
		// implementar
	}

	@FXML
	private void onKeyPressed(Event event) {
	}

	public void setUsuarioAlterar(UsuarioVO[] args) {
		this.usuarioAlterar = args[0];
		labelCodigo.setText(Long.toString(usuarioAlterar.getCodigoUsuario()));
		textFieldUsuario.setText(usuarioAlterar.getNomeUsuario());
		comboBoxGrupoUsuario.getSelectionModel().select(usuarioAlterar.getGrupoUsuario());
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
	public void clickOnCadastrar() throws IOException, Exception {

		if (validarEntrada()) {
			if (isAlterarUsuario == false) {
				UsuarioVO usuario = new UsuarioVO();
				usuario.setCodigoUsuario(Integer.valueOf(labelCodigo.getText()));
				usuario.setNomeUsuario(textFieldUsuario.getText());
				usuario.setGrupoUsuario(comboBoxGrupoUsuario.getSelectionModel().getSelectedItem());
				// carrega a tela para cadastrar a senha posteriormente cifra
				String senha = FwBoarding.carregarDialogCadastroSenha(dialogStage);
				cifrarSenha(senha);
				usuario.setSenha(senha);
				usuarioDAO.inserir(usuario);

				// Atualiza Tela de Consulta
				if (usuario.getNomeUsuario().equals(usuarioDAO.retornaDescricaoUsuario(usuario.getNomeUsuario()))) {
					ConsultaUsuario.observableListUsuario.addAll(usuario);
				}
				// fechar dialog
				dialogStage.close();
			} else {
				usuarioAlterar.setNomeUsuario(textFieldUsuario.getText());
				usuarioAlterar.setGrupoUsuario(comboBoxGrupoUsuario.getSelectionModel().getSelectedItem());
				usuarioDAO.alterar(usuarioAlterar);
				ConsultaUsuario.itensEncontrados.set(ConsultaUsuario.itensEncontrados.indexOf(usuarioAlterar),
						usuarioAlterar);
				dialogStage.close();
			}

		}

	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (textFieldUsuario.getText() == null || textFieldUsuario.getText().length() == 0) {

			errorMessage = "Descrição inválida ou nula!\n";
			textFieldUsuario.requestFocus();
		} else if (comboBoxGrupoUsuario.getSelectionModel().getSelectedItem() == null) {

			errorMessage = "Selecione a qual grupo pertence!\n";
			comboBoxGrupoUsuario.requestFocus();

		} else if (usuarioDAO.retornaDescricaoUsuario(textFieldUsuario.getText()).equals(textFieldUsuario.getText())) {

			// Caso não tenha este if da erro!
			if (isAlterarUsuario == true) {
				if (!textFieldUsuario.getText().equals(usuarioAlterar.getNomeUsuario())) {
					errorMessage = "Usuário já existe!";
				}
			} else {
				errorMessage = "Usuário já existe!";
				textFieldUsuario.requestFocus();
			}

		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro de Usuário", errorMessage, 0, "", errorMessage);
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

	private String cifrarSenha(String textSenha) throws Exception {

		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();

		byte[] plainText = textSenha.getBytes(StandardCharsets.UTF_8);
		byte[] cipherText = cadastroAutenticacao.encrypt(plainText);
		// byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);

		return new String(cipherText);
	}

}
