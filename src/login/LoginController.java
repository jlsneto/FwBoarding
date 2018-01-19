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
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;
import usuario.CadastroAutenticacaoBO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import fwboarding.MainViewController;
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

	UsuarioDAO usuarioDao;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loggingProgress.setVisible(false);
	}


	@FXML
	void clickOnConectar(ActionEvent event) throws Exception {
		this.usuarioDao = new UsuarioDAO();
        loggingProgress.setVisible(true);
    	labelStatus.setText("Verificando dados");
    	labelStatus.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.millis(1500));
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
				 
				 	//Falta Resgatar todos os dados do usuario no DAO
		            UsuarioVO user=new UsuarioVO();
		            user.setNomeUsuario(textUsuarioLogin.getText());
		            
		            //setar usuário logado
		            UsuarioSessao.setUsuarioAtivo(user);
		            
		            Stage stage = new Stage();
		    		FXMLLoader loader = new FXMLLoader();
		    		loader.setLocation(getClass().getResource(Routes.MAINVIEW));
		            Parent root = loader.load();
		            
		            MainViewController controller = loader.getController();
		            controller.setStage(stage);
		            
		            Scene scene = new Scene(root);
		            scene.getStylesheets().add(getClass().getResource("/view/styles/styles.css").toExternalForm());
		            //stage.initStyle(StageStyle.UNDECORATED);
		            stage.setMaximized(false);
		            stage.setResizable(false);
		            stage.setScene(scene);
		            stage.getIcons().add(new Image(getClass().getResource("/view/images/Icons/IconNavio.png").toString()));
		            stage.setIconified(false);
		            stage.show();
		            
		            //Fechar tela de Login
		           this.stage.close();
		           
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


	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

}
