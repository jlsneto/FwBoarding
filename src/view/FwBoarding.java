package view;

import java.io.IOException;

import controller.CadastroNavioController;
import controller.ConsultasNavioController;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FwBoarding extends Application {

	public static Stage stage;
	public static BorderPane rootLayout;

	@Override
	public void start(Stage stage) throws IOException {
		FwBoarding.stage = stage;
		carregarTelaLogin();
		carregarRootLayout();

	}

	public void carregarRootLayout() {
		try {

			// Carrega Tela Principal
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TelaPrincipal.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);

			stage.setScene(scene);
			stage.setTitle("FwBoarding");
			stage.setMaximized(false);
			stage.getIcons().add(new Image(this.getClass().getResource("/view/images/Icons/IconNavio.png").toString()));

			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void carregarConfiguracaoDatabase() throws IOException {

		// Carrega o arquivo fxml e cria um novo stage para a janela popup.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/ConfiguracaoDatabase.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		// Cria o palco dialogStage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Configuração do Banco de Dados");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		// Mostra a janela e espera até o usuário fechar.
		dialogStage.showAndWait();

	}

	public static void carregarTelaLogin() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/TelaLogin.fxml"));
		AnchorPane page = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("Login");
		//dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);

		LoginController controller = loader.getController();
		controller.setDialogStage(dialogStage);

		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
	}

	public static void carregarTelaNavioConsulta() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(FwBoarding.class.getResource("/view/TelaConsultas.fxml"));
			AnchorPane navio;
			navio = (AnchorPane) loader.load();
			// Define a TelaConsultas no centro do root layout.
			FwBoarding.rootLayout.setCenter(navio);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
public static void carregarTelaCadastroNavio() throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(FwBoarding.class.getResource("/view/CadastroNavio.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
	
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cadastro de Navios");
		//dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UTILITY);
		dialogStage.initOwner(FwBoarding.stage);
		dialogStage.setResizable(false);
		
		//Define Palco deste Dialog
		CadastroNavioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
		if(dialogStage.isShowing() == false) {
			//pass
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
