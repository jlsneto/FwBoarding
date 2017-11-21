package fwboarding;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClassePrincipal extends Application {

	public static Stage stage;
	public static BorderPane rootLayout;

	@Override
	public void start(Stage stage) throws IOException {
		ClassePrincipal.stage = stage;

		initRootLayout();

	}

	public void initRootLayout() {
		try {
			
			//Carrega Tela Principal
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
	
	void carregarConfiguracaoDatabase() throws IOException{

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
		loader.setLocation(ClassePrincipal.class.getResource("/view/TelaLogin.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Login");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setResizable(false);
		
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		dialogStage.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
