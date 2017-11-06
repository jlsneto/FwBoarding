package fwboarding;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClassePrincipal extends Application {
	
	private Stage stage;
	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		
		initRootLayout();
		
	}
	
	public void initRootLayout() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));

			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("FwBoarding");
			stage.setMaximized(true);
			stage.show();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
