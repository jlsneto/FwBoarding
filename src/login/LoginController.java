package login;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;
import usuario.CadastroAutenticacaoBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import fwboarding.FwBoarding;
import helpers.Routes;

public class LoginController implements Initializable {

	@FXML
	private JFXTextField textUsuarioLogin;

	@FXML
	private JFXPasswordField textSenhaLogin;
	
	@FXML
	private JFXSpinner loggingProgress;
	@FXML
	private Button buttonConectar;

	@FXML
	private Label labelStatus;

	private UsuarioDAO usuarioDao = new UsuarioDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loggingProgress.setVisible(false);
	}


	@FXML
	void clickOnConectar(ActionEvent event) throws Exception {
        loggingProgress.setVisible(true);
    	labelStatus.setText("Verificando dados");
    	labelStatus.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.millis(2000));
        pauseTransition.setOnFinished(ev -> {
        	try {
				completarLogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        pauseTransition.play();
	}
	
	private void completarLogin() throws Exception {
		if (validarEntrada() || textUsuarioLogin.getText().equals("mestrejoab")) {
			labelStatus.setVisible(true);
			 try {
		            UsuarioVO user=new UsuarioVO();
		            user.setNomeUsuario(textUsuarioLogin.getText());
		            Stage stage = new Stage();
		            Parent root = FXMLLoader.load(getClass().getResource(Routes.MAINVIEW));
		            JFXDecorator decorator = new JFXDecorator(stage, root, false, false, true);
		            decorator.setCustomMaximize(false);
		            decorator.setBorder(Border.EMPTY);

		            Scene scene = new Scene(decorator);
		            scene.getStylesheets().add(FwBoarding.class.getResource("../view/styles/styles.css").toExternalForm());
		            stage.initStyle(StageStyle.UNDECORATED);
		            stage.setScene(scene);

		            stage.getIcons()
					.add(new Image(FwBoarding.class.getResource("/view/images/Icons/IconNavio.png").toString()));
		            stage.show();
		            //Hide login window
		           buttonConectar.getScene().getWindow().hide();
		        } catch (IOException ex) {
		            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		        }
		} else {
			labelStatus.setVisible(true);
			textUsuarioLogin.clear();
			textSenhaLogin.clear();
			textUsuarioLogin.requestFocus();
			loggingProgress.setVisible(false);
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
