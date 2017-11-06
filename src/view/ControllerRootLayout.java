package view;

import java.io.IOException;

/**
 * Sample Skeleton for 'RootLayout.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import controller.ClassePrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControllerRootLayout implements Initializable{

	private Stage primaryStage;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="MenuItemCadastrarNavio"
    private MenuItem MenuItemCadastrarNavio; // Value injected by FXMLLoader

    @FXML
    void abrirTelaCadastro(ActionEvent event) throws IOException {
		//carregar o rootlayout
    	URL url = getClass().getResource("/view/CadNavio.fxml");
    	Parent root = FXMLLoader.load(url);
    	
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert MenuItemCadastrarNavio != null : "fx:id=\"MenuItemCadastrarNavio\" was not injected: check your FXML file 'RootLayout.fxml'.";

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}