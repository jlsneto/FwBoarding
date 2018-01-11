package fwboarding;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import helpers.Routes;
import fwboarding.MainViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label txtCurrentWindow;
    
    private HamburgerBasicCloseTransition transitionHamburguer;
    public static Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    	final HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);
        
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
        	if(e.getClickCount() == 1) {

        		//reativa drawer
        		drawer.setDisable(false);
        		
        		transition.setRate(transition.getRate() * -1);
        		 //System.out.println(this.transitionHamburguer.getStatus());
                transition.play();
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
        	}
        });
        
        try {
            VBox sidePane = FXMLLoader.load(getClass().getResource(Routes.MENULATERALVIEW));
            AnchorPane navioPane = FXMLLoader.load(getClass().getResource(Routes.NAVIOVIEW));
            AnchorPane embarquePane = FXMLLoader.load(getClass().getResource(Routes.EMBARQUEVIEW));
            AnchorPane usuarioview = FXMLLoader.load(getClass().getResource(Routes.USUARIOVIEW));
            AnchorPane grupoUsuarioPane = FXMLLoader.load(getClass().getResource(Routes.GRUPOUSUARIOVIEW));
            AnchorPane bemVindoPane = FXMLLoader.load(getClass().getResource(Routes.BEMVINDOVIEW));
            setNode(bemVindoPane);
            drawer.setSidePane(sidePane);

            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                    	transition.setRate(transition.getRate()*(-1.0));
                        transition.play();
                        drawer.setDisable(true);
                        switch (node.getAccessibleText()) {
                            case "inicioMenu":
                                drawer.close();
                                setNode(bemVindoPane);
                                break;
                            case "navioMenu":
                                drawer.close();
                                setNode(navioPane);
                                break;
                            case "usuarioMenu":
                                drawer.close();
                                setNode(usuarioview);
                                break;
                            case "grupoUsuarioMenu":
                                drawer.close();                                
                                setNode(grupoUsuarioPane);
                                break;
                            case "embarqueMenu":
                            	drawer.close();
                            	setNode(embarquePane);
                            	break;
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }
    
    public void setStage(Stage stage) {
    	this.stage = stage;
    }
    
    public Stage getStage() {
    	return this.stage;
    }

}
