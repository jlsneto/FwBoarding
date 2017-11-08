package fwboarding;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClassePrincipal extends Application {

	private Stage stage;
	public static BorderPane rootLayout;

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		
		initRootLayout();

	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TelaPrincipal.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);

			stage.setScene(scene);
			stage.setTitle("FwBoarding");
			stage.setMaximized(true);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
