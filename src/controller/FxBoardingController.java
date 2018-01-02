package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import view.FwBoarding;

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

	public void clickOnNavio(ActionEvent event) {
		FwBoarding.carregarTelaNavioConsulta();

	}

	@FXML
	void clickOnGrupoUsuario(ActionEvent event) throws IOException {
		FwBoarding.carregarTelaGrupoConsulta();
	}

	@FXML
	public void clickOnUsuario() {
		FwBoarding.carregarTelaUsuario();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// Setup da telaFxBoarding
		FwBoarding.stage.setOnCloseRequest(event -> {

			if (FwBoarding.confirmouCancelamentoOuFehamento()) {
				// ... Usuário clicou ok
				Platform.exit();
			} else {
				event.consume();
			}
		});
	}
	
	@FXML
	public void clickOnEmbarque() {
		FwBoarding.carregarTelaEmbarque();
	}

	@FXML
	public void onExit() {
		// Sair
		if (FwBoarding.confirmouCancelamentoOuFehamento()) {
			// ... Usuário clicou ok
			Platform.exit();
		}
	}

}
