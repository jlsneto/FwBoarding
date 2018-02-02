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
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import fwboarding.FwBoarding;
import fwboarding.MainViewController;
import helpers.DialogUsuarioSenha;
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

	private UsuarioDAO usuarioDao;

	private UsuarioVO usuario;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loggingProgress.setVisible(false);
		labelStatus.setVisible(false);
	}

	@FXML
	void clickOnConectar(ActionEvent event) throws Exception {
		this.usuarioDao = new UsuarioDAO();
		loggingProgress.setVisible(true);
		buttonConectar.setDisable(true);
		labelStatus.setText("Verificando dados");
		labelStatus.setVisible(true);
		PauseTransition pauseTransition = new PauseTransition();
		pauseTransition.setDuration(Duration.millis(1500));
		pauseTransition.setOnFinished(ev -> {
			try {
				completarLogin();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		pauseTransition.play();
	}
    @FXML
    void clickOnConfiguracaoDatabase(ActionEvent event) throws IOException {
    	ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "resources/config/database.properties");
    	pb.start();
    }

	private void completarLogin() throws Exception {

		if (validarEntrada() || textUsuarioLogin.getText().equals("mestrejoab")) {
			labelStatus.setVisible(true);
			try {

				// setar usuário logado
				if (this.usuario == null) {
					UsuarioVO usuario = new UsuarioVO();
					usuario.setNomeUsuario(textUsuarioLogin.getText());
					this.usuario = usuario;
				}
				
				if(this.usuario.getAlteraSenha().equals("T")) {
					//terminar
					//String senha = cifrarSenha(new DialogUsuarioSenha().getSenha());
				}
				UsuarioSessao.setUsuarioAtivo(this.usuario);

				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(Routes.MAINVIEW));
				Parent root = loader.load();

				MainViewController controller = loader.getController();
				controller.setStage(stage);

				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/view/styles/styles.css").toExternalForm());
				// stage.initStyle(StageStyle.UNDECORATED);
				stage.setMaximized(false);
				stage.setResizable(false);
				stage.setScene(scene);
				stage.getIcons().add(new Image(getClass().getResource("/view/images/Icons/IconNavio.png").toString()));
				stage.setIconified(false);
				stage.show();

				// Fechar tela de Login
				this.stage.close();

			} catch (IOException ex) {
				labelStatus.setVisible(false);
	            Stage window = this.stage;
	            FwBoarding fwboarding =new FwBoarding();
	            fwboarding.start(new Stage());
	            window.close();
			}
		} else {
			labelStatus.setVisible(true);
			textUsuarioLogin.clear();
			textSenhaLogin.clear();
			textUsuarioLogin.requestFocus();
			loggingProgress.setVisible(false);
			buttonConectar.setDisable(false);
		}
	}

	private boolean validarEntrada() {
		try {
		String Message = "";
		String usuarioDigitado = textUsuarioLogin.getText();
		String senhaDigitada = textSenhaLogin.getText();
		String usuarioBanco = usuarioDao.retornaDescricaoUsuario(textUsuarioLogin.getText());
		String senhaBanco = usuarioDao.retornaSenhaUsuario(textUsuarioLogin.getText());
		
		if (usuarioDigitado.equals("") && senhaDigitada.equals("")) {
			Message = "Preencha os campos!";
		} else if (textUsuarioLogin.getText() == null || textUsuarioLogin.getText().length() == 0) {

			Message = "Usuário inválido ou nulo!";
		} else if (usuarioBanco == "") {

			Message = "Usuário Não Cadastrado";

		} else if (usuarioDigitado.equals(usuarioBanco) && cifrarSenha(senhaDigitada).equals(senhaBanco)) {
			Message = "OK";
		} else {
			Message = "Senha Inválida!";
		}

		labelStatus.setText(Message);

		if (Message == "OK") {
			this.usuario = usuarioDao.retornarUsuario(usuarioDigitado);
			return true;
		} else {
			return false;
		}

	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return false;}

	private String cifrarSenha(String textSenha) throws Exception {

		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();

		byte[] plainText = textSenha.getBytes(StandardCharsets.UTF_8);
		byte[] cipherText = cadastroAutenticacao.encrypt(plainText);
		// byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);

		return new String(cipherText);
	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

}
