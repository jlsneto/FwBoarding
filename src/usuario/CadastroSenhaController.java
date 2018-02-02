package usuario;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.ConstruirDialog;

public class CadastroSenhaController implements Initializable{

	@FXML
	private JFXPasswordField fieldTextSenha;

	@FXML
	private JFXPasswordField fieldTextConfirmSenha;

	@FXML
	private JFXButton buttonConfirmar;
	
	@FXML
	private Label statusVerifica;
	
	private Stage dialogStage;

	static boolean isAlterar = false;
	
	public static String senha;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		dialogStage.setOnCloseRequest(event -> {
				if(isAlterar) {
					dialogStage.close();
				}else {
					statusVerifica.setText("Atenção: obrigatório cadastrar a senha antes de sair.");
					statusVerifica.setVisible(true);
					event.consume();
				}
				
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
		statusVerifica.setText("Validando dados!");
		statusVerifica.setVisible(true);
		if (fieldTextSenha.getText() == null || fieldTextSenha.getText().length() == 0) {

			errorMessage = "Senha invalida ou nula!\n";
			fieldTextSenha.requestFocus();
		}
		else if (fieldTextConfirmSenha.getText() == null || fieldTextConfirmSenha.getText().length() == 0) {

			errorMessage = "Senha invalida ou nula!\n";
			fieldTextConfirmSenha.requestFocus();
		} else if(!fieldTextConfirmSenha.getText().equals(fieldTextSenha.getText())) {
			errorMessage = "As Senhas Não Combinam!\n";
		}

		if (errorMessage.length() == 0) {
			senha = fieldTextSenha.getText();
			statusVerifica.setText("Senha cadastrada com Sucesso!");
			return true;
		} else {
			statusVerifica.setText(errorMessage);
			return false;
		}

	}
	@FXML
	public void keyPressed(){
		if(statusVerifica.isVisible()) {
			statusVerifica.setVisible(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		statusVerifica.setVisible(false);
		
	}

}
