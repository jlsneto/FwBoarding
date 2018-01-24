package fwboarding;

import com.jfoenix.controls.JFXButton;
import com.sun.java.swing.plaf.windows.resources.windows;

import helpers.DialogUsuarioSenha;
import helpers.Routes;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.UsuarioSessao;
import model.dao.UsuarioDAO;
import usuario.CadastroAutenticacaoBO;
import usuario.CadastroSenhaController;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class MenuLateralController implements Initializable {

	@FXML
	private MenuButton buttonUsuarioNome;
    @FXML
    private JFXButton inicioBtn;
    @FXML
    private JFXButton navioBtn;
    @FXML
    private JFXButton usuarioBtn;
    @FXML
    private JFXButton grupoUsuarioBtn;
    @FXML
    private JFXButton embarqueBtn;
    @FXML
    private JFXButton trocarUsuarioBtn;
    @FXML
    private JFXButton sairBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	buttonUsuarioNome.setText(UsuarioSessao.getUsuarioAtivo().getNomeUsuario());
    }    

    @FXML
    private void trocarUsuario(ActionEvent event) {
        try {
            Stage window=(Stage) trocarUsuarioBtn.getScene().getWindow();
            FwBoarding fwboarding =new FwBoarding();
            fwboarding.start(new Stage());
            window.close();
        } catch (Exception ex) {
            Logger.getLogger(MenuLateralController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void alterarSenha() throws Exception {
    	String senha = cifrarSenha(new DialogUsuarioSenha().getSenha());
    	UsuarioSessao.getUsuarioAtivo().setSenha(senha);
    	UsuarioDAO usuarioDao = new UsuarioDAO();
    	usuarioDao.alterar(UsuarioSessao.getUsuarioAtivo());
    }
    
	private String cifrarSenha(String textSenha) throws Exception {

		CadastroAutenticacaoBO cadastroAutenticacao = new CadastroAutenticacaoBO();

		byte[] plainText = textSenha.getBytes(StandardCharsets.UTF_8);
		byte[] cipherText = cadastroAutenticacao.encrypt(plainText);
		// byte[] decryptedCipherText = cadastroAutenticacao.decrypt(cipherText);

		return new String(cipherText);
	}
	
    @FXML
    private void sair(ActionEvent event) {
        Platform.exit();
    }
    
}