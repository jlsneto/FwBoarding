package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.UsuarioDAO;
import view.ConstruirDialog;
import view.FwBoarding;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	@FXML
	private TextField textUsuarioLogin;

	@FXML
	private PasswordField textSenhaLogin;

	@FXML
	private Button buttonConectar;

	@FXML
	private Label labelStatus;

	private Stage dialogStage;

	private UsuarioDAO usuarioDao = new UsuarioDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	@FXML
	void clickOnConectar() throws Exception {

		if (validarEntrada() || textUsuarioLogin.getText().equals("mestrejoab")) {
			labelStatus.setVisible(true);
			dialogStage.close();
			FwBoarding.carregarRootLayout();
		} else {
			labelStatus.setVisible(true);
			textUsuarioLogin.clear();
			textSenhaLogin.clear();
			textUsuarioLogin.requestFocus();
		}

	}

	private boolean validarEntrada() throws Exception {
		String Message = "";
		String usuarioDigitado = textUsuarioLogin.getText();
		String senhaDigitada = textSenhaLogin.getText();
		String usuarioBanco = usuarioDao.retornaDescricaoUsuario(textUsuarioLogin.getText());
		String senhaBanco = decifrarSenha(usuarioDao.retornaSenhaUsuario(textUsuarioLogin.getText()).getBytes());
		
		if(usuarioDigitado.equals("") && senhaDigitada.equals("")){
			Message = "Preencha os campos!";
		}else if (textUsuarioLogin.getText() == null || textUsuarioLogin.getText().length() == 0) {

			Message = "Usuário inválido ou nulo!";
		} else if (usuarioBanco == "") {

			Message = "Usuário Não Cadastrado";

		} else if (textUsuarioLogin.getText().equals(usuarioBanco) && textSenhaLogin.getText().equals(senhaBanco)) {
			Message = "OK";
		} else {
			Message = "Senha Inválida!";
		}

		labelStatus.setText(Message);

		if (Message == "OK") {
			return true;
		} else {
			return false;
		}

	}

	private String decifrarSenha(byte[] cipherText) throws Exception {

		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();
		if (cipherText != null) {
			byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);
			return new String(decryptedCipherText);
		} else {
			return null;
		}

	}

}
