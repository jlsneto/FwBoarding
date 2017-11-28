package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


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
	void clickOnConectar() {
		dialogStage.close();
	}

}
