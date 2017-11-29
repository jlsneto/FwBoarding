package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import bo.CadastroAutenticacaoBO;


public class TelaLoginController implements Initializable {

	@FXML
	private TextField textUsuarioLogin;

	@FXML
	private PasswordField textSenhaLogin;

	@FXML
	private Button buttonConectar;



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
	
		
		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();
		
		byte[] plainText = textSenhaLogin.getText().getBytes(StandardCharsets.UTF_8);
		byte[] cipherText = cadastroAutenticacao.encrypt(plainText);
		byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);

		System.out.println(new String(plainText));
		System.out.println(new String(cipherText));
		System.out.println(new String(decryptedCipherText));
		
		dialogStage.close();
	}

}
