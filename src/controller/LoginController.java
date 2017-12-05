package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.FwBoarding;

import java.net.URL;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	@FXML
	void clickOnConectar() throws Exception {

		if (textUsuarioLogin.getText().equals("admin") && textSenhaLogin.getText().equals("1234")) {
			labelStatus.setVisible(true);
			labelStatus.setText("Conectado!");
			dialogStage.close();
			FwBoarding.carregarRootLayout();
		} else {
			labelStatus.setVisible(true);
			labelStatus.setText("Erro ao Conectar! Login: admin senha: 1234");
			textUsuarioLogin.clear();
			textSenhaLogin.clear();
			textUsuarioLogin.requestFocus();
		}

		/*
		 * CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();
		 * 
		 * byte[] plainText = textSenhaLogin.getText().getBytes(StandardCharsets.UTF_8);
		 * byte[] cipherText = cadastroAutenticacao.encrypt(plainText); byte[]
		 * decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);
		 * 
		 * System.out.println(new String(plainText)); System.out.println(new
		 * String(cipherText)); System.out.println(new String(decryptedCipherText));
		 */

	}

}
