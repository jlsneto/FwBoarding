package fwboarding;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FxBoardingController implements Initializable {

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

	public void initialize(URL location, ResourceBundle resources) {
		// Setup da telaFxBoarding

	}

	@FXML
	public void onExit() {
		// Sair
		Platform.exit();

	}

}
