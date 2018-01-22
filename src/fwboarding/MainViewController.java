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

import helpers.DialogMessage;
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
import login.UsuarioSessao;

public class MainViewController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label txtCurrentWindow;
    
    @FXML
    private Label usuarioLogado;
    
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
        //usuarioLogado.setText(UsuarioSessao.getUsuarioAtivo().getNomeUsuario());
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
        	AnchorPane bemVindoPane = FXMLLoader.load(getClass().getResource(Routes.BEMVINDOVIEW));
        	setNode(bemVindoPane);
            drawer.setSidePane(sidePane);

            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                    	transition.setRate(transition.getRate()*(-1.0));
                        transition.play();
                        try {
                        switch (node.getAccessibleText()) {
                            case "inicioMenu":
                            	drawer.setDisable(true);
                                drawer.close();
                                setNode(bemVindoPane);
                                break;
                            case "navioMenu":     
                            	AnchorPane navioPane = FXMLLoader.load(getClass().getResource(Routes.NAVIOVIEW));
                            	drawer.setDisable(true);
                                drawer.close();
                                setNode(navioPane);
                                break;
                            case "usuarioMenu":
                            	AnchorPane usuarioview = FXMLLoader.load(getClass().getResource(Routes.USUARIOVIEW));
                            	drawer.setDisable(true);
                                drawer.close();
                                setNode(usuarioview);
                                break;
                            case "grupoUsuarioMenu":
                            	AnchorPane grupoUsuarioPane = FXMLLoader.load(getClass().getResource(Routes.GRUPOUSUARIOVIEW));
                            	drawer.setDisable(true);
                                drawer.close();                                
                                setNode(grupoUsuarioPane);
                                break;
                            case "embarqueMenu":
								AnchorPane embarquePane = FXMLLoader.load(getClass().getResource(Routes.EMBARQUEVIEW));
                            	drawer.setDisable(true);
                            	drawer.close();
                            	setNode(embarquePane);
                            	break;
                        }
                    }catch (IOException e1) {
                    	e1.printStackTrace();
                    }});
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
