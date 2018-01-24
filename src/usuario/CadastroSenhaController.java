package usuario;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import view.ConstruirDialog;

public class CadastroSenhaController implements Initializable{

	@FXML
	private JFXPasswordField fieldTextSenha;

	@FXML
	private JFXPasswordField fieldTextConfirmSenha;

	@FXML
	private JFXButton buttonConfirmar;

	private Stage dialogStage;
	
	public static String senha;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		dialogStage.setOnCloseRequest(event -> {
				dialogStage.close();
		});
	}

	@FXML
	public void clickOnConfirm() {
		if(validarEntrada()) {
			dialogStage.close();
		}else {
			fieldTextSenha.clear();
			fieldTextConfirmSenha.clear();
			fieldTextSenha.requestFocus();
		}
	}

	public String getSenha() {
		return senha;
	}

	private boolean validarEntrada() {
		String errorMessage = "";

		if (fieldTextSenha.getText() == null || fieldTextSenha.getText().length() == 0) {

			errorMessage = "Senha invalida ou nula!\n";
			fieldTextSenha.requestFocus();
		} else if (fieldTextConfirmSenha.getText() == null || fieldTextConfirmSenha.getText().length() == 0) {

			errorMessage = "Senha invalida ou nula!\n";
			fieldTextConfirmSenha.requestFocus();
		} else if(!fieldTextConfirmSenha.getText().equals(fieldTextSenha.getText())) {
			errorMessage = "As Senhas Não Combinam!\n";
		}

		if (errorMessage.length() == 0) {
			senha = fieldTextSenha.getText();
			return true;
		} else {
			ConstruirDialog dialogErro = new ConstruirDialog();
			dialogErro.DialogError("Erro cadastro de Usuário", errorMessage, 0, "", errorMessage);
			return false;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
