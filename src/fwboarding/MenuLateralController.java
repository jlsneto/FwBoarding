package fwboarding;

import com.jfoenix.controls.JFXButton;
import com.sun.java.swing.plaf.windows.resources.windows;

import helpers.Routes;

import java.net.URL;
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
    private void alterarSenha() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource(Routes.CADASTROSENHAVIEW));
    		AnchorPane cadastroSenhaView = loader.load();
    		

    		Stage stage = new Stage();
    		stage.initOwner(MainViewController.stage);
    		stage.setResizable(false);
    		Scene scene = new Scene(cadastroSenhaView);
    		
    		CadastroSenhaController controller = loader.getController();
    		controller.setDialogStage(stage);
    		
    		stage.setScene(scene);
    		stage.showAndWait();
    		
    	}catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
    }
    @FXML
    private void sair(ActionEvent event) {
        Platform.exit();
    }
    
}