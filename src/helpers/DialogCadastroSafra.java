package helpers;

import fwboarding.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import safra.CadastroSafraController;
import usuario.CadastroSenhaController;

public class DialogCadastroSafra {
	private String anoSafra;
	
	public DialogCadastroSafra() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(DialogUsuarioSenha.class.getResource(Routes.CADASTROSENHAVIEW));
			AnchorPane cadastroSenhaView = loader.load();

			Stage stage = new Stage();
			stage.initOwner(MainViewController.stage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			Scene scene = new Scene(cadastroSenhaView);

			CadastroSafraController controller = loader.getController();
			controller.setDialogStage(stage);

			stage.setScene(scene);
			stage.showAndWait();
			this.anoSafra = controller.getAnoSafra();
		} catch (Exception e) {
			System.out.println("ERRO!");
		}
	}

	public String getAnoSafra() {
		return anoSafra;
		
	}
	
}
