package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fwboarding.ClassePrincipal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class TelaPrincipalController implements Initializable {

	@FXML
	private Menu menuGeral;

	@FXML
	private MenuItem menuItemGeralNavio;

	@FXML
	private Menu menuGeralGrupoUsuarios;

	@FXML
	private MenuItem menuItemGrupoUsuariosGrupos;

	@FXML
	private Menu menuGeralUsuarios;

	@FXML
	private MenuItem menuItemUsuariosUsuarios;

	@FXML
	private MenuItem menuItemUsuariosTrocarUsuario;

	@FXML
	private MenuItem menuItemGeralSair;

	@FXML
	private Menu menuEmbarques;

	@FXML
	private MenuItem menuItemNovoEmbarque;

	@FXML
	private Menu menuRelatorios;

	public void initTelaNavio() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/TelaConsultas.fxml"));
		AnchorPane navio = (AnchorPane) loader.load();
		// Define a TelaConsultas no centro do root layout.
		ClassePrincipal.rootLayout.setCenter(navio);

	}

	public void clickOnNavio(ActionEvent event) throws IOException {

		initTelaNavio();

	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void onExit() {
		//Sair
		Platform.exit();
	}

}
