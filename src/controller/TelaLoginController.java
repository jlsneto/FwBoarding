package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

	public class PleaseProvideControllerClassName {

		@FXML
		private TextField textUsuarioLogin;

		@FXML
		private PasswordField textSenhaLogin;

		@FXML
		private Button buttonCancelar;

		@FXML
		private Button buttonConectar;

		@FXML
		void clickOnCancelar(ActionEvent event) {

		}

		@FXML
		void clickOnConectar(ActionEvent event) {

		}

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
