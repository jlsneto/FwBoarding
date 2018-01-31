package helpers;

import fwboarding.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.vo.SafraVO;
import safra.CadastroSafraController;
import usuario.CadastroSenhaController;

public class DialogCadastroSafra {
	private String anoSafra;
	public static SafraVO safraAlterar;
	public DialogCadastroSafra() {
		if(CadastroSafraController.isAlterarSafra == true){
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(DialogUsuarioSenha.class.getResource(Routes.CADASTROSAFRAVIEW));
				AnchorPane cadastroSafraView = loader.load();

				Stage stage = new Stage();
				stage.initOwner(MainViewController.stage);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setResizable(false);
				Scene scene = new Scene(cadastroSafraView);

				CadastroSafraController controller = loader.getController();
				controller.setDialogStage(stage);
				controller.setSafraAlterar(safraAlterar);
				

				stage.setScene(scene);
				stage.showAndWait();
				this.anoSafra = controller.getAnoSafra();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(DialogUsuarioSenha.class.getResource(Routes.CADASTROSAFRAVIEW));
				AnchorPane cadastroSafraView = loader.load();

				Stage stage = new Stage();
				stage.initOwner(MainViewController.stage);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.setResizable(false);
				Scene scene = new Scene(cadastroSafraView);

				CadastroSafraController controller = loader.getController();
				controller.setDialogStage(stage);
				//controller.setSafraAlterar(safraAlterar);
				

				stage.setScene(scene);
				stage.showAndWait();
				this.anoSafra = controller.getAnoSafra();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public String getAnoSafra() {
		return anoSafra;
		
	}
	
}
