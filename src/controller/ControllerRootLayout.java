package controller;

import java.io.IOException;

/**
 * Sample Skeleton for 'RootLayout.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ControllerRootLayout extends ClassePrincipal{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="MenuItemCadastrarNavio"
    private MenuItem MenuItemCadastrarNavio; // Value injected by FXMLLoader

    @FXML
    void abrirTelaCadastro(ActionEvent event) throws IOException {
		//carregar o rootlayout
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClassePrincipal.class.getResource("/view/CadNavio.fxml"));
		AnchorPane cadNavio = loader.load();
		
		ControllerRootLayout controller = loader.getController();
		
		Scene scene = new Scene(cadNavio);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert MenuItemCadastrarNavio != null : "fx:id=\"MenuItemCadastrarNavio\" was not injected: check your FXML file 'RootLayout.fxml'.";

    }
}