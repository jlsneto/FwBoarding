package helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import fwboarding.MainViewController;
import fwboarding.MenuLateralController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import usuario.CadastroSenhaController;

public class DialogUsuarioSenha {

	private String senha;

	public DialogUsuarioSenha() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DialogUsuarioSenha.class.getResource(Routes.CADASTROSENHAVIEW));
			AnchorPane cadastroSenhaView = loader.load();

			Stage stage = new Stage();
			stage.initOwner(MainViewController.stage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			Scene scene = new Scene(cadastroSenhaView);

			CadastroSenhaController controller = loader.getController();
			controller.setDialogStage(stage);

			stage.setScene(scene);
			stage.showAndWait();
			this.senha = controller.getSenha();
		} catch (Exception e) {
			Logger.getLogger(MenuLateralController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public String getSenha() {
		return senha;
		
	}
}
