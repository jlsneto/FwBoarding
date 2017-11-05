package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClassePrincipal extends Application {
	
	public Stage primaryStage;
	public BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("FwBoarding");
		
		initRootLayout();
		
		
	}

	private void initRootLayout() {
		
		try {
			//carregar o rootlayout
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ClassePrincipal.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Exibe a scene que contêm o rootlayout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
