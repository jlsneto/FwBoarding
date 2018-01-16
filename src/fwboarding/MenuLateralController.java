package fwboarding;

import com.jfoenix.controls.JFXButton;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class MenuLateralController implements Initializable {

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
    }    

    @FXML
    private void trocarUsuario(ActionEvent event) {
        try {
            Stage window=(Stage) trocarUsuarioBtn.getScene().getWindow();
            FwBoarding hospitalFX=new FwBoarding();
            hospitalFX.start(new Stage());
            window.close();
        } catch (Exception ex) {
            Logger.getLogger(MenuLateralController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void sair(ActionEvent event) {
        Platform.exit();
    }
    
}